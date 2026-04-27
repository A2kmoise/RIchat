package com.example.rib.Idto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;


@Data
@NoArgsConstructor
public class LoginRequest {

    @NonNull
    private String email;

    @NonNull
    private String password;

    public LoginRequest(@NonNull String email, @NonNull String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
