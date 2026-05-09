package com.example.rib.Icontrol;

import com.example.rib.Iconf.TokenGenerator;
import com.example.rib.Idto.LoginRequest;
import com.example.rib.Idto.RegisterRequest;
import com.example.rib.Imodel.User;
import com.example.rib.Iserv.AuthService;
import com.example.rib.Iserv.OtpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@Tag(name = "authentication")
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
    @Operation(summary = "user registration", description = "User registers with email and password")
    @PostMapping("/register")
    public String signUp(@RequestBody RegisterRequest registerRequest){
        return authService.register(registerRequest);
    }

    // VERIFY OTP
    @Operation(summary = "verification of otp", description = "verify otp after restering")
    @PostMapping("/verify-otp")
    public boolean verify(@RequestParam String email,@RequestParam String otp){
        return authService.verifyOtp(email, otp);
    }

    // LOGIN
    @Operation(summary = "login with email and password", description = "User login with email and password ")
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    // RESEND OTP
    @Operation(summary = "Resending otp", description = "Just in case otp not got you cna resend another")
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
    @Operation(summary = "reset password", description = "email with OTP sent verify it the reset your password")
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
    @Operation(summary = "real api to write new password", description = "here you type new password after otp verification and your password resets")
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email,
                                @RequestParam String otp,
                                @RequestParam String newPassword){

        return authService.resetPassword(email, otp, newPassword);
    }

    // LOGOUT
    @Operation(summary = "just dummy logout", description = "Could be implemented when keeping otp in cookies to delete it when user logs out")
    @PostMapping("/logout")
    public String logout(){
        return "User logged out";
    }

    // GET CURRENT USER (example protected endpoint)
    @Operation(summary = "User descriptive api", description = "Profile api to return user's data")
    @GetMapping("/me")
    public User currentUser(@RequestHeader("Authorisation") String header){
        String token = header.substring(7); //Bearer removed

        String email = tokenGenerator.extractEmail(token);
        return authService.me(email);
    }

}