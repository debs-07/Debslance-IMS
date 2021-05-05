package com.example.demo;



import java.io.IOException;
import java.util.Optional;

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



import com.example.demo.MailService;
//import com.demo.sendgrid.service.EmailService;

@Controller
@RequestMapping
public class AppController {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private CustomerRepository customerRepo;
//	@Autowired
//	EmailService emailService;
//	
//	@GetMapping("/sendMail/{email}")
//	public String sendEmail(@PathVariable(value = "email", required = true) String email)
//	{
//	return	emailService.sendEmail(email);
//	
//	}
	
	@Autowired
	MailService mailService;
	
	@PostMapping("/api/send-text")
	public String send() throws IOException {
		return mailService.sendTextEmail();
	}
	
	
	//Home page
	@GetMapping("/")
	public String home() {
		return "home";
	}
	//CustomersHome page
	@GetMapping("/customersHome")
	public String customersHome() {
		return "customersHome";
	}
	
//////////////////////////////////////////////////////////////////////////
//	@GetMapping("/customer/login")
//	public String customerLogin(Model model) {
//		model.addAttribute("customer", new Customer());
//		return "customerLogin";
//	}
//
//	@PostMapping("/customer/loggedin")
//	public String customerLoggedIn() {
//		return "showItems";
//	}
////////////////////////////////////////////////////////////////////////////////
	
	//CustomerRegister page
	@GetMapping("/customerRegister")
	public String customerRegister(Model model) {
		model.addAttribute("customer",new Customer());
		return "customerRegister";
	}
	@PostMapping("/customer-register-done")
	public String PostCustomerregister(Customer customer) {
		    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		    String encodedPassword = passwordEncoder.encode(customer.getPassword());
		    customer.setPassword(encodedPassword);
		    customerRepo.save(customer);
		return "showItems";
	}
	@GetMapping("/showPageShop")
	public ModelAndView showPageShop(Item items) {
		ModelAndView mav=new ModelAndView("showPageShop");
		mav.addObject("items",itemRepo.findAll());
		return mav;
	}
	@GetMapping("/cartView")
	public String cartView() {
		return "cart";
	}
	
	
	@GetMapping("/cart/{id}")
	public ModelAndView cart(@PathVariable(name = "id") Long id) {
		ModelAndView mav=new ModelAndView("cart");
		mav.addObject("item",itemRepo.findById(id));
		return mav;
		
	}
	
	

	
	
	
	
	
	//retailersHome page
	@GetMapping("/retailersHome")
	public String retailersHome() {
		return "retailersHome";
	}
	
	//Dash board
	@GetMapping("/dashboardPre")
	public String dashboard() {
		return "dashboardPre";
	}
	
	//Register New Account
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user",new User());
		return "register";
	}
	@PostMapping("/register-done")
	public String Postregister(User user) {
		    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		    String encodedPassword = passwordEncoder.encode(user.getPassword());
		    user.setPassword(encodedPassword);
		    userRepo.save(user);
		return "dashboardPre";
	}
	
	/////////////////////////////////////////////////////////////////////
//	@GetMapping("/user/login")
//	public String userrLogin(Model model) {
//		model.addAttribute("user", new User());
//		return "userLogin";
//	}
//
//	@PostMapping("/user/loggedin")
//	public ModelAndView userLoggedIn(User users,User user) {
//		ModelAndView mav=new ModelAndView("dashboard");
//		mav.addObject("user",userRepo.findByEmail(user.getEmail()));
//		mav.addObject("users",userRepo.findAll());
//		return mav;
//	}
	////////////////////////////////////////////////////////////////////
	
	//After Login Can see Inventory
	@GetMapping("/inventory")
	public String inventory() {
		return "inventory";
	}
	//Add items after logging in
	@GetMapping("/add")
	public String addItem(Model model) {
		model.addAttribute("item",new Item());
		return "addItem";
	}
	@PostMapping("/addItem-done")
	public String Postregister(Item item) {
		itemRepo.save(item);
		return "inventory";
	}
	
	//View Items after Loggin in
	@GetMapping("/inventoryTable")
	public ModelAndView viewcontents(Item items) {
		ModelAndView mav=new ModelAndView("inventoryTable");
		mav.addObject("items",itemRepo.findAll());
		return mav;
	}
	
	@GetMapping("/dashboard")
	public ModelAndView dashboard(Item items) {
		ModelAndView mav=new ModelAndView("dashboard");
		mav.addObject("items",itemRepo.findAll());
		return mav;
	}
	
	@GetMapping("/stocksGraph")
	public ModelAndView stocksGraph(Item items) {
		ModelAndView mav=new ModelAndView("stocksGraph");
		mav.addObject("items",itemRepo.findAll());
		return mav;
	}
	@GetMapping("/chooseProducts")
	public String chooseProducts() {
		return "chooseProducts";
	}
	@GetMapping("/fridgeStock")
	public ModelAndView fridgeStock(Item items) {
		ModelAndView mav=new ModelAndView("fridgeInventoryTable");
		mav.addObject("items",itemRepo.findAll());
		return mav;
	}
	@GetMapping("/acStock")
	public ModelAndView acStock(Item items) {
		ModelAndView mav=new ModelAndView("acInventoryTable");
		mav.addObject("items",itemRepo.findAll());
		return mav;
	}
	@GetMapping("/coolersStock")
	public ModelAndView coolerStock(Item items) {
		ModelAndView mav=new ModelAndView("coolersInventoryTable");
		mav.addObject("items",itemRepo.findAll());
		return mav;
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView update(@PathVariable(name = "id") Long id) {
		ModelAndView mav=new ModelAndView("updateItemForm");
		mav.addObject("item",itemRepo.findById(id));
		return mav;	
	}
	@PostMapping("/updateItem-done/{id}")
	public String updateItem(@PathVariable(name = "id") Long id,Item item) {
		Item upItem = itemRepo.findById(id);
		upItem.setName(item.getName());
		upItem.setUnit(item.getUnit());
		upItem.setType(item.getType());
		upItem.setCostPrice(item.getCostPrice());
		upItem.setSellPrice(item.getSellPrice());
		itemRepo.save(upItem);
		return "inventory";
	}

    @PutMapping("/items")
    public Item update(@RequestBody Item itemObj) {
    	return itemRepo.save(itemObj);
    }
	
	}

