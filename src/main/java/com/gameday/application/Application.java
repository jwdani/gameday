package com.gameday.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// TODO: Auto-generated Javadoc
/**
 * The Class Application.
 */
@SpringBootApplication(scanBasePackages={"com.gameday"})
@EnableJpaRepositories("com.gameday.repository")
@EntityScan("com.gameday.model") 
public class Application extends SpringBootServletInitializer {
	
	private static final Logger LOGGER = LogManager.getLogger(Application.class);
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}	
	
}
