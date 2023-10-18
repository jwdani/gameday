package com.gameday.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gameday.repository.UsersRepository;
import com.gameday.security.GamedayUserDetailsService;

@Controller
public class FlowController {
	
	private static final Logger LOGGER = LogManager.getLogger(FlowController.class);
	
	@Autowired
	private UsersRepository usersRepository;
	
	@RequestMapping(value = "/")
   	public ModelAndView home(@AuthenticationPrincipal GamedayUserDetailsService userDetails) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userDetails", userDetails);
		modelAndView.setViewName("home");
		
		return modelAndView;
   	}
}