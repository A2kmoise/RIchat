package com.example.rib.Icontrol;

import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {

    @PutMapping("/update-profile")
    public void updateProfile(){}

    @PutMapping("/update-password")
    public void updatePassword(){}
}
