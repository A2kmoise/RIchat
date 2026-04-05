package com.example.rib.Iserv;

import com.example.rib.Idto.RegisterRequest;
import com.example.rib.Irepo.UsersRepository;
import com.example.rib.Imodel.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsersRepository usersRepository,
                       PasswordEncoder passwordEncoder){
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterRequest registerRequest){

        // check if user exists
        if(usersRepository.existsUserByEmail(registerRequest.getEmail())){
            throw new RuntimeException("User already exists");
        }

        // create new user
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        // save user
        usersRepository.save(user);

        return "User registered successfully";
    }
}