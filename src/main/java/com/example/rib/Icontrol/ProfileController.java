package com.example.rib.Icontrol;

import com.example.rib.Iserv.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.*;

@Tag(name="profile")
@RestController
@RequestMapping("/richat/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @Operation(summary = "updating profile", description = "user can update profile i.e username")
    @PutMapping("/profile")
    public void updateProfile(
            @RequestParam String userId,
            @RequestParam String newName
    ){
        profileService.updateProfile(userId, newName);
    }

    @Operation(summary = "updating password", description = "user can update their password in case needed only password")
    @PutMapping("/password")
    public void updatePassword(
            @RequestParam String userId,
            @RequestParam String newPassword
    ){
        profileService.updatePassword(userId, newPassword);
    }
}
