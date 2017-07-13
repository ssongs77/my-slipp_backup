package net.slipp.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.slipp.domain.User;
import net.slipp.domain.UserRepository;

@Controller
public class UserController {
	
	private List<User> users = new ArrayList<User>();
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/user/create")
	public String create(User user){
		System.out.println("User : "+ user);
		users.add(user);
		userRepository.save(user);
		
		return "redirect:/user/list";
	}
	
	@GetMapping("/user/list")
	public String list(Model model){
		System.out.println("list");
		model.addAttribute("users", users);
		return "/user/list";
	}
}
 