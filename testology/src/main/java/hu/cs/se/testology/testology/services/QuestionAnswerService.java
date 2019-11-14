package hu.cs.se.testology.testology.services;

import hu.cs.se.testology.testology.model.Question;
import hu.cs.se.testology.testology.model.QuestionAnswer;
import hu.cs.se.testology.testology.model.User;

import java.util.List;

public interface QuestionAnswerService {

    void save(QuestionAnswer questionAnswer);

    List<QuestionAnswer> findAll();

    QuestionAnswer findQuestionAnswerByID(Long id);

    List<QuestionAnswer> findAllByUser(User user);

    QuestionAnswer findByQuestionAndUser(Question question , User user);

}
