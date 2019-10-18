package hu.cs.se.testology.testology.controller;

import hu.cs.se.testology.testology.model.Class;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherController {

    @GetMapping("/teacher/createClass")
    public String renderCreateClassPage(Model model){

        model.addAttribute("class", new Class());
        return "pages/htmlFile/createClass";
    }
}
