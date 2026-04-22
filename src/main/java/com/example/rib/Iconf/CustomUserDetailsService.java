package com.example.rib.Iconf;

import com.example.rib.Imodel.User;
import com.example.rib.Irepo.UsersRepository;
import lombok.NonNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    public CustomUserDetailsService(UsersRepository usersRepository){
        this.usersRepository =usersRepository;
    }

    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
     User user = usersRepository.findByEmail(email)
             .orElseThrow(() -> new UsernameNotFoundException("user with this email not found "));

     return  new org.springframework.security.core.userdetails.User(
             user.getEmail(),
             user.getPassword(),
             Collections.singletonList(new SimpleGrantedAuthority("ROLE_"))
     );
    }
}
