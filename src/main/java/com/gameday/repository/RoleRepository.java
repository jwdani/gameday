package com.gameday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gameday.model.Role;


// TODO: Auto-generated Javadoc
/**
 * The Interface RoleRepository.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	/**
	 * Gets the role list.
	 *
	 * @return the role list
	 */
	@Query("SELECT r.name FROM Role r")
    public List<String> getRoleList();
	
	/**
	 * Gets the role by name.
	 *
	 * @param name the name
	 * @return the role by name
	 */
	@Query("SELECT r FROM Role r WHERE r.name = :name")
    public Role getRoleByName(@Param("name") String name);
}
