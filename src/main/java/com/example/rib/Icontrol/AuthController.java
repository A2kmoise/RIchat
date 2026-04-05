package com.example.rib.Icontrol;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth") // base path
public class AuthController {

    // REGISTER
    @PostMapping("/register")
    public String signUp(@RequestBody String body){
        return "User registered";
    }

    // VERIFY OTP
    @PostMapping("/verify-otp")
    public String verify(@RequestParam String otp){
        return "OTP verified";
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody String body){
        return "User logged in";
    }

    // RESEND OTP
    @PostMapping("/resend-otp")
    public String resendOtp(@RequestParam String email){
        return "OTP resent";
    }

    // FORGOT PASSWORD
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email){
        return "Password reset link sent";
    }

    // RESET PASSWORD
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token,
                                @RequestParam String newPassword){
        return "Password reset successful";
    }

    // LOGOUT
    @PostMapping("/logout")
    public String logout(){
        return "User logged out";
    }

    // GET CURRENT USER (example protected endpoint)
    @GetMapping("/me")
    public String currentUser(){
        return "Current user details";
    }
}