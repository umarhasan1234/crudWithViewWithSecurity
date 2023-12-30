package com.example.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginRequest {
    String userNameOrEmail;
    String loginPassword;

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getUserNameOrEmail() {
        return userNameOrEmail;
    }

    public void setUserNameOrEmail(String userNameOrEmail) {
        this.userNameOrEmail = userNameOrEmail;
    }
}
