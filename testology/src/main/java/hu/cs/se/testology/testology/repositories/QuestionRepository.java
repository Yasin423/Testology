package hu.cs.se.testology.testology.repositories;

import hu.cs.se.testology.testology.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question , Long> {
}
