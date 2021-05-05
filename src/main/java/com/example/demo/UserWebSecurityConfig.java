//package com.example.demo;
//
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@Order(1)
//public class UserWebSecurityConfig extends WebSecurityConfigurerAdapter {
////	@Autowired
////    BCryptPasswordEncoder bCryptPasswordEncoder;
//	
//	
//	@Autowired
//    UserDetailsService adminDetailsServiceImpl;
//	
////	@Override
////	public void configure(AuthenticationManagerBuilder auth) throws Exception {
////		auth.userDetailsService(adminDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);
////
////	}
//	
//	 @Autowired
//	    private DataSource dataSource;
//	 
//	 @Primary
//	    @Bean
//	    public UserDetailsService DetailsService() {
//	        return new CustomUserDetailsService();
//	    }
//	     @Primary
//	    @Bean
//	    public BCryptPasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder();
//	    }
//	     @Primary
//	    @Bean
//	    public DaoAuthenticationProvider authenticationProvider() {
//	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//	        authProvider.setUserDetailsService(userDetailsService());
//	        authProvider.setPasswordEncoder(passwordEncoder());
//	         
//	        return authProvider;
//	    }
//	
//	 @Override
//	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	        auth.authenticationProvider(authenticationProvider());
//	    }
//
//
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http
//			.antMatcher("/admin/**")
//			.authorizeRequests().anyRequest().authenticated()
//			.and().formLogin().loginPage("/admin/login")
//				.defaultSuccessUrl("/admin/dashboard", true)
//				.failureUrl("/admin/login?adminerror=true")
//			.permitAll()
//			.and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login");
//		http.csrf().disable();
//
//	}	
//}

