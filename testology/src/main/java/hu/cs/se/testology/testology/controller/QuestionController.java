package hu.cs.se.testology.testology.controller;

import hu.cs.se.testology.testology.model.Question;
import hu.cs.se.testology.testology.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    public void saveQuestion(Question question){
        questionService.save(question);
    }

}
