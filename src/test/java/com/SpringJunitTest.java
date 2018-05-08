package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.entity.Permission;
import com.entity.Role;
import com.entity.RolePermission;
import com.entity.User;
import com.entity.UserRole;
import com.service.DictService;
import com.service.RolePermissionService;
import com.service.UserRoleService;

/** 声明用的是Spring的测试类 **/
@RunWith(SpringJUnit4ClassRunner.class)
/** 声明spring主配置文件位置 **/
@ContextConfiguration(locations={"classpath*:applicationContext*.xml"})
/** 事务自动回滚  **/
/*@Rollback*/
public class SpringJunitTest extends EntityManagerTestCase{
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RolePermissionService rolePermissionService;	
	
	@Autowired
	private DictService dictService;
	
	/*@Test
	public void testTxAaop() {
		dictService.txOne();
	}*/
	
	@Test  
	public void testData() {  
		User user = new User();
		user.setUsername("test");
		user.setPassword("123456");

		Role role = new Role();
		role.setName("admin");
		
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		userRoleService.save(userRole);
		
		Permission permission = new Permission();
		permission.setName("show");

		RolePermission rolePermission = new RolePermission();
		rolePermission.setRole(role);
		rolePermission.setPermission(permission);
		rolePermissionService.save(rolePermission);
		
		permission = new Permission();
		permission.setName("add");

		rolePermission = new RolePermission();
		rolePermission.setRole(role);
		rolePermission.setPermission(permission);
		rolePermissionService.save(rolePermission);
	}
}
