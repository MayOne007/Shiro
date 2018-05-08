package com.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.entity.User;
import com.service.UserService;

public class UserRealm extends AuthorizingRealm {

	@Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();       
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRolesByUsername(username));
        authorizationInfo.setStringPermissions(userService.findPermissionsByUsername(username));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        User user = userService.findByUsername(username);
        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        		user.getUsername(), //用户名
        		user.getPassword(), //密码
                //ByteSource.Util.bytes(adminUser.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

    /*public void clearUserCache(PrincipalCollection principals) {
    	super.clearCachedAuthorizationInfo(principals);
    	super.clearCachedAuthenticationInfo(principals);
    	userService.clearUserCache(principals.toString());
    }*/
    public void clearUserCache(String uername) {
    	/*super.clearCachedAuthorizationInfo(principals);
    	super.clearCachedAuthenticationInfo(principals);*/
    	userService.clearUserCache(uername);
    }

    public void clearUserCache() {
    	/*super.getAuthorizationCache().clear();
    	super.getAuthorizationCache().clear();*/
    	userService.clearUserCache();
    }

}