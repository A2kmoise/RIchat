package com.example.rib.Idto;

import lombok.Data;
import org.jspecify.annotations.NonNull;


@Data
public class LoginRequest {

    @NonNull
    private String email;

    @NonNull
    private String password;

    public LoginRequest(@NonNull String email, @NonNull String password){
        this.email = email;
        this.password = password;
    }


}
