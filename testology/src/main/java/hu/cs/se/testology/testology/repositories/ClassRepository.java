package hu.cs.se.testology.testology.repositories;

import hu.cs.se.testology.testology.model.Class;
import hu.cs.se.testology.testology.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

    Class findByAccessCode(String accessCode);

    List<Class> getAllByTeacher(User teacher);

}
