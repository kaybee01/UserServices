package org.scaler.springsecuritytest.Repository;

import org.scaler.springsecuritytest.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User save(User u);
    Optional<User> findByEmail(String email);
}
