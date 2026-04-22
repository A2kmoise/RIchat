package com.example.rib.Icontrol;

import org.springframework.web.bind.annotation.*;

@RestController("/richat-api/v1/profile")
public class ProfileController {

    @PutMapping("/update-profile")
    public void updateProfile(){}

    @PutMapping("/update-password")
    public void updatePassword(){}
}
