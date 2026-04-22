package com.example.rib.Idto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NonNull;

@Data
@Getter
@Setter
public class RegisterRequest {

    private String email;

    @NonNull
    private String username;

    @NonNull
    private String password;

    public RegisterRequest(String email, @NonNull String username, @NonNull String password){
        this.email = email;
        this.username = username;
        this.password = password;
    }

}
