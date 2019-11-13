package hu.cs.se.testology.testology.repositories;

import hu.cs.se.testology.testology.model.Class;
import hu.cs.se.testology.testology.model.ClassRegistration;
import hu.cs.se.testology.testology.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<ClassRegistration, Long> {

    List<ClassRegistration> findAllByAClass(Class aClass);

    ClassRegistration findByAClassAndUser(Class aClass , User student);

    ClassRegistration findByAClass(Class aClass);

    void deleteAllByAClass(Class aClass);

    List<ClassRegistration> findAllByUser(User student);



}
