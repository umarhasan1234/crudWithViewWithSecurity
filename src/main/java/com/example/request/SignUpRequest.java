package com.example.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private long singUpId;
    private String userNameOrEmail;

    private String signUpPassword;


}
