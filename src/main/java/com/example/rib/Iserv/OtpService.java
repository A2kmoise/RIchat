package com.example.rib.Iserv;

import com.example.rib.Iconf.OtpGenerator;
import com.example.rib.Imodel.Otp;
import com.example.rib.Irepo.OtpRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class OtpService {
    private final OtpRepository otpRepository;
    private final OtpGenerator otpGenerator;
    private final JavaMailSender javaMailSender;

    public OtpService(OtpRepository otpRepository,
                      OtpGenerator otpGenerator,
                      JavaMailSender javaMailSender){
        this.otpRepository = otpRepository;
        this.otpGenerator = otpGenerator;
        this.javaMailSender = javaMailSender;
    }

    public String generateSaveOtpAndSend(String email){

        otpRepository.deleteByEmail(email);
        String otpValue = otpGenerator.generateOtp();

        Otp otp = new Otp();
        otp.setEmail(email);
        otp.setOtp(otpValue);
        otp.setCreatedAt(Instant.now());
        otp.setExpiresAt(Instant.now().plusSeconds(300));

        otpRepository.save(otp);

        return otpValue;
    }

    public void sendOtp(String email, String otpValue){
      SimpleMailMessage message = new SimpleMailMessage();
      message.setTo(email);
      message.setSubject("Your OTP code");
      message.setText("Your OTP is: " + otpValue);
      javaMailSender.send(message);
    }

    public boolean verifyOtp(String email,String otpInput){
         Otp otp = otpRepository.findTopByEmailOrderByCreatedAtDesc(email)
        .orElseThrow(()-> new RuntimeException("Otp not exist"));
// check if its used
     if(otp.isUsed()){
     throw new RuntimeException("Otp is used");
      }
        // check expiration
        if (otp.getExpiresAt().isBefore(Instant.now())) {
            throw new RuntimeException("OTP expired");
        }

        // check match
        if (!otp.getOtp().equals(otpInput)) {
            throw new RuntimeException("Invalid OTP");
        }

        // mark as used
        otp.setUsed(true);
        otpRepository.save(otp);

        return true;
    }

}
