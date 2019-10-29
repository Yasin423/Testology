package hu.cs.se.testology.testology.controller;

import hu.cs.se.testology.testology.model.Test;
import hu.cs.se.testology.testology.services.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {

    @Autowired
    TestServiceImpl testService;

    @GetMapping("/Test/Register")
    public String showTest(Model model){
        model.addAttribute("test",new Test());
        return "pages/htmlFile/createTest";
    }
    @PostMapping("/Test/Register")
    public String creatTest(@ModelAttribute Test test){
        testService.saveTest(test);
        return "redirect:/test/list";
    }
    @RequestMapping(path = {"/test/list","/"},method = RequestMethod.GET)
    public String getTestPage(Model model){
        model.addAttribute("test",testService.findAll());
        return "pages/htmlFile/testList";
    }
     @GetMapping("/test/edit/{id}")
    public String editTest(@PathVariable Long id,Model model) {
        Test test = testService.findTestById(id);
        model.addAttribute("test",test);
        return "pages/htmlFile/createTest";
    }
    @GetMapping("/test/list/{id}")
    public String deleteTestById(@PathVariable Long id){
        testService.deleteTestById(id);
        return "redirect:/test/list";
    }

}
