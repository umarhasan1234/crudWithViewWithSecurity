package controller;

import com.example.authentication.CustomUserDetailsService;
import com.example.authentication.JwtUtill;
import com.example.request.LoginRequest;
import com.example.responce.LoginResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


//import com.example.authentication.CustomUserDetailsService;
//import com.example.authentication.JwtUtill;
//import com.example.request.LoginRequest;
//import com.example.responce.LoginResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginUser {

    public String token;
    private static final String JWT_COOKIE_NAME = "jwtToken";
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtill jwtUtill;




    @GetMapping("/login/")
    public ModelAndView loginUser(ModelAndView modelAndView){

       modelAndView.setViewName("/views/login");
        return modelAndView;
    }


    @RequestMapping(value = "/login/token", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserNameOrEmail(), loginRequest.getLoginPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: " + e.getMessage());
        }

        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(loginRequest.getUserNameOrEmail());

        token = this.jwtUtill.generateToken(userDetails);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);

        Cookie tokenCookie = new Cookie(JWT_COOKIE_NAME, token);
        tokenCookie.setMaxAge(24 * 60 * 60);
        tokenCookie.setPath("/");
        response.addCookie(tokenCookie);

        return ResponseEntity.ok(loginResponse);
    }

}
