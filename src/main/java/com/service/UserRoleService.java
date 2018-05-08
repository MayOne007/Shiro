package com.service;

import com.entity.UserRole;

import core.service.BaseService;

public interface UserRoleService extends BaseService<UserRole, String> {

	public UserRole save(UserRole userRole);
	
}
