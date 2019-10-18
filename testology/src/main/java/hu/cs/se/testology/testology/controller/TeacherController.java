package hu.cs.se.testology.testology.controller;

import hu.cs.se.testology.testology.model.Class;
import hu.cs.se.testology.testology.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherController {

    @Autowired
    private ClassService classService;

    @GetMapping("/teacher/createClass")
    public String renderCreateClassPage(Model model){

        model.addAttribute("class", new Class());
        return "pages/htmlFile/createClass";
    }

    @PostMapping("/teacher/createClass")
    public String createClass(@ModelAttribute("class") Class aClass){

        classService.save(aClass);

        return "redirect:/teacher/createClass";
    }

    @PostMapping("/error")
    public String createClasse(@ModelAttribute("class") Class aClass){
        return "redirect:/pages/htmlFile/createClass";
    }
}
