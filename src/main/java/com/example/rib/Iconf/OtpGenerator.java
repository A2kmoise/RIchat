package com.example.rib.Iconf;

import com.example.rib.Irepo.UsersRepository;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class OtpGenerator {
private static final SecureRandom secure = new SecureRandom();
    private final UsersRepository usersRepository;

    public OtpGenerator(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public String generateOtp(){
    int otp = 100000 + secure.nextInt(900000);
    return String.valueOf(otp);
}
}
