package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
@CrossOrigin(origins="https://kit.fontawesome.com/a076d05399.js")
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
		return "delivery/home";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
  	@GetMapping("/register-1604")
  	public String register(Model model) {
  		model.addAttribute("user",new User());
  		return "admin/register";
  	}
  	
  	@PostMapping("/register-done")
  	public String Postregister(User user) {
  		    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  		    String encodedPassword = passwordEncoder.encode(user.getPassword());
  		    user.setPassword(encodedPassword);
  		    if(userRepo.findByEmail(user.getEmail())==null) {
  		    userRepo.save(user);
  		  return "admin/home";
  		    }
  		return "Email Already Exists";
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
  		 if(userRepo.findByEmail(user.getEmail())==null) {
  		    userRepo.save(user);
  		  return "user/home";
  		  }
  		return "Email Already Exists";
	}}

