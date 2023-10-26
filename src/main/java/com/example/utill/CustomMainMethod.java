package com.example.utill;

import com.example.DemoApplication;
import com.example.service.UserService;
import controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CustomMainMethod {




    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(DemoApplication.class);

        // Customize any properties or configurations as needed
       // application.setAdditionalProfiles("custom-method"); // Optional

        // Run the Spring Boot application
        ConfigurableApplicationContext context = application.run(args);

        // Your custom code can go here
        System.out.println("customm method created");



        // Close the application context when you're done
      //  context.close();

            }
}
