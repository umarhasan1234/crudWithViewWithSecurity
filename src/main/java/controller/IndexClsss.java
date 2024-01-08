package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexClsss {
    @GetMapping("/index1")
    public ModelAndView ind(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/views/index");
        return modelAndView;

    }

//    @GetMapping("/index1")
//    public ResponseEntity<String> ind(){
//        System.out.println("index call");
//        return new ResponseEntity<>("successfull", HttpStatus.ACCEPTED);
//    }
}
