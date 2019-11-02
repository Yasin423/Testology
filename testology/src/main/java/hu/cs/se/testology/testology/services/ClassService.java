package hu.cs.se.testology.testology.services;

import hu.cs.se.testology.testology.model.Class;

import java.util.List;

public interface ClassService {
    void save(Class aClass);

    List<Class> findAll();

    Class findClassByID(Long id);

    void deleteByID(Long id);
}
