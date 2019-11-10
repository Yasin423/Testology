package hu.cs.se.testology.testology.controller;

import hu.cs.se.testology.testology.model.Class;
import hu.cs.se.testology.testology.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping("/class/add")
    public String renderCreateClassPage(Model model){

        model.addAttribute("class", new Class());
        return "class/createClass";
    }

    @PostMapping("/class/add")
    public String createClass(@ModelAttribute("class") Class aClass){

        classService.save(aClass);

        return "redirect:/class/list";
    }

    @GetMapping("/class/list")
    public String renderClassListPage(Model model){

        model.addAttribute("classes" , classService.findAll());

        return "class/classList";
    }

    @GetMapping("/class/edit/{id}")
    public String editClass(@PathVariable Long id , Model model){

        model.addAttribute("class" , classService.findClassByID(id));


        return "class/createClass";
    }

    @GetMapping("/class/delete/{id}")
    public String deleteClass(@PathVariable Long id){

        classService.deleteByID(id);

        return "redirect:/class/list";
    }

}
