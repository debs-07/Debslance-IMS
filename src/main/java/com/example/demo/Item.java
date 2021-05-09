package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String model;
	private String type;
	private Long unit;
	private Long sp;
	private Long cp;
	private Double rating;
	private String reviews="";
	private String profit;
	private String color;
	private String sold="";
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getSp() {
		return sp;
	}
	public void setSp(Long sp) {
		this.sp = sp;
	}
	public Long getCp() {
		return cp;
	}
	public void setCp(Long cp) {
		this.cp = cp;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public String getReviews() {
		return reviews;
	}
	public void setReviews(String reviews) {
		this.reviews = reviews;
	}
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Long getUnit() {
		return unit;
	}
	public void setUnit(Long unit) {
		this.unit = unit;
	}
	public String getSold() {
		return sold;
	}
	public void setSold(String sold) {
		this.sold = sold;
	}

	   public List<String> getReviewList(){
	        if(this.reviews.length() > 0){
	            return Arrays.asList(this.reviews.split(","));
	        }
	        return new ArrayList<>();
	    }
	   public List<String> getSoldList(){
	        if(this.sold.length() > 0){
	            return Arrays.asList(this.sold.split(","));
	        }
	        return new ArrayList<>();
	    }
}
