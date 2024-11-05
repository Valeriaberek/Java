package com.keyce.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests((requests) -> requests.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/user").permitAll()
				.requestMatchers("/user/**", "/keyce/**").hasRole("USER")
				.requestMatchers("/", "/**", "/login", "/error").permitAll()).csrf(csrf -> csrf.disable())
				.formLogin((form) -> form.defaultSuccessUrl("/", true) // Redirection après connexion réussie
						.permitAll())
				.logout((logout) -> logout.permitAll());

		return http.build();
	}

	@Bean
	UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails user = User.builder().username("user").password(passwordEncoder.encode("password")).roles("USER")
				.build();

		UserDetails admin = User.builder().username("admin").password(passwordEncoder.encode("admin"))
				.roles("ADMIN", "USER").build();

		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
