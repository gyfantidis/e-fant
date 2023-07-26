package com.efant.efant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class EFantApplication {

	public static void main(String[] args) {
		SpringApplication.run(EFantApplication.class, args);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, DaoAuthenticationProvider daoAuthenticationProvider) throws Exception {

		http
				.cors().and()
				.csrf().disable()
				.authorizeRequests((authz) ->
						authz
								.requestMatchers("/actuator/health").permitAll()
								.requestMatchers("/signup").permitAll()
								.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.authenticationProvider(daoAuthenticationProvider)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//		http.oauth2ResourceServer().jwt();

		return http.build();
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public AuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

}
