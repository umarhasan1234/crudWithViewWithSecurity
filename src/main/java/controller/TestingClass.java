package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestingClass {

    @GetMapping("/test")
    public String test(){
        System.out.println("this is testing Class");
        return "run testing clas successfully";
    }
}
