package model;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.request.UserRequest;
import com.example.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public void saveUserData(UserRequest userRequest) {
		User u=new User();
		u.setName(userRequest.getName());
		u.setEmail(userRequest.getEmail());
		u.setMob(userRequest.getMob());
		u.setPosition(userRequest.getPosition());
		userRepo.save(u);
	}

	@Override
	public List<User> getUserData() {
		List<User> u=userRepo.findAll();
		return u;
	}

	@Override
	public void deleteUserData(long id) {
		userRepo.deleteById(id);
	}

	@Override
	public void updateUserData(UserRequest userRequest) {
		User userData = userRepo.findById(userRequest.getId()).orElseThrow(()->new EntityNotFoundException());
		userData.setName(userRequest.getName());
		userData.setEmail(userRequest.getEmail());
		userData.setMob(userRequest.getMob());
		userData.setPosition(userRequest.getPosition());
		userRepo.save(userData);
		
	}

	@Override
	public User getUserDataById(long id) {
		 User user = userRepo.findById(id).orElseThrow(()->new EntityNotFoundException());
		return user;
		
	}

}
