package hu.cs.se.testology.testology.controller;

import hu.cs.se.testology.testology.model.Question;
import hu.cs.se.testology.testology.model.Test;
import hu.cs.se.testology.testology.services.QuestionService;
import hu.cs.se.testology.testology.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private TestService testService;

    @PostMapping("/question/create/{testID}")
    public String saveQuestion(@ModelAttribute Question question , @PathVariable Long testID){

        Test test = testService.findTestById(testID);

        question.setTest(test);

        questionService.save(question);

        return "redirect:/test/view/" + testID;

    }

}
