package hu.cs.se.testology.testology.controller;

import hu.cs.se.testology.testology.model.Question;
import hu.cs.se.testology.testology.model.Test;
import hu.cs.se.testology.testology.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/question/create")
    public String saveQuestion(@ModelAttribute Question question , @ModelAttribute Test test){

        questionService.save(question);

        return "redirect:/test/list/";

    }

}
