package hu.cs.se.testology.testology.repositories;

import hu.cs.se.testology.testology.model.Question;
import hu.cs.se.testology.testology.model.QuestionAnswer;
import hu.cs.se.testology.testology.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {

    List<QuestionAnswer> findAllByUser(User user);

    QuestionAnswer findByQuestion(Question question);

    QuestionAnswer findByQuestionAndUser(Question question , User user);

}
