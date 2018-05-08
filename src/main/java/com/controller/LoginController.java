package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.contant.SystemContant;

import core.model.MessageResult;

@Controller("loginController")
@RequestMapping("/login")
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/login");
		mv.addObject("msg", request.getParameter(SystemContant.KICKOUT_MSG));
		return mv;
	}
	

	@ResponseBody
	@RequestMapping(value="in", method = RequestMethod.POST)
	public Object in(HttpServletRequest request) {
		String username = request.getParameter("username");
        String password = request.getParameter("password");
	
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {  
            subject.login(token);
        } catch (UnknownAccountException e) {
        	logger.error(e);
        	return new MessageResult(false,"用户名或密码出错");
        } catch (IncorrectCredentialsException e) {
        	logger.error(e);
        	return new MessageResult(false,"用户名或密码出错");
        } catch (AuthenticationException e) {  
            logger.error(e);
            return new MessageResult(false,"登录出错");
        }
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        String url = "";
    	if(savedRequest !=null){
    		url = savedRequest.getRequestUrl();    		
    	}
		return new MessageResult(true,url);
	}
	
	@ResponseBody
	@RequestMapping(value="out", method = RequestMethod.POST)
	public ModelAndView out(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/login");
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return mv;
	}
	
}