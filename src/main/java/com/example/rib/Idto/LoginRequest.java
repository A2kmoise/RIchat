package com.example.rib.Idto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.NonNull;


@Data
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    @NonNull
    @NotBlank(message = "Email is required")
    private String email;

    @NonNull
    @NotBlank(message = "Password is required")
    @Size(max = 6, message = "password should be six characters")
    private String password;

    public LoginRequest(@NonNull String email, @NonNull String password){
        this.email = email;
        this.password = password;
    }
}
