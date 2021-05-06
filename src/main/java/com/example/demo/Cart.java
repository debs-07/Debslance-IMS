package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private String model;
	private Long tsp;
	private Long tcp;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Long getTsp() {
		return tsp;
	}
	public void setTsp(Long tsp) {
		this.tsp = tsp;
	}
	public Long getTcp() {
		return tcp;
	}
	public void setTcp(Long tcp) {
		this.tcp = tcp;
	}
	
}
