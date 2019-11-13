package hu.cs.se.testology.testology.repositories;

import hu.cs.se.testology.testology.model.Class;
import hu.cs.se.testology.testology.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test,Long> {

    List<Test> findAllByAClass(Class aClass);
}
