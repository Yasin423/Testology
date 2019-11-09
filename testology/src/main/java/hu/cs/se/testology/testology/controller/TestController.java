package hu.cs.se.testology.testology.controller;

import hu.cs.se.testology.testology.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.jws.WebParam;

@Controller
public class TestController {

//    @Autowired
//    private ClassRepository classRepository;
//
//    @GetMapping("/test/create")
//    public String renderCreateTestPage(Model model){
//
//        model.addAttribute("classes" , classRepository.findAll());
//
//        return "tess/createTest";
//    }
//
//    @PostMapping("test/create")
//    public String createTest(){
//
//        return "redirect:/test/list";
//    }
}
