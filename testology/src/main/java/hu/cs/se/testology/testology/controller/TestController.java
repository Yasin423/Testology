package hu.cs.se.testology.testology.controller;

import hu.cs.se.testology.testology.model.*;
import hu.cs.se.testology.testology.model.Class;
import hu.cs.se.testology.testology.repositories.RegistrationRepository;
import hu.cs.se.testology.testology.security.UserPrincipal;
import hu.cs.se.testology.testology.services.ClassService;
import hu.cs.se.testology.testology.services.QuestionService;
import hu.cs.se.testology.testology.services.TestService;
import hu.cs.se.testology.testology.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

        List<Test> tests = new ArrayList<>();

        if (userPrincipal.getUser().getRole().equals("ROLE_TEACHER")) {
            for (Class c : userPrincipal.getUser().getClassesAsTeacher()){
                tests.addAll(testService.findAllByAClass(c));
            }
        }
        if (userPrincipal.getUser().getRole().equals("ROLE_STUDENT")) {
            for (ClassRegistration classRegistration : registrationRepository.findAllByUser(userPrincipal.getUser())) {
                tests.addAll(classRegistration.getaClass().getTests());
            }
        }



        model.addAttribute("tests", tests);

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
        model.addAttribute("questions", test.getQuestions());
        model.addAttribute("answersKey", answersKey);
        model.addAttribute("isViewing", false);
        model.addAttribute("tempText", new String[]{"firstAnswer", "secondAnswer", "thirdAnswer", "fourthAnswer"});

        return "test/testView";
    }

    @PostMapping("/test/{testId}/submit/{userId}")
    public String submitTest(@PathVariable(name = "testId") Long id, @PathVariable(name = "userId") Long userId,
                             @ModelAttribute AnswersKey answersKey, @AuthenticationPrincipal UserPrincipal userPrincipal) {

        List<Question> questions = testService.findTestById(id).getQuestions();
        User user = userPrincipal.getUser();

        for (Question question : questions) {

            QuestionAnswer questionAnswer = new QuestionAnswer();

            questionAnswer.setQuestion(question);
            questionAnswer.setAnswer(answersKey.getAnswers().get(String.valueOf(question.getId())));
            questionAnswer.setUser(user);

            question.getQuestionAnswers().add(questionAnswer);

            System.out.println(question.getQuestionAnswers().get(0).getAnswer());
            System.out.println(questionAnswer.getQuestion().getQuestionText());
            System.out.println(questionAnswer.getUser().getUsername());

            userService.save(user);
            questionService.save(question);
        }

        return "redirect:/test/list";
    }
}
