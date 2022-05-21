package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface userRepository extends JpaRepository<User, Long>{
	User findByNameAndPassword(String name,String password);
	User findByName(String name);
	//Login findByUsernameAndPassword(String username, String password);
}