package com.gameday.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class Users.
 */
@Entity
@Table(name = "USERS")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Users implements Serializable {
              
	/** The id. */
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
              
	/** The username. */
	@Column(nullable=false,length=50,unique=true)
	private String username;
  
	/** The password. */
	@Column (name="password", length=100)
	private String password;
  
	/** The confirm password. */
	@Transient
	private String confirmPassword;
  
	/** The password hint. */
	@Column(name="password_hint")
	private String passwordHint;
  
	/** The first name. */
	@Column(name="first_name",nullable=false,length=50)
	private String firstName;
              
    /** The last name. */
    @Column(name="last_name",nullable=false,length=50)
    private String lastName;
    
    /** The screen name. */
    @Column(name="screen_name",nullable=false,length=50,unique = true)
    private String screenName;
              
    /** The email. */
    @Column(name="email",nullable=false,length=50)
    private String email;
    
    /** The url. */
    @Column(name="url",nullable=true,length=75)
    private String url;
    
    /** The bio. */
    @Column(name="bio",nullable=true,length=400)
    private String bio;
    
    /** The image. */
    @Lob
    @Transient //@Column(name="image")
    private byte[] image;
              
    /** The roles. */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
    @JoinTable(
            name="users_role",
            joinColumns = { @JoinColumn( name="users_id") },
            inverseJoinColumns = @JoinColumn( name="role_id"))
    private Set<Role> roles = new HashSet<Role>();
    
    /** The role list. */
    @Transient
    private List<String> roleList;
                 
    /** The active. */
    @Column(name="account_active",nullable=false)
    private boolean active;
  
    /** The created at. */
    @Column(name = "created_on", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    /** The updated at. */
    @Column(name = "updated_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
              
    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
    	return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Long id) {
    	this.id = id;
    }    
	
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	/**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
    	this.password = password;
    }
	
    /**
     * Gets the confirm password.
     *
     * @return the confirm password
     */
    public String getConfirmPassword() {
    	return confirmPassword;
    }
	
    /**
     * Sets the confirm password.
     *
     * @param confirmPassword the new confirm password
     */
    public void setConfirmPassword(String confirmPassword) {
    	this.confirmPassword = confirmPassword;
    }
	
    /**
     * Gets the password hint.
     *
     * @return the password hint
     */
    public String getPasswordHint() {
    	return passwordHint;
    }
	
    /**
     * Sets the password hint.
     *
     * @param passwordHint the new password hint
     */
    public void setPasswordHint(String passwordHint) {
    	this.passwordHint = passwordHint;
    }
	
    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
    	return firstName;
    }
	
    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }
	
    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
    	return lastName;
    }
	
    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }    
	
    /**
     * Gets the screen name.
     *
     * @return the screen name
     */
    public String getScreenName() {
		return screenName;
	}

	/**
	 * Sets the screen name.
	 *
	 * @param screenName the new screen name
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
    	return email;
    }
	
    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
    	this.email = email;
    }    
	
    /**
     * Gets the url.
     *
     * @return the url
     */
    public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}	

	/**
	 * Gets the bio.
	 *
	 * @return the bio
	 */
	public String getBio() {
		return bio;
	}

	/**
	 * Sets the bio.
	 *
	 * @param bio the new bio
	 */
	public void setBio(String bio) {
		this.bio = bio;
	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public Set<Role> getRoles() {
    	return roles;
    }
	
    /**
     * Sets the roles.
     *
     * @param roles the new roles
     */
    public void setRoles(Set<Role> roles) {
    	this.roles = roles;
    } 
    
    /**
     * Gets the role list.
     *
     * @return the role list
     */
    public List<String> getRoleList() {    
    	
    	return roleList;    
    }
    
    /**
     * Sets the role list.
     *
     * @param roles the new role list
     */
    public void setRoleList(String roles) {
    	
    	List<String> roleList = new ArrayList<String>();
    	
    	for(String role : roles.split(",")) {
    		
    		roleList.add(role);
    	}
    	
    	this.roleList = roleList;
    } 

	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Gets the created at.
	 *
	 * @return the created at
	 */
	public Date getCreatedAt() {
    	return createdAt;
    }
	
    /**
     * Sets the created at.
     *
     * @param createdAt the new created at
     */
    public void setCreatedAt(Date createdAt) {
    	this.createdAt = createdAt;
    }
	
    /**
     * Gets the updated at.
     *
     * @return the updated at
     */
    public Date getUpdatedAt() {
    	return updatedAt;
    }
	
    /**
     * Sets the updated at.
     *
     * @param updatedAt the new updated at
     */
    public void setUpdatedAt(Date updatedAt) {
    	this.updatedAt = updatedAt;
    }
}

