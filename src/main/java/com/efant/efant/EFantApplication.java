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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
				.exceptionHandling(Customizer.withDefaults())
				.authenticationProvider(daoAuthenticationProvider)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//		http.oauth2ResourceServer().jwt();

		return http.build();

	}

	/**
	 * Allow the application to receive requests from the React app
	 * @return
	 */

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.addAllowedOrigin("http://localhost:3000"); // Allow the React app origin
		configuration.addAllowedOrigin("http://localhost:3001"); // Allow the React app origin
		configuration.addAllowedOrigin("http://app.e-fant.com/"); // Allow the React app origin
		configuration.addAllowedOrigin("https://app.e-fant.com/"); // Allow the React app origin
		configuration.addAllowedOrigin("http://apptest.e-fant.com/"); // Allow the React app origin
		configuration.addAllowedOrigin("https://apptest.e-fant.com/"); // Allow the React app origin
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
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
