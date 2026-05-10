package com.example.rib.Idto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.NonNull;

@Data
@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Email is required")
    private String email;

    @NonNull
    @NotBlank(message = "Username is required")
    private String username;

    @NonNull
    @NotBlank(message = "Password is required")
    @Size(max = 6, message = "Password be at least 6 characters")
    private String password;

    public RegisterRequest(String email, @NonNull String username, @NonNull String password){
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
