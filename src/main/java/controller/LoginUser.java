package controller;

import com.example.authentication.CustomUserDetailsService;
import com.example.authentication.JwtUtill;
import com.example.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/login")
public class LoginUser {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtill jwtUtill;

    @GetMapping("/")
    public ModelAndView loginUser(ModelAndView modelAndView){
       modelAndView.setViewName("/views/login");
        return modelAndView;
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ModelAndView generateToken(@ModelAttribute("loginRequest")LoginRequest loginRequest,ModelAndView modelAndView) throws Exception {
        System.out.println(loginRequest.getUserNameOrEmail());
        System.out.println(loginRequest.getLoginPassword());

        try{
            System.out.println("try ke andar wala");
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserNameOrEmail(),loginRequest.getLoginPassword()));
            System.out.println("try ke niche wala ");

        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("User name not found");
        }
        System.out.println("custom ko call krne se pahale");
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(loginRequest.getUserNameOrEmail());
        System.out.println("custom ko call krte time ");
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getPassword());

        System.out.println("token for this user\n");
        String token = this.jwtUtill.generateToken(userDetails);
        System.out.println(token);
modelAndView.setViewName("/views/findDetails");
return modelAndView;
       // return new ResponseEntity<>("token generate successfully", HttpStatus.ACCEPTED);
    }
}
