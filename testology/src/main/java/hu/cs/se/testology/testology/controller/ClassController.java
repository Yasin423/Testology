package hu.cs.se.testology.testology.controller;

import hu.cs.se.testology.testology.model.Class;
import hu.cs.se.testology.testology.services.ClassService;
import hu.cs.se.testology.testology.services.ClassServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClassController {

    @Autowired
    private ClassServiceImpl classService;

    @GetMapping("/teacher/createClass")
    public String renderCreateClassPage(Model model){

        model.addAttribute("class", new Class());
        return "pages/htmlFile/createClass";
    }

    @PostMapping("/teacher/createClass")
    public String createClass(@ModelAttribute("class") Class aClass){
        classService.save(aClass);
        return "redirect:/class/list";
    }
    @RequestMapping(path = {"/class/list"},method = RequestMethod.GET)
    public String getClassPage(Model model){
        model.addAttribute("class",classService.findAllClasses());
        return "pages/htmlFile/classList";
    }
    @GetMapping("/class/edit/{id}")
    public String editClass(@PathVariable Long id, Model model){
       Class classes=classService.editClassById(id);
       model.addAttribute("classes",classes);
        return "pages/htmlFile/createClass";
    }
    @GetMapping("/class/delete/{id}")
    public String deleteClass(@PathVariable Long id){
        classService.deleteById(id);
        return "redirect:/class/list";
    }
}
