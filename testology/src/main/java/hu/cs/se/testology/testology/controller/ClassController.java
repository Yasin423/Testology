package hu.cs.se.testology.testology.controller;

import hu.cs.se.testology.testology.model.*;
import hu.cs.se.testology.testology.model.Class;
import hu.cs.se.testology.testology.repositories.RegistrationRepository;
import hu.cs.se.testology.testology.security.UserPrincipal;
import hu.cs.se.testology.testology.services.ClassService;
import hu.cs.se.testology.testology.services.TestResultService;
import hu.cs.se.testology.testology.services.TestService;
import hu.cs.se.testology.testology.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.*;

@Controller
public class ClassController {

    @Autowired
    private ClassService classService;

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private TestService testService;

    @Autowired
    private TestResultService testResultService;

    @GetMapping("/class/add")
    public String renderCreateClassPage(Model model){

        model.addAttribute("class", new Class());
        model.addAttribute("activeParent" , "class");
        return "class/createClass";
    }

    @PostMapping("/class/add")
    public String createClass(@ModelAttribute("class") Class aClass , @AuthenticationPrincipal UserPrincipal userPrincipal){

        aClass.setTeacher(userPrincipal.getUser());

        try {
            aClass.setAccessCode(generateAccessCode());
        }catch (ConstraintViolationException e){
            aClass.setAccessCode(generateAccessCode());
        }

        classService.save(aClass);

        return "redirect:/class/list";
    }

    private String generateAccessCode(){
        return UUID.randomUUID().toString();
    }

    @GetMapping("/class/list")
    public String renderClassListPage(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal){

        List<Class> classes = new ArrayList<>();

        User user = userPrincipal.getUser();

        if(user.getRole().equals("ROLE_TEACHER")){
            classes = classService.getAllByTeacher(userPrincipal.getUser());
        }

        if(user.getRole().equals("ROLE_STUDENT")){

            for(ClassRegistration registration : registrationRepository.findAllByUser(user)){
                classes.add(registration.getaClass());
            }

        }

        model.addAttribute("classes" , classes);
        model.addAttribute("activeParent" , "class");
        return "class/classList";
    }

    @GetMapping("/class/edit/{id}")
    public String editClass(@PathVariable Long id , Model model){

        model.addAttribute("class" , classService.findClassByID(id));
        model.addAttribute("activeParent" , "class");

        return "class/createClass";
    }

    @GetMapping("/class/delete/{id}")
    public String deleteClass(@PathVariable Long id){

        Class aClass = classService.findClassByID(id);

        System.out.println(aClass.getId());

        classService.deleteByID(id);

        return "redirect:/class/list";
    }

    @GetMapping("/class/join")
    public String renderJoinClassPage(Model model){
        model.addAttribute("activeParent" , "class");
        return "class/joinClass";
    }

    @GetMapping("class/view/{id}")
    public String renderClassViewPage(@PathVariable Long id , Model model , @AuthenticationPrincipal UserPrincipal userPrincipal){

        Class aClass = classService.findClassByID(id);

        List<User> students = new ArrayList<>();

        //these are the not answered tests for teacher
        //these are the not taken tests for student
        List<Test> firstCatTests = new ArrayList<>();

        //these are the answered tests for teacher
        //these are the taken tests for student
        List<Test> secondCatTests = new ArrayList<>();

        Map<String , String> takenTestsResults = new HashMap<>();

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

        for(ClassRegistration registration : registrationRepository.findAllByAClass(aClass)){
            students.add(registration.getUser());
        }

        model.addAttribute("testResults" , takenTestsResults);
        model.addAttribute("newTests", firstCatTests);
        model.addAttribute("takenTests", secondCatTests);
        model.addAttribute("activeParent" , "class");
        model.addAttribute("classID" , id);
        model.addAttribute("students" , students);
        return "class/classView";
    }

    @PostMapping("/class/join")
    public String joinClass(@AuthenticationPrincipal UserPrincipal userPrincipal , @RequestParam("accessCode") String accessCode){


        Class aClass = classService.findByAccessCode(accessCode);

        System.out.println(aClass);

//        if(aClass == null){
//
//            return "redirect:/"
//        }

        User student = userPrincipal.getUser();

        ClassRegistration registration = new ClassRegistration();

        registration.setaClass(aClass);
        registration.setUser(student);

        aClass.getRegistrations().add(registration);

        classService.save(aClass);

        return "redirect:/class/list";
    }

    @GetMapping("/class/{classID}/remove/{student.id}")
    public String removeStudent(@PathVariable(name = "classID") Long classID , @PathVariable(name = "student.id") Long studentID){

        Class aClass = classService.findClassByID(classID);

        User student = userService.findUserByID(studentID);

        ClassRegistration registration = registrationRepository.findByAClassAndUser(aClass , student);

        registrationRepository.delete(registration);

        return "redirect:/class/view/" + classID;
    }
}
