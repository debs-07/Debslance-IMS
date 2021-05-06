package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ItemRepository itemRepo;
//	@Autowired
//	private CustomerRepository customerRepo;
	

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
    @GetMapping("index")
    public String index(){
        return "user/showItems";
    }
    
  	@GetMapping("/home")
  	public String customersHome() {
  		return "user/home";
  	}
  	
  	@GetMapping("/showPageShop")
  	public ModelAndView showPageShop(Item items) {
  		ModelAndView mav=new ModelAndView("user/showPageShop");
  		mav.addObject("items",itemRepo.findAll());
  		return mav;
  	}
  	
  	@GetMapping("/cartView")
  	public String cartView() {
  		return "user/cart";
  	}
  	
  	@GetMapping("/cart/{id}")
  	public ModelAndView cart(@PathVariable(name = "id") Long id) {
  		ModelAndView mav=new ModelAndView("user/cart");
  		mav.addObject("item",itemRepo.findById(id));
  		return mav;	
  	}
  	
}