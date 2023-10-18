package com.gameday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gameday.model.Users;

// TODO: Auto-generated Javadoc
/**
 * The Interface UsersRepository.
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	
	/**
	 * Gets the users by name.
	 *
	 * @param name the name
	 * @return the users by name
	 */
	@Query("SELECT u FROM Users u WHERE u.username = :username")
    public Users getUsersByName(@Param("username") String username);
	
	/**
	 * Gets the users by id.
	 *
	 * @param id the id
	 * @return the users by id
	 */
	@Query("SELECT u FROM Users u WHERE u.id = :id")
    public Users getUsersById(@Param("id") Long id);
	
	/**
	 * Gets the users with URls.
	 *
	 * @return the users with URls
	 */
	@Query(value = "select * from users where url <> '' order by screen_name asc", nativeQuery = true)
	public List<Users> getUsersWithURLs();
}

