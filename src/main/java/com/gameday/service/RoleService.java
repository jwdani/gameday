package com.gameday.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameday.exception.BadResourceException;
import com.gameday.exception.ResourceAlreadyExistsException;
import com.gameday.exception.ResourceNotFoundException;
import com.gameday.model.Role;
import com.gameday.repository.RoleRepository;



// TODO: Auto-generated Javadoc
/**
 * The Class RoleService.
 */
@Service
public class RoleService {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);
	
	/** The role repository. */
	@Autowired
	private RoleRepository roleRepository;	
	
	/**
	 * Exists by id.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	private boolean existsById(Long id) {
		
        return roleRepository.existsById(id);
    }
	
	/**
	 * Exists by name.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	private boolean existsByName(String name) {
		
		if(roleRepository.getRoleByName(name) != null)
			return true;
		
        return false;
    }
    
    /**
     * Find by id.
     *
     * @param id the id
     * @return the role
     * @throws ResourceNotFoundException the resource not found exception
     */
    public Role findById(Long id) throws ResourceNotFoundException {
    	
        Role role = roleRepository.findById(id).orElse(null);
        
        if (role==null) {
            throw new ResourceNotFoundException("Cannot find Role with id: " + id);
        }
        else return role;
    }
    
    /**
     * Find by name.
     *
     * @param name the name
     * @return the role
     * @throws ResourceNotFoundException the resource not found exception
     */
    public Role findByName(String name) throws ResourceNotFoundException {
    	
        Role role = roleRepository.getRoleByName(name);
        
        if (role==null) {
            throw new ResourceNotFoundException("Cannot find Role with name: " + name);
        }
        else return role;
    }
    
    /**
     * Find all.
     *
     * @return the list
     */
    public List<Role> findAll() {
        
        return roleRepository.findAll();
    }
      
    /**
     * Save.
     *
     * @param role the role
     * @return the role
     * @throws BadResourceException the bad resource exception
     * @throws ResourceAlreadyExistsException the resource already exists exception
     */
    public Role save(Role role) throws BadResourceException, ResourceAlreadyExistsException {
    	
        if (!StringUtils.isEmpty(role.getName())) {
        	
        	if (role.getName() != null && existsByName(role.getName())) { 
            	
                throw new ResourceAlreadyExistsException("Role with name: " + role.getName() + " already exists");
            }
        	else if (role.getId() != null && existsById(role.getId())) { 
            	
                throw new ResourceAlreadyExistsException("Role with id: " + role.getId() + " already exists");
            }
            
            return roleRepository.save(role);
        }
        else {
        	
            BadResourceException badResourceException = new BadResourceException("Failed to save role");
            badResourceException.addErrorMessage("Role is null or empty");            
            throw badResourceException;
        }
    }
    
    /**
     * Update.
     *
     * @param role the role
     * @return the role
     * @throws BadResourceException the bad resource exception
     * @throws ResourceNotFoundException the resource not found exception
     */
    public Role update(Role role) throws BadResourceException, ResourceNotFoundException {
    	
        if (!StringUtils.isEmpty(role.getName())) {
        	
            if (!existsById(role.getId())) {
                throw new ResourceNotFoundException("Cannot find Role with id: " + role.getId());
            }
            
            return roleRepository.save(role);
        }
        else {
        	
            BadResourceException badResourceException = new BadResourceException("Failed to save role");
            badResourceException.addErrorMessage("Role is null or empty");            
            throw badResourceException;
        }
    }
    
    /**
     * Delete.
     *
     * @param role the role
     * @throws ResourceNotFoundException the resource not found exception
     */
    public void delete(Role role) throws ResourceNotFoundException {
    	
        if (!existsById(role.getId())) 
            throw new ResourceNotFoundException("Cannot find role with id: " + role.getId());        
        else 
            roleRepository.delete(role);        
    } 
	
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	public void deleteById(Long id) throws ResourceNotFoundException {
    	
        if (!existsById(id)) 
            throw new ResourceNotFoundException("Cannot find role with id: " + id);        
        else 
            roleRepository.deleteById(id);        
    }    

	/**
	 * Count.
	 *
	 * @return the long
	 */
	public Long count() {
    	
        return roleRepository.count();
    }
}

