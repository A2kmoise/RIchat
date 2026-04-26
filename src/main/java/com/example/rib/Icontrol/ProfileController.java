package com.example.rib.Icontrol;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/richat-api/v1/profile")
public class ProfileController {

    @PutMapping("/profile")
    public void updateProfile(){}

    @PutMapping("/password")
    public void updatePassword(){}
}
