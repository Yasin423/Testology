package hu.cs.se.testology.testology.services;

import hu.cs.se.testology.testology.repositories.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private usersRepository usersRepository;
}
