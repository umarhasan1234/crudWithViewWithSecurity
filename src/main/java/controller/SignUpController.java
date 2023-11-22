package controller;

import com.example.request.SignUpRequest;
import com.example.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/signUp")
public class SignUpController {

    @Autowired(required = true)
    private SignUpService signUpService;

    @PostMapping("/")
    public ResponseEntity<String> saveUser(@RequestBody SignUpRequest signUpRequest){

        signUpService.saveSignUpData(signUpRequest);
        return  new ResponseEntity<String>("Sign up  successfull", HttpStatus.OK);
    }

    @GetMapping("/")
    public ModelAndView signUpUser(ModelAndView modelAndView){
        modelAndView.setViewName("/views/signUpForm");
        return modelAndView;
    }
    @RequestMapping("/save")
    public ModelAndView saveSignUpUser(@ModelAttribute("signUpRequest") SignUpRequest signUpRequest,ModelAndView modelAndView){
        signUpService.saveSignUpData(signUpRequest);
        modelAndView.setViewName("/views/signUpForm");
        return modelAndView;
    }

}
