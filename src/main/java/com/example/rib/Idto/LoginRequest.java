package com.example.rib.Idto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class LoginRequest {

    @Setter
    @Getter
    @NotNull
    private String email;

    @Setter
    @Getter
    @NotNull
    private String password;

    public LoginRequest(String email, String password){
        this.email = email;
        this.password = password;
    }


}
