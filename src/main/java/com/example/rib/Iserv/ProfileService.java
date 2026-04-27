package com.example.rib.Iserv;

import com.example.rib.Imodel.User;
import com.example.rib.Irepo.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileService(UsersRepository usersRepository, PasswordEncoder passwordEncoder){
          this.usersRepository = usersRepository;
          this.passwordEncoder = passwordEncoder;
    }

    public void updateProfile(String userId, String newName){

        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user doesn't exist"));

        user.setUsername(newName);

        usersRepository.save(user);
    }

    public void updatePassword(String userId, String newPassword){

        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user doesn't exist"));

        user.setPassword(passwordEncoder.encode(newPassword));

        usersRepository.save(user);
    }
}
