package hu.cs.se.testology.testology.services;

import hu.cs.se.testology.testology.model.Class;
import hu.cs.se.testology.testology.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl  implements ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Override
    public void save(Class aClass) {
        classRepository.save(aClass);
    }

    @Override
    public Object findAllClasses() {
        return classRepository.findAll();
    }

    @Override
    public Class editClassById(Long id) {

        return classRepository.getOne(id);
    }

    public void deleteById(Long id) {
        classRepository.deleteById(id);
    }
}
