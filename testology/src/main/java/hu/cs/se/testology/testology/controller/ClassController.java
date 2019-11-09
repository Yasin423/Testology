package hu.cs.se.testology.testology.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import hu.cs.se.testology.testology.model.Class;
import hu.cs.se.testology.testology.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.jws.WebParam;

@Controller
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping("/class/add")
    public String renderCreateClassPage(Model model){

        model.addAttribute("class", new Class());
        return "createClass";
    }

    @PostMapping("/class/add")
    public String createClass(@ModelAttribute("class") Class aClass){

        classService.save(aClass);

        return "redirect:/class/list";
    }

    @GetMapping("/class/list")
    public String renderClassListPage(Model model){

        model.addAttribute("classes" , classService.findAll());

        return "classList";
    }

    @GetMapping("/class/{id}/edit")
    public String editClass(@PathVariable Long id , Model model){

        model.addAttribute("class" , classService.findClassByID(id));


        return "createClass";
    }

    @GetMapping("/class/{id}/delete")
    public String deleteClass(@PathVariable Long id){

        classService.deleteByID(id);

        return "redirect:/class/list";
    }

}
