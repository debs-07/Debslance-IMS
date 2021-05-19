package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDetails,Integer>{

	OrderDetails findById(Long id);
	OrderDetails findByItemId(Long id);
	List<OrderDetails> findAll();
}
