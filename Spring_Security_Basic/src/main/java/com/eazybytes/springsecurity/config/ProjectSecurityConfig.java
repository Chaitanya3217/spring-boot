package com.eazybytes.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount","/myBalance","/myLoans","/myCards").authenticated()
                .requestMatchers("/notices","/contact").permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll())
        .formLogin(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults());
		
		http.headers(headersConfigurer->headersConfigurer.frameOptions(frameOptionsConfig->
				frameOptionsConfig.disable()));
		return http.build();
//		http.authorizeHttpRequests().
//		requestMatchers("/myAccount","/myBalance","/myLoans","/myCards").authenticated()
//		.requestMatchers("/notices","/contact").permitAll()
//		.and().formLogin()
//		.and().httpBasic();
//		return http.build();
	}

/*	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
//		the below was using noop password encoder
		UserDetails admin = User.withUsername("admin")
				.password("12365")
				.authorities("admin")
				.build();
		UserDetails user = User.withUsername("user")
				.password("12365")
				.authorities("admin")
				.build();
		return new InMemoryUserDetailsManager(admin,user);
	}
*/
	
//		 this is a general or normal approach and not recommended approach in
//		 production bcz the peoples can see our credentials so try to avoid like this
		 
/*	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails admin = User.withDefaultPasswordEncoder()
				.username("admin")
				.password("12365")
				.authorities("admin")
				.build();
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("12365")
				.authorities("user")
				.build();
		return new InMemoryUserDetailsManager(admin,user);
	}
	*/
//	write now we are using the second format i.e., jdbcuserdetailsservice but i am not able to get these security details.
//	@Bean
//	public UserDetailsService userDetailsService(DataSource datasource) {
//		return new JdbcUserDetailsManager(datasource);
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
