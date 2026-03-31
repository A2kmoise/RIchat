package com.example.rib.Iserv;

import com.example.rib.Idto.RegisterRequest;
import com.example.rib.Irepo.UsersRepository;

public class AuthService {

private final UsersRepository usersRepository;

public AuthService(UsersRepository usersRepository){
    this.usersRepository = usersRepository;
}

void register(RegisterRequest registerRequest){
    if(usersRepository.existsUserByEmail(registerRequest.getEmail())){
        throw new RuntimeException("User already exists");
    }

}
}
