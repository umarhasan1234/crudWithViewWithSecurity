package com.example.authentication;

import com.example.entity.Login;
import com.example.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private LoginRepository signUpRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login loginData = signUpRepository.findBySignUpUserName(username);
        System.out.println(loginData.getSignUpUserName());
        System.out.println(loginData.getSignUpPassword());
        if(loginData==null){
            throw new UsernameNotFoundException("userName not found !!!!!!!!");
        }
        return

                new org.springframework.security.core.userdetails.User(
                        loginData.getSignUpUserName(),
                        loginData.getSignUpPassword(),
                // Add user roles and authorities here if you have them
                // Collections.emptyList() for no roles and authorities
                Collections.emptyList()
        );
    }
}
