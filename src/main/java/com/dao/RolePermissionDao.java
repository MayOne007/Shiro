package com.dao;

import org.springframework.data.repository.CrudRepository;

import com.dao.plus.RolePermissionDaoPlus;
import com.entity.RolePermission;

public interface RolePermissionDao extends CrudRepository<RolePermission, String>, RolePermissionDaoPlus {
    
}