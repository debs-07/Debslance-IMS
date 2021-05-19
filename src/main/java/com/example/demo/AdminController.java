package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private QueryRepository queryRepo;
	
	
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
 
  	@GetMapping("/inventoryTable")
  	public ModelAndView viewcontents(Item items) {
  		ModelAndView mav=new ModelAndView("admin/inventoryTable");
  		mav.addObject("items",itemRepo.findAll());
  		return mav;
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
  	
	@GetMapping("/rating/{id}")
  	public ModelAndView rating(@PathVariable(name = "id") Long id,Item items) {
  		ModelAndView mav=new ModelAndView("admin/rating");
  		mav.addObject("item",itemRepo.findById(id));
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
  	
  	@GetMapping("/pichartAc")
  	public ModelAndView pichartAc(Item items) {
  		ModelAndView mav=new ModelAndView("/admin/pichartAc");
  		mav.addObject("items",itemRepo.findAll());
  		return mav;
  	}
  	
	
  	@GetMapping("/pichartFridge")
  	public ModelAndView pichartFridge(Item items) {
  		ModelAndView mav=new ModelAndView("/admin/pichartFridge");
  		mav.addObject("items",itemRepo.findAll());
  		return mav;
  	}
  	
	
  	@GetMapping("/pichartCoolers")
  	public ModelAndView pichartCoolers(Item items) {
  		ModelAndView mav=new ModelAndView("/admin/pichartCoolers");
  		mav.addObject("items",itemRepo.findAll());
  		return mav;
  	}
  	
  	@GetMapping("/ordersGraph")
  	public ModelAndView ordersGraph(Item items,OrderDetails order) {
  		ModelAndView mav=new ModelAndView("/admin/ordersGraph");
  		mav.addObject("items",itemRepo.findAll());
  		mav.addObject("orders",orderRepo.findAll());
  		return mav;
  	}
  		 
 	@GetMapping("/add")
 	public String addItem(Model model) {
 		model.addAttribute("item",new Item());
 		return "admin/addItem";
 	}
 	@PostMapping("/addItem-done")
 	public String Postregister(Item item) {
 		itemRepo.save(item);
 		return "admin/home";
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
 		upItem.setColor(item.getColor());
 		itemRepo.save(upItem);
 		return "admin/home";
 	}
 	
      @GetMapping("/queries")
    	public ModelAndView queries(OrderDetails order) {
    		ModelAndView mav=new ModelAndView("/admin/queries");
    		mav.addObject("queries",queryRepo.findAll());
    		return mav;
      }
      
      @GetMapping("/resolved/{id}")
  	public String resolved(@PathVariable(name = "id") Long id,OrderDetails order) {
  		Query query=queryRepo.findById(id);
  		query.setStatusAdmin("resolved");
  		queryRepo.save(query);
  		return "/admin/home";
    }
}
