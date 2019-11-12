package hu.cs.se.testology.testology.controller;

import hu.cs.se.testology.testology.model.Class;
import hu.cs.se.testology.testology.security.UserPrincipal;
import hu.cs.se.testology.testology.services.ClassService;
import hu.cs.se.testology.testology.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClassController {

    @Autowired
    private ClassService classService;

    @Autowired
    private UserService userService;

    @GetMapping("/class/add")
    public String renderCreateClassPage(Model model){

        model.addAttribute("class", new Class());
        model.addAttribute("activeParent" , "class");
        return "class/createClass";
    }

    @PostMapping("/class/add")
    public String createClass(@ModelAttribute("class") Class aClass , @AuthenticationPrincipal UserPrincipal userPrincipal){

        aClass.setTeacher(userPrincipal.getUser());

        classService.save(aClass);

        return "redirect:/class/list";
    }

    @GetMapping("/class/list")
    public String renderClassListPage(Model model){

        model.addAttribute("classes" , classService.findAll());
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

        classService.deleteByID(id);

        return "redirect:/class/list";
    }

    @GetMapping("/class/join")
    public String renderJoinClassPage(Model model){
        model.addAttribute("activeParent" , "class");
        return "class/joinClass";
    }

    @GetMapping("class/view/{id}")
    public String renderClassViewPage(@PathVariable Long id , Model model){

        Class aClass = classService.findClassByID(id);
        model.addAttribute("activeParent" , "class");
        model.addAttribute("classID" , id);
        model.addAttribute("students" , aClass.getStudents());
        return "class/classView";
    }

    @PostMapping("/class/join")
    public String joinClass(@AuthenticationPrincipal UserPrincipal userPrincipal , @RequestParam("accessCode") String accessCode){


        Class aClass = classService.findByAccessCode(accessCode);
        aClass.getStudents().add(userPrincipal.getUser());

        classService.save(aClass);

        return "redirect:/class/list";
    }

    @GetMapping("/class/{classID}/remove/{student.id}")
    public String removeStudent(@PathVariable(name = "classID") Long classID , @PathVariable(name = "student.id") Long studentID){

        Class aClass = classService.findClassByID(classID);

        aClass.getStudents().remove(userService.findUserByID(studentID));

        classService.save(aClass);

        return "redirect:/class/view/" + classID;
    }
}
