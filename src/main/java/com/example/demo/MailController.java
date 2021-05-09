package com.example.demo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


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
			return "/showItemPage";
	
  	}
	
	}
	

