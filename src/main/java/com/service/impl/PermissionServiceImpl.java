package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PermissionDao;
import com.entity.Permission;
import com.service.PermissionService;

import core.service.BaseServiceImpl;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Integer> implements PermissionService {
	
	@Autowired
	PermissionDao permissionDao;

}
