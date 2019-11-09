package hu.cs.se.testology.testology.controller;

import hu.cs.se.testology.testology.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class TestRestController {

    @Autowired
    private ClassRepository classRepository;

    @GetMapping("/test/create")
    public ModelAndView renderCreateTestPage(Model model){

        model.addAttribute("classes" , classRepository.findAll());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test/createTest");
        return modelAndView;
    }

    @PostMapping("test/create")
    public RedirectView createTest(){

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/test/list");
        return redirectView;
    }

    @GetMapping("/test/list")
    public ModelAndView renderTestListPage(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test/testList");
        return modelAndView;
    }
}
