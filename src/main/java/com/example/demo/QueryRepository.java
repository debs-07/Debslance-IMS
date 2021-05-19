package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryRepository extends JpaRepository<Query,Integer>{

	Query findById(Long id);
	Query findByuserEmail(String email);
	List<Query> findAll();
}
