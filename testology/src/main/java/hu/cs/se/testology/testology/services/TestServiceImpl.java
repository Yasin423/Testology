package hu.cs.se.testology.testology.services;

import hu.cs.se.testology.testology.model.Test;
import hu.cs.se.testology.testology.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private TestRepository testRepository;


    public Object findAll() {

        return testRepository.findAll();
    }

    public void saveTest(Test test) {
        testRepository.save(test);
    }

    @Override
    public Test findTestById(Long id) {
        return testRepository.getOne(id);
    }

    @Override
    public void deleteTestById(Long id) {
        testRepository.deleteById(id);
    }
}
