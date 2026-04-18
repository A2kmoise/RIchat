package com.example.rib.Iconf;

import java.security.SecureRandom;

public class OtpGenerator {
private static final SecureRandom secure = new SecureRandom();

public String generateOtp(){
    int otp = 100000 + secure.nextInt(900000);
    return String.valueOf(otp);
}
}
