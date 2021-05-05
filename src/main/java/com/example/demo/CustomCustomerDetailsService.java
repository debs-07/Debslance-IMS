//package com.example.demo;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomCustomerDetailsService implements UserDetailsService {
//
//	@Autowired
//	private CustomerRepository customerRepo;
//
////	@Autowired
////	BCryptPasswordEncoder bCryptPasswordEncoder;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		Customer customer=customerRepo.findByEmail(username);
//		if(customer==null)
//		{
//			throw new UsernameNotFoundException("User not found");
//		}
//		return new CustomCustomerDetails(customer);
//	}
//	}

