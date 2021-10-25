package com.zkteco.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zkteco.user.entity.User;


public interface UserRepository extends JpaRepository<User, String>{
	public User findByFirstName(String userName);

	@Query(value = "Select t from User t where t.firstName like ?1 OR t.lastName like ?1 OR t.address like ?1 OR t.email_Id like ?1 OR t.userCode like ?1 OR t.gender like ?1")
	public Page<User> userContaining(String search, Pageable paging);

	@Query(value = "Select t from User t where t.firstName like ?1")
	public Object fetchUserById(String string);
	
}






