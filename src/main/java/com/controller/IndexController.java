package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shiro.UserRealm;

@Controller("indexController")
@RequestMapping("/index")
public class IndexController {
	
	@RequiresRoles("admin")
	@RequiresPermissions("show")
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public Object index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/test");		
		return mv;
	}
	
	
	/**
	 * 清除当前用户的shiro缓存
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/clear", method = RequestMethod.GET)
	public String clear(HttpServletRequest request) {
		RealmSecurityManager rsm = (RealmSecurityManager)SecurityUtils.getSecurityManager();  
	    UserRealm userRealm = (UserRealm)rsm.getRealms().iterator().next();

	    Subject subject = SecurityUtils.getSubject();
	    userRealm.clearUserCache(subject.getPrincipals().toString());
	    
		return "clear user:" + subject.getPrincipal();
	}
	
	
	
}