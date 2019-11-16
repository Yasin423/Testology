package hu.cs.se.testology.testology.controller;

import hu.cs.se.testology.testology.model.*;
import hu.cs.se.testology.testology.model.Class;
import hu.cs.se.testology.testology.repositories.ClassRepository;
import hu.cs.se.testology.testology.repositories.RegistrationRepository;
import hu.cs.se.testology.testology.repositories.TestRepository;
import hu.cs.se.testology.testology.repositories.TestResultRepository;
import hu.cs.se.testology.testology.security.UserPrincipal;
import hu.cs.se.testology.testology.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @GetMapping("/")
    public String renderIndexPage(Model model , @AuthenticationPrincipal UserPrincipal userPrincipal){

        int numberOfClasses = 0;
        int numberOfTestsWithAnswerKey = 0 ;
        int numberOfTestsWithoutAnswerKey = 0;
        int numberOfTestsTaken = 0;
        int numberOfTestsNotTaken = 0;

        User currentUser = userPrincipal.getUser();

        List<Class> classes = classRepository.getAllByTeacher(currentUser);
        List<Test> tests = new ArrayList<>();

        numberOfClasses = classes.size();

        if(currentUser.getRole().equals("ROLE_STUDENT")){
            numberOfClasses = registrationRepository.countAllByUser(currentUser);
        }

        for(Class c : classes){
            numberOfTestsWithAnswerKey += testRepository.findAllByAClassAndAnsweredIsTrue(c).size();
            numberOfTestsWithoutAnswerKey += testRepository.findAllByAClassAndAnsweredIsFalse(c).size();
            tests.addAll(testRepository.findAllByAClass(c));
        }

        List<TestResult> testResults = testResultRepository.findAllByStudent(currentUser);

        numberOfTestsTaken = testResults.size();

        for (TestResult t : testResults){
            if(!tests.contains(t.getTest())) {
                numberOfTestsNotTaken ++;
            }
        }

        model.addAttribute("numClasses" , numberOfClasses);
        model.addAttribute("testsWithAnswerKey" , numberOfTestsWithAnswerKey);
        model.addAttribute("testsWithoutAnswerKey" , numberOfTestsWithoutAnswerKey);
        model.addAttribute("numberOfTestsTaken" , numberOfTestsTaken);
        model.addAttribute("numberOfTestsNotTaken" , numberOfTestsNotTaken);

        return "index";
    }
}
