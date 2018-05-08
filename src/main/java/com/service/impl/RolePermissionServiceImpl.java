package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RolePermissionDao;
import com.entity.RolePermission;
import com.service.RolePermissionService;

import core.service.BaseServiceImpl;

@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission, String> implements RolePermissionService {
	
	@Autowired
	RolePermissionDao rolePermissionDao;

	@Override
	public RolePermission save(RolePermission entity) {
		return rolePermissionDao.save(entity);
	}
	
}
