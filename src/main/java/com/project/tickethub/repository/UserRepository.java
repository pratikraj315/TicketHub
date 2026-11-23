
package com.project.tickethub.repository;

import com.project.tickethub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}

//SELECT * FROM users WHERE email = ?;