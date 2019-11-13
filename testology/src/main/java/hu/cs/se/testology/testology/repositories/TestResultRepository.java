package hu.cs.se.testology.testology.repositories;

import hu.cs.se.testology.testology.model.*;
import hu.cs.se.testology.testology.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult , Long> {

    List<ClassRegistration> findAllByStudent(User student);
    List<ClassRegistration> findAllByTest(Test test);
}
