package controller;

import com.example.authentication.CustomUserDetailsService;
import com.example.authentication.JwtUtill;
import com.example.request.LoginRequest;
import com.example.responce.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

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


//    private final AuthenticationManager authenticationManager;
//    private final CustomUserDetailsService customUserDetailsService;
//    private final JwtUtill jwtUtill;
//
//    // Inject necessary dependencies using constructor injection
//    public LoginUser(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, JwtUtill jwtUtill) {
//        this.authenticationManager = authenticationManager;
//        this.customUserDetailsService = customUserDetailsService;
//        this.jwtUtill = jwtUtill;
//    }


    @GetMapping("/login/")
    public ModelAndView loginUser(ModelAndView modelAndView){

        System.out.println("login user call");
       modelAndView.setViewName("/views/login");
        return modelAndView;
    }


    @RequestMapping(value = "/login/token",method = RequestMethod.POST)
        public ModelAndView generatesdsfsdddedfToken(@RequestBody LoginRequest loginRequest, HttpServletResponse response, ModelAndView modelAndView) throws Exception {
        System.out.println("user name is -"+loginRequest.getUserNameOrEmail());
        System.out.println("password is -"+loginRequest.getLoginPassword());

        try {

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserNameOrEmail(), loginRequest.getLoginPassword()));

        }

        catch (Exception e){
            e.printStackTrace();
            throw new Exception("User name not found");
        }
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(loginRequest.getUserNameOrEmail());

        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getPassword());

        System.out.println("token for this user\n");
        token= this.jwtUtill.generateToken(userDetails);
        System.out.println(token);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);

        // Set the token in the response
        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", JWT_COOKIE_NAME + "=" + token);


        modelAndView.setViewName("/views/index");
        return modelAndView;
    //    return new ResponseEntity<>("token generate successfully", HttpStatus.ACCEPTED);

 //  return ResponseEntity.ok(new LoginResponse(token));
    }


}
