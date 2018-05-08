package com.shiro;

import java.io.Serializable;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import com.contant.SystemContant;

public class KickoutFilter extends AccessControlFilter {

	/**
	 * 踢出前一个登陆或后一个登陆的同一用户
	 */
	private boolean kickoutBefore = true;
	/**
	 * 同一个用户的最大同时登陆数
	 */
	private int maxUserCount = 1;

	/**
	 * 保存同一用户登录数<用户名，sessionId队列>
	 */
	private Cache onliceCache;

	/**
	 * 被踢出的登录<用户名，sessionId队列>
	 */
	private Cache kickoutCache;

	@Autowired
	@Qualifier("redisCacheManager")
	public void setCacheManager(CacheManager cacheManager) {
		this.onliceCache = cacheManager.getCache("shiro_user_online");
		this.kickoutCache = cacheManager.getCache("shiro_user_kickout");
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		Session session = subject.getSession();
		String username = (String) subject.getPrincipal();
		Serializable sessionId = session.getId();
		//如果没有登录，直接进行之后的流程
		if (!subject.isAuthenticated() && !subject.isRemembered()) {			
			return true;
		}
		
		//先判断当前用户是否被踢出
		Deque<Serializable> kickoutDeque = getKickoutDeque(username);
		for (Serializable id : kickoutDeque) {
			if (sessionId.equals(id)) {				
				subject.logout();
				//踢出后在kickoutDeque中删除当前sessionId
				System.out.println("踢出sessionId：" + id);
				kickoutDeque.remove(id);
				kickoutCache.put(username, kickoutDeque);
				//跳转到登录页
				Map<String, String> params = new HashMap<String, String>();
				params.put(SystemContant.KICKOUT_MSG, "kick out login");
				WebUtils.issueRedirect(request, response, "/login", params);
				return false;
			}
		}

		//如果队列里没有此sessionId，放入队列
		Deque<Serializable> onlineDeque = getOnlineDeque(username);
		if (!onlineDeque.contains(sessionId)) {
			onlineDeque.push(sessionId);
		}
		//判断当前用户在线数目是否超出maxUserCount，然后把超出的用户从onlineDeque移到kickoutDeque
		while (onlineDeque.size() > maxUserCount) {
			Serializable kickoutSessionId = null;
			if (kickoutBefore) {
				kickoutSessionId = onlineDeque.removeLast();
				kickoutDeque.push(kickoutSessionId);
			} else {
				kickoutSessionId = onlineDeque.removeFirst();
				kickoutDeque.push(kickoutSessionId);
			}

		}
		onliceCache.put(username, onlineDeque);
		kickoutCache.put(username, kickoutDeque);

		return true;
	}

	/**
	 * 获取在线用户
	 * 
	 * @param username
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Deque<Serializable> getOnlineDeque(String username) {
		Deque<Serializable> onlineDeque;
		if (onliceCache.get(username) == null) {
			onlineDeque = new LinkedList<Serializable>();
		} else {
			onlineDeque = (Deque<Serializable>) onliceCache.get(username).get();
		}
		return onlineDeque;
	}

	/**
	 * 获取被踢出的用户
	 * 
	 * @param username
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Deque<Serializable> getKickoutDeque(String username) {
		Deque<Serializable> kickoutDeque;
		if (kickoutCache.get(username) == null) {
			kickoutDeque = new LinkedList<Serializable>();
		} else {
			kickoutDeque = (Deque<Serializable>) kickoutCache.get(username).get();
		}
		return kickoutDeque;
	}

}