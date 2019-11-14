package hu.cs.se.testology.testology.services;

import hu.cs.se.testology.testology.model.Test;
import hu.cs.se.testology.testology.model.TestResult;
import hu.cs.se.testology.testology.model.User;

import java.util.List;

public interface TestResultService {

    void save(TestResult testResult);

    List<TestResult> findAll();

    TestResult findTestResultByID(Long id);

    List<TestResult> findAllByStudent(User student);

    List<TestResult> findAllByTest(Test test);

    TestResult findByTest(Test test);
}
