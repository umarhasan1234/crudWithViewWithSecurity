package com.example.databasePropertiesFile;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DataBaseProperties {

    @Autowired
    private Environment environment;

    public String getDatabaseUrl(){
        return environment.getProperty("spring.datasource.url");
    }

    public String getDataBaseUserName(){
        return environment.getProperty("spring.datasource.username");


    }

    public String getDatabasePasssword(){
        return environment.getProperty("spring.datasource.password");

    }
}
