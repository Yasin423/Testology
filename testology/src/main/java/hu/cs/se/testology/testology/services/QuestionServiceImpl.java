package hu.cs.se.testology.testology.services;

import hu.cs.se.testology.testology.model.Class;
import hu.cs.se.testology.testology.model.Question;
import hu.cs.se.testology.testology.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question findQuestionByID(Long id) {
        return questionRepository.getOne(id);
    }

    @Override
    public void deleteByID(Long id) {
        questionRepository.deleteById(id);
    }


}
