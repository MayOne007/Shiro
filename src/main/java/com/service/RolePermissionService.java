package com.service;

import com.entity.RolePermission;

import core.service.BaseService;

public interface RolePermissionService extends BaseService<RolePermission, String> {

	public RolePermission save(RolePermission userRole);
	
}
