package com.gameday.handler;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger LOGGER = LogManager.getLogger(LoggingAccessDeniedHandler.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	        if (auth != null) {
	        	LOGGER.info(auth.getName()
	                    + " was trying to access protected resource: "
	                    + request.getRequestURI());
	        }

	        response.sendRedirect(request.getContextPath() + "/access-denied");
	}
}
