package com.dao;

import org.springframework.data.repository.CrudRepository;

import com.dao.plus.RoleDaoPlus;
import com.entity.Role;

public interface RoleDao extends CrudRepository<Role, Integer>, RoleDaoPlus {
    
}