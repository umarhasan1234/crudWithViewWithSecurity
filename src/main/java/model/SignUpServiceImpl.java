package model;

import com.example.entity.SignUp;
import com.example.repository.SignUpRepository;
import com.example.request.SignUpRequest;
import com.example.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private SignUpRepository signUpRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    public void saveSignUpData(SignUpRequest signUpRequest) {
//
//    }


      @Override
    public void saveSignUpData(SignUpRequest signUpRequest) {
        String password=passwordEncoder.encode(signUpRequest.getSignUpPassword());
        SignUp s=new SignUp();
        s.setSignUpid(signUpRequest.getSingUpId());
        s.setSignUpUserName(signUpRequest.getUserNameOrEmail());
        s.setSignUpPassword(password);
        signUpRepository.save(s);
    }
}
