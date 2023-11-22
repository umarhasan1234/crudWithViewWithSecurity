package controller;


import com.example.databasePropertiesFile.DataBaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/vpt/database")
public class DatabaseController {

    @Autowired
            private DataBaseProperties dataBaseProperties;
    @RequestMapping("/getDatabaseProperties")
        public String getProperties(){
            String url = dataBaseProperties.getDatabaseUrl();
            String username = dataBaseProperties.getDataBaseUserName();
            String password = dataBaseProperties.getDatabasePasssword();

            return "Database URL: " + url + "<br>" +
                    "Username: " + username + "<br>" +
                    "Password: " + password;

        }
}
