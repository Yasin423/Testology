package hu.cs.se.testology.testology.controller;

import hu.cs.se.testology.testology.model.Question;
import hu.cs.se.testology.testology.model.Test;
import hu.cs.se.testology.testology.services.ClassService;
import hu.cs.se.testology.testology.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private ClassService classService;

    @Autowired
    private TestService testService;

    @GetMapping("/test/create")
    public String renderCreateTestPage(Model model){

        model.addAttribute("classes" , classService.findAll());
        model.addAttribute("test" , new Test());

        return "test/createTest";
    }

    @PostMapping("test/create")
    public String createTest(@ModelAttribute Test test, @RequestParam Long classID){

        test.setaClass(classService.findClassByID(classID));

        testService.save(test);

        return "redirect:/test/list";
    }

    @GetMapping("/test/list")
    public String testList(Model model){

        model.addAttribute("tests" , testService.findAll());


        return "test/testList";
    }

    @GetMapping("/test/view/{id}")
    public String renderViewTestPage(@PathVariable Long id , Model model){

        Test test = testService.findTestById(id);

        model.addAttribute("questions" , test.getQuestions());
        model.addAttribute("questionsNumber" , test.getQuestions().size());
        model.addAttribute("question" , new Question());
        model.addAttribute("test" , test);

        return "test/testView";
    }
}
