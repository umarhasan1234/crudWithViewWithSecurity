package controller;

import com.example.entity.User;
import com.example.request.UserRequest;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
		public List<User> getData(ModelAndView modelAndView){
		List<User> user=userService.getUserData();
//		modelAndView.addObject("user", user);
//		modelAndView.setViewName("/views/findDetails");
//		return modelAndView;
			return user;
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
