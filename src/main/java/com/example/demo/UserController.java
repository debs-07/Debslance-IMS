package com.example.demo;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
@RequestMapping("user")
public class UserController {
	
	
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ItemRepository itemRepo;
//	@Autowired
//	private CustomerRepository customerRepo;
	
	
	@GetMapping("/gg")
	public String gg() {
	return "gg";
}
	
	

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
    @GetMapping("/plans")
    public String index(){
        return "user/plans";
    }
    
    @GetMapping("/plansCheckOut/{name}")
    public String plansCheckOut(@PathVariable("name") String name){
    	String username ;
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if (principal instanceof UserDetails) {
    		   username = ((UserDetails)principal).getUsername();
    		} else {
    		   username = principal.toString();
    		}
    	User user=userRepo.findByEmail(username);
    	user.setSubscription(name);
    	userRepo.save(user);
        return "user/plansCheckOut";
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
  	
 	@GetMapping("/displayProducts")
  	public ModelAndView displayProducts(Item items) {
  		ModelAndView mav=new ModelAndView("user/displayProducts");
  		mav.addObject("items",itemRepo.findAll());
  		return mav;
  	}
  	
  	@GetMapping("/cartView")
  	public String cartView() {
  		return "user/cart";
  	}
  	
	@GetMapping("/modifyStock/{id}")
  	public String modifyStock(@PathVariable(name = "id") Long id,RedirectAttributes redirectAttributes) {
		Item item=itemRepo.findById(id);
		Long x=item.getId();
		  redirectAttributes.addFlashAttribute("id", x);
		if(x<10)
  			return "redirect:/api/send";
  		return "/";
  	}
  	
  	@GetMapping("/cart/{id}")
  	public ModelAndView cart(@PathVariable(name = "id") Long id,@RequestParam Long units) {
  		ModelAndView mav=new ModelAndView("user/cart");
  		mav.addObject("item",itemRepo.findById(id));
  		mav.addObject("number",units);
  		Item item=itemRepo.findById(id);
  		item.setUnit(item.getUnit()-units);
  		
  		itemRepo.save(item);
  		return mav;	
  	}
  	
}