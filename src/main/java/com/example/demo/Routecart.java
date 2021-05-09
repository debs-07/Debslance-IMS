package com.example.demo;

import org.springframework.stereotype.Controller;

@Controller
public class Routecart {

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	private Long id;
	private Long no;
}
