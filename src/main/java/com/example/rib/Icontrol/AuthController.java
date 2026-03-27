package com.example.rib.Icontrol;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("/auth/register")
    public void signUp(){}

    @PostMapping("/auth/verify-otp")
    public void verify(){}

    @PostMapping("/auth/login")
    public void login(){}
}
