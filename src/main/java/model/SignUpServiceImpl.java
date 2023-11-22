package model;

import com.example.entity.Login;
import com.example.repository.LoginRepository;
import com.example.request.SignUpRequest;
import com.example.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private LoginRepository signUpRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    public void saveSignUpData(SignUpRequest signUpRequest) {
//
//    }


      @Override
    public void saveSignUpData(SignUpRequest signUpRequest) {
        String password=passwordEncoder.encode(signUpRequest.getSignUpPassword());
        Login s=new Login();
        s.setSignUpid(signUpRequest.getSingUpId());
        s.setSignUpUserName(signUpRequest.getUserNameOrEmail());
        s.setSignUpPassword(password);
        signUpRepository.save(s);
    }
}
