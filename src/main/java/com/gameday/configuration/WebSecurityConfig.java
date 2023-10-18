package com.gameday.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.gameday.handler.LoginAuthenticationSuccessHandler;
import com.gameday.security.GamedayPasswordEncoder;
import com.gameday.security.GamedayUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	private static final Logger LOGGER = LogManager.getLogger(WebSecurityConfig.class);
	
	@Autowired
	GamedayUserDetailsService gamedayUserDetailsService;
	
	@Autowired
    private HandlerMappingIntrospector handlerMappingIntrospector;	
	
	@Autowired
	private LoginAuthenticationSuccessHandler successHandler;	

	@Bean
    public static GamedayPasswordEncoder passwordEncoder() {
		
        return new GamedayPasswordEncoder();
    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
			
		httpSecurity.authorizeHttpRequests((authorize) -> authorize				
					.requestMatchers(new MvcRequestMatcher(handlerMappingIntrospector, "/h2-console/**")).permitAll()					
					.anyRequest().authenticated())
		    		.userDetailsService(gamedayUserDetailsService)				
		    		.httpBasic(withDefaults())
		    		.formLogin().successHandler(successHandler);;		
		
		httpSecurity.csrf((csrf) -> csrf.disable());
		httpSecurity.headers((headers) -> headers.disable());
			
		return httpSecurity.build();
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
        	auth.userDetailsService(gamedayUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
