package com.gameday.controller;

import java.sql.Date;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gameday.constants.GamedayConstants;
import com.gameday.exception.ResourceNotFoundException;
import com.gameday.model.Users;
import com.gameday.repository.UsersRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class UsersController.
 */
@Controller
@RequestMapping(GamedayConstants.REQUEST_MAPPTING_ADMIN)
public class UsersController {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(UsersController.class);
	
	/** The users repository. */
	@Autowired
    private UsersRepository usersRepository;
	
	/**
	 * List userss.
	 *
	 * @return the model and view
	 */
	@GetMapping("/users")
    public ModelAndView listUserss() {
    	
    	ModelAndView modelAndView = new ModelAndView();
    	
    	modelAndView.addObject("userss", usersRepository.findAll());
    	modelAndView.setViewName("/listUserss");
        
        return modelAndView;
    }
    
	/**
	 * Creates the users.
	 *
	 * @param users the users
	 * @return the model and view
	 */
	@PostMapping("/users")
    public ModelAndView createUsers(@ModelAttribute("users") Users users) {
		
		Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		users.setCreatedAt(now);
		users.setUpdatedAt(now);
    	
    	usersRepository.save(users);
    	
    	ModelAndView modelAndView = new ModelAndView();
    	
    	modelAndView.addObject("message", "Users " + users.getUsername() + " was created.");
    	modelAndView.setViewName("message");
        
        return modelAndView;
    }
    
	/**
	 * Gets the users.
	 *
	 * @param id the id
	 * @return the users
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping("/users/{id}")
    public ModelAndView getUsers(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
                   
    	Users users = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Users with ID " + id + " not found"));
    	
    	ModelAndView modelAndView = new ModelAndView();
    	
    	modelAndView.addObject("users", users);
    	modelAndView.setViewName("/updateUsers");
        
        return modelAndView;
    }	
	
	/**
	 * Update.
	 *
	 * @param users the users
	 * @return the model and view
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@PutMapping("/users")  
    public ModelAndView update(@ModelAttribute("users") Users users) throws ResourceNotFoundException {  
		
		Users oldUsers = usersRepository.findById(users.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Users with username " + users.getUsername() + " not found"));
		
		users.setCreatedAt(oldUsers.getCreatedAt());
		users.setUpdatedAt(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		
		usersRepository.save(users);
         
		ModelAndView modelAndView = new ModelAndView();
		
    	modelAndView.addObject("message", "Users " + users.getUsername() + " was saved.");
    	modelAndView.setViewName("message");
    	
    	return modelAndView;
    } 
	
	/**
	 * Delete.
	 *
	 * @param users the users
	 * @return the model and view
	 */
	@DeleteMapping("/users")
    public ModelAndView delete(@ModelAttribute("users") Users users) {
        	
    	usersRepository.delete(users);

    	ModelAndView modelAndView = new ModelAndView();

    	modelAndView.addObject("message", "Users " + users.getUsername() + " was deleted.");
    	modelAndView.setViewName("message");
        
        return modelAndView;
    }
    
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the model and view
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@DeleteMapping("/users/{id}")
    public ModelAndView delete(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
    	
                   
    	Users users = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Users with ID " + id + " not found"));
    	
    	usersRepository.delete(users);

    	ModelAndView modelAndView = new ModelAndView();

    	modelAndView.addObject("message", "Users " + id + " was deleted.");
    	modelAndView.setViewName("message");
        
        return modelAndView;
    }    
}
