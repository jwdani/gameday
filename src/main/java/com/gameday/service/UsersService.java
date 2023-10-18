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
import com.gameday.model.Users;
import com.gameday.repository.UsersRepository;



// TODO: Auto-generated Javadoc
/**
 * The Class UsersService.
 */
@Service
public class UsersService {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersService.class);
	
	/** The users repository. */
	@Autowired
	private UsersRepository usersRepository;	
	
	/**
	 * Exists by id.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	private boolean existsById(Long id) {
		
        return usersRepository.existsById(id);
    }
	
	/**
	 * Exists by name.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	private boolean existsByName(String name) {
		
		if(usersRepository.getUsersByName(name) != null)
			return true;
		
        return false;
    }
    
    /**
     * Find by id.
     *
     * @param id the id
     * @return the users
     * @throws ResourceNotFoundException the resource not found exception
     */
    public Users findById(Long id) throws ResourceNotFoundException {
    	
        Users users = usersRepository.findById(id).orElse(null);
        
        if (users==null) {
            throw new ResourceNotFoundException("Cannot find Users with id: " + id);
        }
        else return users;
    }
    
    /**
     * Find by name.
     *
     * @param name the name
     * @return the users
     * @throws ResourceNotFoundException the resource not found exception
     */
    public Users findByName(String name) throws ResourceNotFoundException {
    	
        Users users = usersRepository.getUsersByName(name);
        
        if (users==null) {
            throw new ResourceNotFoundException("Cannot find Users with name: " + name);
        }
        else return users;
    }
    
    /**
     * Find all.
     *
     * @return the list
     */
    public List<Users> findAll() {
        
        return usersRepository.findAll();
    }
      
    /**
     * Save.
     *
     * @param users the users
     * @return the users
     * @throws BadResourceException the bad resource exception
     * @throws ResourceAlreadyExistsException the resource already exists exception
     */
    public Users save(Users users) throws BadResourceException, ResourceAlreadyExistsException {
    	
        if (!StringUtils.isEmpty(users.getUsername())) {
        	
        	if (users.getUsername() != null && existsByName(users.getUsername())) { 
            	
                throw new ResourceAlreadyExistsException("Users with username: " + users.getUsername() + " already exists");
            }
        	else if (users.getId() != null && existsById(users.getId())) { 
            	
                throw new ResourceAlreadyExistsException("Users with id: " + users.getId() + " already exists");
            }
            
            return usersRepository.save(users);
        }
        else {
        	
            BadResourceException badResourceException = new BadResourceException("Failed to save users");
            badResourceException.addErrorMessage("Users is null or empty");            
            throw badResourceException;
        }
    }
    
    /**
     * Update.
     *
     * @param users the users
     * @return the users
     * @throws BadResourceException the bad resource exception
     * @throws ResourceNotFoundException the resource not found exception
     */
    public Users update(Users users) throws BadResourceException, ResourceNotFoundException {
    	
        if (!StringUtils.isEmpty(users.getUsername())) {
        	
            if (!existsById(users.getId())) {
                throw new ResourceNotFoundException("Cannot find Users with id: " + users.getId());
            }
            
            return usersRepository.save(users);
        }
        else {
        	
            BadResourceException badResourceException = new BadResourceException("Failed to save users");
            badResourceException.addErrorMessage("Users is null or empty");            
            throw badResourceException;
        }
    }
    
    /**
     * Delete.
     *
     * @param users the users
     * @throws ResourceNotFoundException the resource not found exception
     */
    public void delete(Users users) throws ResourceNotFoundException {
    	
        if (!existsById(users.getId())) 
            throw new ResourceNotFoundException("Cannot find users with id: " + users.getId());        
        else 
            usersRepository.delete(users);        
    } 
	
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	public void deleteById(Long id) throws ResourceNotFoundException {
    	
        if (!existsById(id)) 
            throw new ResourceNotFoundException("Cannot find users with id: " + id);        
        else 
            usersRepository.deleteById(id);        
    }    

	/**
	 * Count.
	 *
	 * @return the long
	 */
	public Long count() {
    	
        return usersRepository.count();
    }
}

