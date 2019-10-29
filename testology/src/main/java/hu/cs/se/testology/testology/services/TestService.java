package hu.cs.se.testology.testology.services;

import hu.cs.se.testology.testology.model.Test;

public interface TestService {
    Test findTestById(Long id);

    void deleteTestById(Long id);
}
