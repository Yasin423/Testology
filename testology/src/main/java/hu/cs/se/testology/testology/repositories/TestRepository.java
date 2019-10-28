package hu.cs.se.testology.testology.repositories;

import hu.cs.se.testology.testology.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test,Long> {
}
