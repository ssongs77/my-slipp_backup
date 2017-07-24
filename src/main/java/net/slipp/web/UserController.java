package net.slipp.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slipp.domain.User;
import net.slipp.domain.UserRepository;
import scala.remote;

@Controller
@RequestMapping("/users")
public class UserController {
	
	private List<User> users = new ArrayList<User>();
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/loginForm")
	public String loginForm(){
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session){
		User user = userRepository.findByUserId(userId);
		if(user == null)
		{
			System.out.println("Login Failure!");
			return "redirect:/users/loginForm";
		}
		if(!password.equals(user.getPassword())){
			System.out.println("Login Failure!");
			return "redirect:/users/loginForm";
		}
		System.out.println("Login Success!");
		session.setAttribute("user", user);
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("user");
		
		return "redirect:/";
	}
	
	@GetMapping("/form")
	public String form(){
		return "/user/form";
	}
	
	@PostMapping("")
	public String create(User user){
		System.out.println("User : "+ user);
		users.add(user);
		userRepository.save(user);
		
		return "redirect:/users";
	}
	
	@GetMapping("")
	public String list(Model model){
		System.out.println("list");
		//model.addAttribute("users", users);
		model.addAttribute("users", userRepository.findAll());
		return "/user/list"; 
	}
	
	@GetMapping("/{id}/form")
	public String updateform(@PathVariable Long id, Model model){ // 위에 {id}와 여기 선언한 id 이름이 같아야 함.
		User user = userRepository.findOne(id);
		model.addAttribute("user", user);
		return "/user/updateForm";
	}
	
	//@PostMapping("/{id}")
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User newUser){
		System.out.println("update");
		User user = userRepository.findOne(id);
		user.update(newUser);
		userRepository.save(user);
		return "redirect:/users";
	}
	
}
 