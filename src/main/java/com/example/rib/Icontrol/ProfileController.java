package com.example.rib.Icontrol;

import com.example.rib.Iserv.ProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/richat/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @PutMapping("/profile")
    public void updateProfile(
            @RequestParam String userId,
            @RequestParam String newName
    ){
        profileService.updateProfile(userId, newName);
    }

    @PutMapping("/password")
    public void updatePassword(
            @RequestParam String userId,
            @RequestParam String newPassword
    ){
        profileService.updatePassword(userId, newPassword);
    }
}
