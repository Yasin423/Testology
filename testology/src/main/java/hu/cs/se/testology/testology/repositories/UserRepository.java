package hu.cs.se.testology.testology.repositories;

import hu.cs.se.testology.testology.model.Test;
import hu.cs.se.testology.testology.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
