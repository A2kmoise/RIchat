package com.example.rib.Irepo;

import com.example.rib.Imodel.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, String> {
    Optional<Otp> findTopByEmailOrderByCreatedAtDesc(String email);

    void deleteByEmail(String email);
}
