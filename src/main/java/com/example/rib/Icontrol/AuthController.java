package com.example.rib.Icontrol;

import com.example.rib.Iconf.TokenGenerator;
import com.example.rib.Idto.LoginRequest;
import com.example.rib.Idto.RegisterRequest;
import com.example.rib.Imodel.User;
import com.example.rib.Iserv.AuthService;
import com.example.rib.Iserv.OtpService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/richat/api/v1/auth") // base path
public class AuthController {

    private final AuthService authService;
    private final OtpService otpService;
    private final JavaMailSender javaMailSender;
    private final TokenGenerator tokenGenerator;

    public AuthController(AuthService authService, OtpService otpService, JavaMailSender javaMailSender, TokenGenerator tokenGenerator) {
        this.authService = authService;
        this.otpService = otpService;
        this.javaMailSender = javaMailSender;
        this.tokenGenerator = tokenGenerator;
    }

    // REGISTER
    @PostMapping("/register")
    public String signUp(@RequestBody RegisterRequest registerRequest){
        return authService.register(registerRequest);
    }

    // VERIFY OTP
    @PostMapping("/verify-otp")
    public boolean verify(@RequestParam String email,@RequestParam String otp){
        return authService.verifyOtp(email, otp);
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    // RESEND OTP
    @PostMapping("/resend-otp")
    public String resendOtp(@RequestParam String email){
        String otp = otpService.generateSaveOtpAndSend(email);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("OTP RESENT");
        message.setText("Your OTP: " + otp);
        javaMailSender.send(message);
        return "OTP resent successfully";
    }

    // FORGOT PASSWORD
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email){
        String otp = otpService.generateSaveOtpAndSend(email);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password reset OTP");
        message.setText("Your OTP for password reset: " + otp);
        javaMailSender.send(message);

        return "Password reset otp sent";
        //Will use link method next

    }

    // RESET PASSWORD
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email,
                                @RequestParam String otp,
                                @RequestParam String newPassword){

        return authService.resetPassword(email, otp, newPassword);
    }

    // LOGOUT
    @PostMapping("/logout")
    public String logout(){
        return "User logged out";
    }

    // GET CURRENT USER (example protected endpoint)
    @GetMapping("/me")
    public User currentUser(@RequestHeader("Authorisation") String header){
        String token = header.substring(7); //Bearer removed

        String email = tokenGenerator.extractEmail(token);
        return authService.me(email);
    }

}