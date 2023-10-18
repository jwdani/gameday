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
import com.gameday.model.Role;
import com.gameday.repository.RoleRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class RoleController.
 */
@Controller
@RequestMapping(GamedayConstants.REQUEST_MAPPTING_ADMIN)
public class RoleController {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(RoleController.class);
	
	/** The role repository. */
	@Autowired
    private RoleRepository roleRepository;
	
	/**
	 * List roles.
	 *
	 * @return the model and view
	 */
	@GetMapping("/role")
    public ModelAndView listRoles() {
    	
    	ModelAndView modelAndView = new ModelAndView();
    	
    	modelAndView.addObject("roles", roleRepository.findAll());
    	modelAndView.setViewName("/listRoles");
        
        return modelAndView;
    }
    
	/**
	 * Creates the role.
	 *
	 * @param role the role
	 * @return the model and view
	 */
	@PostMapping("/role")
    public ModelAndView createRole(@ModelAttribute("role") Role role) {
		
		Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		role.setCreatedAt(now);
		role.setUpdatedAt(now);
    	
    	roleRepository.save(role);
    	
    	ModelAndView modelAndView = new ModelAndView();
    	
    	modelAndView.addObject("message", "Role " + role.getName() + " was created.");
    	modelAndView.setViewName("message");
        
        return modelAndView;
    }
    
	/**
	 * Gets the role.
	 *
	 * @param id the id
	 * @return the role
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping("/role/{id}")
    public ModelAndView getRole(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
                   
    	Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role with ID " + id + " not found"));
    	
    	ModelAndView modelAndView = new ModelAndView();
    	
    	modelAndView.addObject("role", role);
    	modelAndView.setViewName("/updateRole");
        
        return modelAndView;
    }	
	
	/**
	 * Update.
	 *
	 * @param role the role
	 * @return the model and view
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@PutMapping("/role")  
    public ModelAndView update(@ModelAttribute("role") Role role) throws ResourceNotFoundException {  
		
		Role oldRole = roleRepository.findById(role.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Role with name " + role.getName() + " not found"));
		
		role.setCreatedAt(oldRole.getCreatedAt());
		role.setUpdatedAt(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		
		roleRepository.save(role);
         
		ModelAndView modelAndView = new ModelAndView();
		
    	modelAndView.addObject("message", "Role " + role.getName() + " was saved.");
    	modelAndView.setViewName("message");
    	
    	return modelAndView;
    } 
	
	/**
	 * Delete.
	 *
	 * @param role the role
	 * @return the model and view
	 */
	@DeleteMapping("/role")
    public ModelAndView delete(@ModelAttribute("role") Role role) {
        	
    	roleRepository.delete(role);

    	ModelAndView modelAndView = new ModelAndView();

    	modelAndView.addObject("message", "Role " + role.getName() + " was deleted.");
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
	@DeleteMapping("/role/{id}")
    public ModelAndView delete(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
    	
                   
    	Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role with ID " + id + " not found"));
    	
    	roleRepository.delete(role);

    	ModelAndView modelAndView = new ModelAndView();

    	modelAndView.addObject("message", "Role " + id + " was deleted.");
    	modelAndView.setViewName("message");
        
        return modelAndView;
    }    
}
