package org.seledynowapowieka.simpleMVC.config;

import org.seledynowapowieka.simpleMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}


	@Override protected void configure(HttpSecurity http) throws Exception {
	  
	  http.authorizeRequests().antMatchers("/").permitAll() 
	  .antMatchers("/saveBook").hasRole("CLIENT")
	  .antMatchers("/addBook").hasRole("ADMIN")
	  .antMatchers("/delete").hasRole("ADMIN")
	  .antMatchers("/showCart").hasRole("CLIENT")
	  .and()
	  .formLogin().loginPage("/loginPage").loginProcessingUrl("/authenticateTheUser")
	  .successHandler(customAuthenticationSuccessHandler)
	  .permitAll()
	  .and().logout().permitAll()
	  .and().exceptionHandling().accessDeniedPage("/access-denied"); 
	 
	  }
	
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService); //set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
		return auth;
	}

}
