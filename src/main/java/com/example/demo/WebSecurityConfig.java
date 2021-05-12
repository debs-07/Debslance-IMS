package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

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
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@Order(1)
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private DataSource dataSource;
//   
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new CustomUserDetailsService();
//    }
//  
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//   
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//         
//        return authProvider;
//    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//            .antMatchers("/registerPre","/inventory","/add","/addItem-done","/inventoryTable","/dashboard",
//            		"/stocksGraph","/chooseProducts","/fridgeStock","/acStock","/coolersStock","/update/{id}","/updateItem-done/{id}","/login").authenticated()
//            .anyRequest().permitAll()
//            .and()
//            .formLogin()
//                .usernameParameter("email")
//                .defaultSuccessUrl("/admin/dashboardPre")
//                .permitAll()
//            .and()
//            .logout().logoutSuccessUrl("/").permitAll();
//            .and()
//            .formLogin()
//            .loginProcessingUrl("/admin/login")
//            .loginPage("/login").permitAll()
//            .usernameParameter("email")
//            .defaultSuccessUrl("/admin/dashboardPre")
//            .and()
//            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
//            .and()
//            .rememberMe().tokenValiditySeconds(2592000).key("mySecret!").rememberMeParameter("checkRememberMe");
//    }
//
//}







import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private DataSource dataSource;
    private CustomUserDetailsService customUserDetailsService ;

    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService ) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Autowired
	private RoleAuthenticationSuccessHandler successHandler;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/admin/home","/register","/register-done","/user/home","/user/register","/user-register-done","/user/home","/admin/home","/api/send").permitAll()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/delivery/**").hasAnyRole("ADMIN", "DELIVERY")
//                .antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
//                .antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2")
//                .antMatchers("/api/send").hasRole("USER")
                .and()
                .formLogin().successHandler(successHandler)
                .loginProcessingUrl("/login")
                
                .loginPage("/login").permitAll()
                .usernameParameter("email")
                .passwordParameter("password")
                
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
//                .and()
//                .rememberMe().tokenValiditySeconds(2592000).key("mySecret!").rememberMeParameter("checkRememberMe");

    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.customUserDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}