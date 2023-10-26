package com.example.authentication;

import com.example.entity.SignUp;
import com.example.repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private SignUpRepository signUpRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SignUp signUpData = signUpRepository.findBySignUpUserName(username);
        System.out.println(signUpData.getSignUpUserName());
        System.out.println(signUpData.getSignUpPassword());
        if(signUpData==null){
            throw new UsernameNotFoundException("userName not found !!!!!!!!");
        }
        return

                new org.springframework.security.core.userdetails.User(
                signUpData.getSignUpUserName(),
                signUpData.getSignUpPassword(),
                // Add user roles and authorities here if you have them
                // Collections.emptyList() for no roles and authorities
                Collections.emptyList()
        );
    }
}
