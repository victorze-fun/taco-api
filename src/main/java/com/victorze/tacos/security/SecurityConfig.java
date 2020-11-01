package com.victorze.tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder encoder() {
		return new StandardPasswordEncoder("53cr3t");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS).permitAll() // needed for Angular/CORS
				.antMatchers("/design", "/orders").permitAll()
				.antMatchers(HttpMethod.PATCH, "/ingredients").permitAll()
		        .antMatchers("/**").access("permitAll")
				
			.and()
				.formLogin()
					.loginPage("/login")
			.and()
				.httpBasic()
					.realmName("Taco Cloud")
			.and()
				.logout()
					.logoutSuccessUrl("/")
			.and()
				.csrf()
					.ignoringAntMatchers("/h2-console/**", "/ingredients/**", "/design", "/orders/**")
			;
		
			http.headers().frameOptions().sameOrigin();
			// Active h2-console
//			http.csrf().disable();
//			http.headers().frameOptions().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encoder());
	}

}