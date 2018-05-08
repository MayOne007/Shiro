package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserRoleDao;
import com.entity.UserRole;
import com.service.UserRoleService;

import core.service.BaseServiceImpl;

@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, String> implements UserRoleService {
	
	@Autowired
	UserRoleDao userRoleDao;

	@Override
	public UserRole save(UserRole entity) {
		return userRoleDao.save(entity);
	}
	
}
