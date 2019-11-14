package hu.cs.se.testology.testology.controller;

import hu.cs.se.testology.testology.model.*;
import hu.cs.se.testology.testology.model.Class;
import hu.cs.se.testology.testology.repositories.RegistrationRepository;
import hu.cs.se.testology.testology.security.UserPrincipal;
import hu.cs.se.testology.testology.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private ClassService classService;

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private QuestionAnswerService questionAnswerService;

    @Autowired
    private TestResultService testResultService;

    @GetMapping("/test/create")
    public String renderCreateTestPage(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {

        model.addAttribute("classes", classService.getAllByTeacher(userPrincipal.getUser()));
        model.addAttribute("test", new Test());
        model.addAttribute("activeParent", "test");

        return "test/createTest";
    }

    @PostMapping("test/create")
    public String createTest(@ModelAttribute Test test, @RequestParam Long classID) {

        test.setaClass(classService.findClassByID(classID));

        testService.save(test);

        return "redirect:/test/list";
    }

    @GetMapping("/test/list")
    public String testList(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {

        //these are the answered tests for teacher
        //these are the taken tests for student
        List<Test> secondCatTests = new ArrayList<>();

        Map<String , String> takenTestsResults = new HashMap<>();

        //these are the not answered tests for teacher
        //these are the not taken tests for student
        List<Test> firstCatTests = new ArrayList<>();

        if (userPrincipal.getUser().getRole().equals("ROLE_TEACHER")) {
            for (Class c : classService.getAllByTeacher(userPrincipal.getUser())) {
                for(Test test : testService.findAllByAClass(c)){
                    if(test.isAnswered())
                        secondCatTests.add(test);
                    if (!test.isAnswered())
                        firstCatTests.add(test);
                }
            }

        }
        if (userPrincipal.getUser().getRole().equals("ROLE_STUDENT")) {

            for(TestResult testResult : testResultService.findAllByStudent(userPrincipal.getUser())){
                secondCatTests.add(testResult.getTest());
                takenTestsResults.put(String.valueOf(testResult.getTest().getId()) , String.valueOf(testResult.getScore()));

            }

            for (ClassRegistration classRegistration : registrationRepository.findAllByUser(userPrincipal.getUser())) {

                for(Test test : testService.findAllByAClass(classRegistration.getaClass())){
                    if (test.isAnswered() && !secondCatTests.contains(test)){
                        firstCatTests.add(test);
                    }
                }
            }
        }

        model.addAttribute("testResults" , takenTestsResults);
        model.addAttribute("newTests", firstCatTests);
        model.addAttribute("takenTests", secondCatTests);
        model.addAttribute("activeParent", "test");

        return "test/testList";
    }

    @GetMapping("/test/view/{id}")
    public String renderViewTestPage(@PathVariable Long id, Model model) {

        Test test = testService.findTestById(id);

        model.addAttribute("questions", test.getQuestions());
        model.addAttribute("questionsNumber", test.getQuestions().size());
        model.addAttribute("question", new Question());
        model.addAttribute("test", test);
        model.addAttribute("isViewing", true);
        model.addAttribute("activeParent", "test");
        return "test/testView";
    }

    @GetMapping("/test/{id}/take/{userId}")
    public String renderTakeTestViewPage(@PathVariable(name = "id") Long id, @PathVariable(name = "userId") Long userId, Model model) {

        Test test = testService.findTestById(id);

        AnswersKey answersKey = new AnswersKey();

        model.addAttribute("userId", userId);
        model.addAttribute("testId", id);
        model.addAttribute("test", test);
        model.addAttribute("questions", questionService.findAllByTest(test));
        model.addAttribute("answersKey", answersKey);
        model.addAttribute("isViewing", false);
        model.addAttribute("tempText", new String[]{"firstAnswer", "secondAnswer", "thirdAnswer", "fourthAnswer"});

        return "test/testView";
    }

    @PostMapping("/test/{testId}/submit/{userId}")
    public String submitTest(@PathVariable(name = "testId") Long id, @PathVariable(name = "userId") Long userId,
                             @ModelAttribute AnswersKey answersKey, @AuthenticationPrincipal UserPrincipal userPrincipal) {

        Test test = testService.findTestById(id);
        List<Question> questions = test.getQuestions();
        User user = userPrincipal.getUser();

        if (user.getRole().equals("ROLE_TEACHER")) {
            for (Question question : questions) {
                question.setCorrectAnswer(answersKey.getAnswers().get(String.valueOf(question.getId())));

                questionService.save(question);
            }

            test.setAnswered(true);
            testService.save(test);

            for(TestResult testResult : testResultService.findAllByTest(test)){
                checkTest(test , testResult.getStudent() , true);
            }

        } else if (user.getRole().equals("ROLE_STUDENT")) {

            for (Question question : questions) {

                QuestionAnswer questionAnswer = new QuestionAnswer();

                questionAnswer.setQuestion(question);
                questionAnswer.setUser(user);
                questionAnswer.setAnswer(answersKey.getAnswers().get(String.valueOf(question.getId())));

                question.getQuestionAnswers().add(questionAnswer);

                questionAnswerService.save(questionAnswer);
            }

            if(test.isAnswered()){
                checkTest(test , user , false);
            }

        }


        return "redirect:/test/list";
    }

    private int checkTest(Test test , User user , boolean isAnswerKeyChanged) {
        int score = 0;
        List<Question> questions = testService.findTestById(test.getId()).getQuestions();

        for (Question question : questions){
            QuestionAnswer questionAnswer = questionAnswerService.findByQuestionAndUser(question , user);

            if(questionAnswer.getAnswer().equals(question.getCorrectAnswer())){
                score = score + 1;
            }
        }


        TestResult testResult = (isAnswerKeyChanged) ? testResultService.findByTest(test) : new TestResult();

        testResult.setTest(test);
        testResult.setStudent(user);
        testResult.setTaken(true);
        testResult.setScore(score);

        testResultService.save(testResult);

        return score;
    }

}
