package com.dao;

import org.springframework.data.repository.CrudRepository;

import com.dao.plus.UserRoleDaoPlus;
import com.entity.UserRole;

public interface UserRoleDao extends CrudRepository<UserRole, String>, UserRoleDaoPlus {

}