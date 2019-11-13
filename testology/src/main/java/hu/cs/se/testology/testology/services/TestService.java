package hu.cs.se.testology.testology.services;

import hu.cs.se.testology.testology.model.Class;
import hu.cs.se.testology.testology.model.Test;

import java.util.List;

public interface TestService {

    void save(Test test);

    List<Test> findAll();

    Test findTestById(Long id);

    void deleteTestById(Long id);

    public List<Test> findAllByAClass(Class aClass);
}
