package hu.cs.se.testology.testology.services;

import hu.cs.se.testology.testology.model.Question;
import hu.cs.se.testology.testology.model.QuestionAnswer;
import hu.cs.se.testology.testology.model.User;
import hu.cs.se.testology.testology.repositories.QuestionAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionAnswerServiceImpl implements QuestionAnswerService{

    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    @Override
    public void save(QuestionAnswer questionAnswer) {
        questionAnswerRepository.save(questionAnswer);
    }

    @Override
    public List<QuestionAnswer> findAll() {
        return questionAnswerRepository.findAll();
    }

    @Override
    public QuestionAnswer findQuestionAnswerByID(Long id) {
        return questionAnswerRepository.getOne(id);
    }

    @Override
    public List<QuestionAnswer> findAllByUser(User user) {
        return questionAnswerRepository.findAllByUser(user);
    }

    @Override
    public QuestionAnswer findByQuestionAndUser(Question question, User user) {
        return questionAnswerRepository.findByQuestionAndUser(question , user);
    }

}
