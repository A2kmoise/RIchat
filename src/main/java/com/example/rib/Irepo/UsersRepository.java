package com.example.rib.Irepo;

import com.example.rib.Imodel.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
   boolean existsUserByEmail(String email);

}
