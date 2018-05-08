package com.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dao.DictDao;
import com.dao.RoleDao;
import com.dao.UserDao;
import com.entity.RolePermission;
import com.entity.User;
import com.entity.UserRole;
import com.service.UserService;

import core.service.BaseServiceImpl;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	RoleDao roleDao;

	@Autowired
	DictDao daoDao;
	
	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Cacheable(value="shiro_user_cache:role", key="#username")
	@Override
	public Set<String> findRolesByUsername(String username) {
		Set<String> roles = new HashSet<String>();
		User user = this.findByUsername(username);
		if(user==null) {
			return roles;
		}
		List<UserRole> userRoles = user.getUserRoleList();
		for(UserRole userRole:userRoles) {
			roles.add(userRole.getRole().getName());
		}
		return roles;
	}

	@Cacheable(value="shiro_user_cache:permission", key="#username")
	@Override
	public Set<String> findPermissionsByUsername(String username) {
		Set<String> permissions = new HashSet<String>();
		User user = this.findByUsername(username);
		if(user==null) {
			return permissions;
		}
		List<UserRole> userRoles = user.getUserRoleList();
		for(UserRole userRole:userRoles) {
			List<RolePermission> rolePermissions= userRole.getRole().getRolePermissionList();
			for(RolePermission rolePermission:rolePermissions) {
				permissions.add(rolePermission.getPermission().getName());
			}
		}		
		return permissions;
	}
	
	@CacheEvict(value={"shiro_user_cache:role","shiro_user_cache:permission"}, key="#username")
    public void clearUserCache(String username) {
    	
    }
    
    @CacheEvict(value={"shiro_user_cache:role","shiro_user_cache:permission"}, allEntries=true)
    public void clearUserCache() {
    	
    }
	
}
