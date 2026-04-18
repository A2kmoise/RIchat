package com.example.rib.Iserv;

import com.example.rib.Iconf.OtpGenerator;
import com.example.rib.Imodel.Otp;
import com.example.rib.Irepo.OtpRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class OtpService {
    private final OtpRepository otpRepository;
    private final OtpGenerator otpGenerator;

    public OtpService(OtpRepository otpRepository,
                      OtpGenerator otpGenerator){
        this.otpRepository = otpRepository;
        this.otpGenerator = otpGenerator;
    }

    public String generateAndSaveOtp(String email){
        String otpValue = otpGenerator.generateOtp();

        Otp otp = new Otp();
        otp.setEmail(email);
        otp.setOtp(otpValue);
        otp.setCreatedAt(Instant.now());
        otp.setExpiresAt(Instant.now().plusSeconds(300));

        otpRepository.save(otp);

        return otpValue;
    }

    public void sendOtp(String email, String otp ){
    }
}
