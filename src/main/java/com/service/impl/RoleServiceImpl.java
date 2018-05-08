package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RoleDao;
import com.entity.Role;
import com.service.RoleService;

import core.service.BaseServiceImpl;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> implements RoleService {
	
	@Autowired
	RoleDao roleDao;
	
}
