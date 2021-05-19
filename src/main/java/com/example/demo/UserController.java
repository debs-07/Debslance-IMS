package com.example.demo;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	@Autowired
	private ReviewRepository reviewRepo;
	@Autowired
	private QueryRepository queryRepo;
	@Autowired
	private OrderRepository orderRepo;
	


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
  		return "home";
  	}
  	
  	@GetMapping("/cart/buy/{id}")
  	public ModelAndView cart(@PathVariable(name = "id") Long id,Principal principal,@RequestParam Long units,@RequestParam Double rating,@RequestParam String review) {
  		ModelAndView mav=new ModelAndView("user/cart");
  		mav.addObject("item",itemRepo.findById(id));
  		mav.addObject("number",units);
  		Item item=itemRepo.findById(id);
  		item.setUnit(item.getUnit()-units);
        Double newRating=(item.getRating()+rating)/2;
        item.setRating(newRating);
        if(review!=" ") {
        	System.out.print(review!=null);        
        String name=principal.getName();
        Review reviewNew=new Review();
        reviewNew.setReview(review);
        reviewNew.setItemId(id);
        reviewNew.setUser(name);
        reviewRepo.save(reviewNew);
        }
  		itemRepo.save(item);
  		return mav;	
  	}
  	
  	@GetMapping("/details/{id}")
  	public ModelAndView details(@PathVariable(name = "id") Long id) {
  		ModelAndView mav=new ModelAndView("user/details");
  		mav.addObject("item",itemRepo.findById(id));
  	    mav.addObject("reviews",reviewRepo.findAll());
  		return mav;
}
  	
	@GetMapping("/query/{id}")
  	public ModelAndView query(@PathVariable(name = "id") Long id) {
  		ModelAndView mav=new ModelAndView("user/query");
  		mav.addObject("item",itemRepo.findById(id));
  		mav.addObject("query",new Query()); 
  		return mav;
}

  	@PostMapping("/query/{id}")
  	public String Postquery(@PathVariable(name = "id") Long id,Principal principal,Query query) {
  		  query.setItemId(id);
  		  query.setUserEmail(principal.getName());
  		  queryRepo.save(query);
  		  return "/home";
  	}
  	
	@GetMapping("/order/{id}")
  	public ModelAndView order(@PathVariable(name = "id") Long id) {
  		ModelAndView mav=new ModelAndView("user/order");
  		mav.addObject("item",itemRepo.findById(id));
  		mav.addObject("order",new OrderDetails());
  		return mav;
}

  	@PostMapping("/order-done/{id}")
  	public String Postorder(OrderDetails order,@PathVariable(name = "id") Long id,Principal principal) {
  		  order.setItemId(id);
  		  order.setItemName(itemRepo.findById(id).getModel());
  		  order.setUserEmail(principal.getName());
  		  orderRepo.save(order);
  		 System.out.print(order.getUnits());
  		  return "/user/orderSuccess";
  	}
  	
	@GetMapping("/ordersList")
  	public ModelAndView ordersList(Principal principal) {
  		ModelAndView mav=new ModelAndView("user/ordersList");
  		mav.addObject("orders",orderRepo.findAll());
        mav.addObject("email",principal.getName());
  		return mav;
}
	
	@GetMapping("/cancel/{id}")
  	public String cancel(Principal principal,@PathVariable(name = "id") Long id) {
        OrderDetails order=orderRepo.findById(id);
        if(order.getUserEmail().equals(principal.getName()))
        {
        	order.setStatusUser("cancelled");
        	orderRepo.save(order);
        	return "user/home";
        }
  		return "user/ordersList";
}
	
	@GetMapping("/queries")
  	public ModelAndView queries(Principal principal) {
  		ModelAndView mav=new ModelAndView("user/queries");
  		mav.addObject("queries",queryRepo.findAll());
  		 mav.addObject("email",principal.getName());
  		return mav;
}
	
    @GetMapping("/resolved/{id}")
	public String resolved(@PathVariable(name = "id") Long id,OrderDetails order) {
		Query query=queryRepo.findById(id);
		query.setStatusUser("resolved");
		queryRepo.save(query);
		return "/user/home";
  }
}















