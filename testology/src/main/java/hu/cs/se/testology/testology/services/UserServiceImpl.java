package hu.cs.se.testology.testology.services;

import hu.cs.se.testology.testology.model.User;
import hu.cs.se.testology.testology.repositories.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private usersRepository usersRepository;

    @Override
    public void save(User user) {
        usersRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public User findUserByID(Long id) {
        return usersRepository.getOne(id);
    }

    @Override
    public void deleteUserByID(Long id) {
        usersRepository.deleteById(id);
    }
}
