package hu.cs.se.testology.testology.repositories;

import hu.cs.se.testology.testology.model.Question;
import hu.cs.se.testology.testology.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question , Long> {

    List<Question> findAllByTest(Test test);
}
