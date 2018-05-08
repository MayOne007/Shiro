package com.dao;

import org.springframework.data.repository.CrudRepository;

import com.dao.plus.UserDaoPlus;
import com.entity.User;

public interface UserDao extends CrudRepository<User, Integer>, UserDaoPlus {
    
	public User findByUsername(String username);
}