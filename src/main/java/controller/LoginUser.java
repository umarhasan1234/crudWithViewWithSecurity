package controller;

import com.example.authentication.CustomUserDetailsService;
import com.example.authentication.JwtUtill;
import com.example.request.LoginRequest;
import com.example.responce.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.web.server.Cookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
@RestController
@RequestMapping("/login")
public class LoginUser {

    public String token;
    private static final String JWT_COOKIE_NAME = "jwtToken";
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtill jwtUtill;

    @GetMapping("/")
    public ModelAndView loginUser(ModelAndView modelAndView){

        System.out.println("login user call");
       modelAndView.setViewName("/views/login");
        return modelAndView;
    }


    @RequestMapping(value = "/token",method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginRequest, HttpServletResponse response) throws Exception {
        System.out.println(loginRequest.getUserNameOrEmail());
        System.out.println(loginRequest.getLoginPassword());

        try{

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserNameOrEmail(),loginRequest.getLoginPassword()));


        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("User name not found");
        }

        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(loginRequest.getUserNameOrEmail());

        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getPassword());

        System.out.println("token for this user\n");
        token= this.jwtUtill.generateToken(userDetails);
        System.out.println(token);

        LoginResponse loginResponse=new LoginResponse();
        loginResponse.setToken(token);


    //    modelAndView.setViewName("/views/index");

       // return new ResponseEntity<>("token generate successfully", HttpStatus.ACCEPTED);

   return ResponseEntity.ok(new LoginResponse(token));
    }


}
