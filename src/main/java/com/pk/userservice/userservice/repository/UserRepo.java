package com.pk.userservice.userservice.repository;

import com.pk.userservice.userservice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
    Users save(Users user);
    Optional<Users> findByEmail(String email);
}
