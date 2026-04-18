package com.example.rib.Icontrol;

import com.example.rib.Idto.LoginRequest;
import com.example.rib.Idto.RegisterRequest;
import com.example.rib.Iserv.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth") // base path
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // REGISTER
    @PostMapping("/register")
    public String signUp(@RequestBody RegisterRequest registerRequest){
        return authService.register(registerRequest);
    }

    // VERIFY OTP
    @PostMapping("/verify-otp")
    public String verify(@RequestParam String otp){
        return authService.verifyOtp();
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
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
