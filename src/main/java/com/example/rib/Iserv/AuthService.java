package com.example.rib.Iserv;

import com.example.rib.Irepo.UsersRepository;

public class AuthService {

private final UsersRepository usersRepository;

public AuthService(UsersRepository usersRepository){
    this.usersRepository = usersRepository;
}

void register(String email){
    if(usersRepository.existsUserByEmail(email)){
        throw new RuntimeException("User already exists");
    }
}
}
