package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ItemRepository itemRepo;
//	@Value("${tomtom.apikey}")
//	 private String tomTomApiKey;
//	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
  	@GetMapping("/home")
  	public String retailersHome() {
  		return "admin/home";
  	}

    @GetMapping("index")
    public String index(){
        return "/admin/dashboard";
    }
    
  	@GetMapping("/inventory")
  	public String inventory() {
  		return "admin/inventory";
  	}
  
  	@GetMapping("/add")
  	public String addItem(Model model) {
  		model.addAttribute("item",new Item());
  		return "admin/addItem";
  	}
  	@PostMapping("/addItem-done")
  	public String Postregister(Item item) {
  		itemRepo.save(item);
  		return "admin/inventory";
  	}
  	
  	@GetMapping("/inventoryTable")
  	public ModelAndView viewcontents(Item items) {
  		ModelAndView mav=new ModelAndView("admin/inventoryTable");
  		mav.addObject("items",itemRepo.findAll());
  		return mav;
  	}
  	
  	@GetMapping("/dashboardPre")
  	public String dashboard() {
  		return "/admin/dashboardPre";
  	}
  	
  	@GetMapping("/dashboard")
  	public ModelAndView dashboard(Item items,User user) {
  		ModelAndView mav=new ModelAndView("/admin/dashboard");
  		mav.addObject("items",itemRepo.findAll());
  		mav.addObject("users",userRepo.findAll());
  		return mav;
  	}
  	
  	@GetMapping("/stocksGraph")
  	public ModelAndView stocksGraph(Item items) {
  		ModelAndView mav=new ModelAndView("admin/stocksGraph");
  		mav.addObject("items",itemRepo.findAll());
  		return mav;
  	}
  	
  	@GetMapping("/chooseProducts")
  	public String chooseProducts() {
  		return "admin/chooseProducts";
  	}
  	
  	@GetMapping("/fridgeStock")
  	public ModelAndView fridgeStock(Item items) {
  		ModelAndView mav=new ModelAndView("admin/fridgeInventoryTable");
  		mav.addObject("items",itemRepo.findAll());
  		return mav;
  	}
  	@GetMapping("/acStock")
  	public ModelAndView acStock(Item items) {
  		ModelAndView mav=new ModelAndView("admin/acInventoryTable");
  		mav.addObject("items",itemRepo.findAll());
  		return mav;
  	}
  	@GetMapping("/coolersStock")
  	public ModelAndView coolerStock(Item items) {
  		ModelAndView mav=new ModelAndView("admin/coolersInventoryTable");
  		mav.addObject("items",itemRepo.findAll());
  		return mav;
  	}
  	
  	@GetMapping("/update/{id}")
  	public ModelAndView update(@PathVariable(name = "id") Long id) {
  		ModelAndView mav=new ModelAndView("admin/updateItemForm");
  		mav.addObject("item",itemRepo.findById(id));
  		return mav;	
  	}
  	@PostMapping("/updateItem-done/{id}")
  	public String updateItem(@PathVariable(name = "id") Long id,Item item) {
  		Item upItem = itemRepo.findById(id);
  		upItem.setModel(item.getModel());
  		upItem.setUnit(item.getUnit());
  		upItem.setType(item.getType());
  		upItem.setCp(item.getCp());
  		upItem.setSp(item.getSp());
  		itemRepo.save(upItem);
  		return "admin/inventory";
  	}

      @GetMapping("/queries")
      public String update() {
      	return "queries";
      }
}
