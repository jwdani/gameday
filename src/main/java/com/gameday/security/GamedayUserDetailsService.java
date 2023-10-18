package com.gameday.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gameday.model.Role;
import com.gameday.model.Users;
import com.gameday.repository.UsersRepository;

@Service
public class GamedayUserDetailsService implements UserDetailsService {
	
	private static final Logger LOGGER = LogManager.getLogger(GamedayUserDetailsService.class);
	
	@Autowired
    private UsersRepository usersRepository;	
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = usersRepository.getUsersByName(username);

        if (users == null) {

            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return User.withUsername(username)
                   .password(users.getPassword())
                   .authorities(mapRolesToAuthorities(users.getRoles()))
                   .build();
    }
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		
        Collection<? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        
        return mapRoles;
    }
}
