package com.example.demo;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/api")
public class MailController {

	@Autowired
	MailService mailService;
	
	@Autowired
	private ItemRepository itemRepo;
	
	@GetMapping("/send")
	public String sendWithTemplate(@ModelAttribute("id") Long id ) throws IOException {
		Item item=itemRepo.findById(id);
		if(item.getUnit()<10)
		{
			return mailService.send(item.getModel());
		}
		else
			return "<h1>ORDER SUCCESS<h1>"
		       + "<a href=\"https://debslance-ims.apps.pcfdev.in/user/displayProducts\">Go Back</a>";
	
  	}
	
	}
	

