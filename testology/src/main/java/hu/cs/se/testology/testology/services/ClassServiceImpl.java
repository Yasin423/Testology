package hu.cs.se.testology.testology.services;

import hu.cs.se.testology.testology.model.Class;
import hu.cs.se.testology.testology.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl  implements ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Override
    public void save(Class aClass) {
        classRepository.save(aClass);
    }

    @Override
    public List<Class> findAll() {
        return classRepository.findAll();
    }

    @Override
    public Class findClassByID(Long id) {
        return classRepository.getOne(id);
    }

    @Override
    public void deleteByID(Long id) {
        classRepository.deleteById(id);
    }

    @Override
    public Class findByAccessCode(String accessCode) {
        return classRepository.findByAccessCode(accessCode);
    }


}
