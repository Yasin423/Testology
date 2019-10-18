package hu.cs.se.testology.testology.repositories;

import hu.cs.se.testology.testology.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
}
