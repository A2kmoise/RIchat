package com.example.rib.Iserv;

import com.example.rib.Idto.LoginRequest;
import com.example.rib.Idto.RegisterRequest;
import com.example.rib.Irepo.UsersRepository;
import com.example.rib.Imodel.User;
import com.example.rib.Iconf.TokenGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;

    public AuthService(UsersRepository usersRepository,
                       PasswordEncoder passwordEncoder,
                       TokenGenerator tokenGenerator){
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
    }

    public String register(RegisterRequest registerRequest){

        // check if user exists
        if(usersRepository.existsUserByEmail(registerRequest.getEmail())){
            throw new RuntimeException("User already exists");
        }

        // create new user
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        // save user
        usersRepository.save(user);

        return tokenGenerator.tokenGenerate(user.getEmail());
    }

    public String login(LoginRequest loginRequest){
        User user = Optional.ofNullable(usersRepository.findByEmail(loginRequest.getEmail()))
                .orElseThrow(() -> new RuntimeException("User doesn't exist"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return tokenGenerator.tokenGenerate(user.getEmail());
    }
}