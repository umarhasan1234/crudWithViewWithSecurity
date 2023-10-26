package controller;

import java.util.List;

import javax.persistence.metamodel.SetAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.User;
import com.example.request.UserRequest;
import com.example.service.UserService;

@RestController
@RequestMapping("/vpt/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public ModelAndView defaultMethod(ModelAndView modelAndView) {
		System.out.println("hiii");
		modelAndView.setViewName("/views/vpt_form");
				return modelAndView;
	}
	           
	
	@PostMapping("/saveData")
	public ModelAndView saveData(@ModelAttribute("userRequest") UserRequest userRequest,ModelAndView modelAndView) {
		userService.saveUserData(userRequest);
		modelAndView.setViewName("/views/vpt_form");
		return modelAndView;
		
	}
	
	@GetMapping("/get")
		public ModelAndView getData(ModelAndView modelAndView){
		List<User> user=userService.getUserData();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("/views/findDetails");
		return modelAndView;
			
		}
	
	@RequestMapping("/update/")
	public ModelAndView updateData(@RequestParam("id")long id, @ModelAttribute UserRequest userRequest,ModelAndView modelAndView) {
		User user=userService.getUserDataById(id);
		modelAndView.addObject("user",user);
		modelAndView.setViewName("/views/update_form");
		return modelAndView;
	}
	
	@RequestMapping("/updateUser")
	public ModelAndView updateUser(@ModelAttribute("userRequest")UserRequest userRequest,ModelAndView modelAndView) {
		userService.updateUserData(userRequest);
		List<User> user=userService.getUserData();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("/views/findDetails");
		return modelAndView;
	}
	
	@RequestMapping("/delete/")
	public ModelAndView deleteData(@RequestParam("id") Long id,ModelAndView modelAndView) {
		userService.deleteUserData(id);
		List<User> user=userService.getUserData();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("/views/findDetails");
		return modelAndView;
	}
	

}
