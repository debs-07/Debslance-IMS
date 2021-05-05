package com.example.demo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer>{
	
	List<Item> findAll();
	Item findById(Long id);
	
	 
	
}
