package com.example.demo;



import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


@Controller
@RequestMapping
public class AppController {
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	@GetMapping("/home/{name}")
	public String commonLandingPage(@PathVariable("name") String name) {
		if(name.equals("user"))
		return "user/home";
		else if(name.equals("admin"))
		return "admin/home";
		System.out.print(name);
		return "delivery/home";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
 
  	@GetMapping("/register")
  	public String register(Model model) {
  		model.addAttribute("user",new User());
  		return "admin/register";
  	}
  	@PostMapping("/register-done")
  	public String Postregister(User user) {
  		    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  		    String encodedPassword = passwordEncoder.encode(user.getPassword());
  		    user.setPassword(encodedPassword);
  		    userRepo.save(user);
  		    
  		return "/home";
  	}

  	@GetMapping("/userRegister")
  	public String customerRegister(Model model) {
  		model.addAttribute("user",new User());
  		return "user/register";
  	}
  	@PostMapping("/userRegisterDone")
  	public String PostCustomerregister(User user) {
  		    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  		    String encodedPassword = passwordEncoder.encode(user.getPassword());
  		    user.setPassword(encodedPassword);
  		    user.setRoles("USER");
  		  user.setPermissions("");
  		 
  		    userRepo.save(user);
  		return "user/plans";
	}}

