package com.dao;

import org.springframework.data.repository.CrudRepository;

import com.dao.plus.PermissionDaoPlus;
import com.entity.Permission;

public interface PermissionDao extends CrudRepository<Permission, Integer>, PermissionDaoPlus {
    
}