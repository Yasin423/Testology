package hu.cs.se.testology.testology.services;

import hu.cs.se.testology.testology.model.Class;
import hu.cs.se.testology.testology.model.Question;

import java.util.List;

public interface QuestionService {

    void save(Question question);

    List<Question> findAll();

    Question findQuestionByID(Long id);

    void deleteByID(Long id);
}
