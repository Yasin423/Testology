package hu.cs.se.testology.testology.services;

import hu.cs.se.testology.testology.model.Test;
import hu.cs.se.testology.testology.model.TestResult;
import hu.cs.se.testology.testology.model.User;
import hu.cs.se.testology.testology.repositories.TestRepository;
import hu.cs.se.testology.testology.repositories.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestResultServiceImpl implements TestResultService{

    @Autowired
    private TestResultRepository testResultRepository;

    @Override
    public void save(TestResult testResult) {
        testResultRepository.save(testResult);
    }

    @Override
    public List<TestResult> findAll() {
        return testResultRepository.findAll();
    }

    @Override
    public TestResult findTestResultByID(Long id) {
        return testResultRepository.getOne(id);
    }

    @Override
    public List<TestResult> findAllByStudent(User student) {
        return testResultRepository.findAllByStudent(student);
    }

    @Override
    public List<TestResult> findAllByTest(Test test) {
        return testResultRepository.findAllByTest(test);
    }

    @Override
    public TestResult findByTest(Test test) {
        return testResultRepository.findByTest(test);
    }
}
