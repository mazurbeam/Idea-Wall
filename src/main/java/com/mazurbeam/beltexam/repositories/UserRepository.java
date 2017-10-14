package com.mazurbeam.beltexam.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mazurbeam.beltexam.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);
	
	@Query(value="SELECT * FROM users WHERE id=?1", nativeQuery=true)
	User findById(Long id);
}
