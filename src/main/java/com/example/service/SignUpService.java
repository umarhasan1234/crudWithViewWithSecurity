package com.example.service;

import com.example.request.SignUpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface SignUpService {

    public void saveSignUpData(SignUpRequest signUpRequest);
}
