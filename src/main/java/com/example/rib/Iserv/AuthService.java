package com.example.rib.Iserv;

import com.example.rib.Idto.LoginRequest;
import com.example.rib.Idto.RegisterRequest;
import com.example.rib.Irepo.UsersRepository;
import com.example.rib.Imodel.User;
import com.example.rib.Iconf.TokenGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final UsersRepository usersRepository;
    private final OtpService otpService;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;

    public AuthService(UsersRepository usersRepository,
                       OtpService otpService,
                       PasswordEncoder passwordEncoder,
                       TokenGenerator tokenGenerator
                      ){
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
        this.otpService = otpService;
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
        user.setVerified(false);

        // save user
        usersRepository.save(user);

      //generate otp and save otp
      String otp = otpService.generateSaveOtpAndSend(user.getEmail());

      //send otp
        otpService.sendOtp(user.getEmail(), otp);

        return "Otp sent via email";
    }

    public void verifyEmail(String email, String otp){
        User user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not exist"));

        user.setVerified(true);
    }
    public boolean verifyOtp(String email, String otp){
         return  otpService.verifyOtp(email, otp);

    }

    public String login(LoginRequest loginRequest){
        User user = usersRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User doesn't exist"));

        if(!user.isVerified()){
            throw new RuntimeException("user not verified");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return tokenGenerator.tokenGenerate(user.getEmail());
    }

    public String resetPassword(String email, String otp, String newPassword){


        User user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("user doesn't exist"));
        if(!otpService.verifyOtp(email, otp)){
            throw new RuntimeException("OTP invalid or expired");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        usersRepository.save(user);

        return "password reset success";
    }

    public User me(String email){
return usersRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("user not found"));
    }
}