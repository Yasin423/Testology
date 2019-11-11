package hu.cs.se.testology.testology.services;

import hu.cs.se.testology.testology.model.Class;
import hu.cs.se.testology.testology.model.User;

import java.util.List;

public interface UserService {

    void save(User user);

    List<User> findAll();

    User findUserByID(Long id);

    void deleteUserByID(Long id);

    User findByUsername(String username);
}
