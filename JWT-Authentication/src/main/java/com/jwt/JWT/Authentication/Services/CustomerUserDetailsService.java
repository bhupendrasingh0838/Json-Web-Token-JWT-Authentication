package com.jwt.JWT.Authentication.Services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CustomerUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (userName.equals("Bhupendra")){
            return new User("Bhupendra","bhupendra",new ArrayList<>());
        }
        else {
            throw new UsernameNotFoundException("User not found Exception");
        }


    }
}
