package com.example.service;

import java.util.List;

import com.example.entity.User;
import com.example.request.UserRequest;

public interface UserService {
	
	public void saveUserData(UserRequest userRequest);
	
	public List<User> getUserData();
	
	public void deleteUserData(long id);
	
	public void updateUserData(UserRequest userRequest);

	public User getUserDataById(long id);

}
