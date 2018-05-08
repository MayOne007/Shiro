package com.service;

import java.util.Set;

import com.entity.User;

import core.service.BaseService;

public interface UserService extends BaseService<User, Integer> {

	public User findByUsername(String username);

	public Set<String> findRolesByUsername(String username);
	
	public Set<String> findPermissionsByUsername(String username);
	
	/**
	 * 根据用户名，清除角色和权限缓存
	 * @param username
	 */
	public void clearUserCache(String username);
	
	/**
	 * 清除所有用户的角色和权限缓存
	 */
	public void clearUserCache();
}
