package com.member.service.frame.core.sys;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.member.service.common.config.FrameConfig;
import com.member.service.managementService.login.po.NmsUser;
import com.member.service.util.FrameDatabaseUtil;

 @SuppressWarnings("unchecked") 
 public class FrameSessionManager implements HttpSessionListener {


	// 将sesison 与线程绑�?
	private static Map<String, HttpSession> threadSession = new HashMap<String, HttpSession>();
	private static Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();

	public void sessionCreated(HttpSessionEvent event) {
		sessions.put(event.getSession().getId().toString(), event.getSession());
		//log.debug("添加session" + sessions);
	}

	public static Map<String, HttpSession> getSessions() {
		return sessions;
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		if (sessions.containsKey(session.getId().toString())) {
			sessions.remove(session.getId().toString());
			//log.debug("移除session,剩余session�?" + sessions);

			
		}
		//移除该session绑定的线�?
		if(threadSession.containsValue(session))
		{
			Map<String, HttpSession> newSessions =(Map<String, HttpSession>)(((HashMap<String, HttpSession>) threadSession).clone());
			Iterator<Entry<String, HttpSession>> it = newSessions.entrySet()
			.iterator();
	         while (it.hasNext()) {
			    Entry<String, HttpSession> entry = it.next();
			     HttpSession sessionT = entry.getValue();
			    if(session== sessionT)
			    {
			    	threadSession.remove(entry.getKey());
			    }
		
	       }
	         
		}
	      //修改用户登录状�?
		Object logonUserO = session.getAttribute("logonUser");
		if (logonUserO != null) {
			
			Map<String,Object> logonUser = (Map) logonUserO;
			int userId = Integer.parseInt(logonUser.get("id").toString());
			NmsUser nmsUser = (NmsUser) FrameDatabaseUtil.getBaseDaoImpl().getEntityById(NmsUser.class,
					userId);
			nmsUser.setUserStatus(FrameConfig.userStatus_Offline);
			FrameDatabaseUtil.getBaseDaoImpl().saveOrUpdate(nmsUser);
			//登出日志记录
		}

	}
     /**
      * 添加当前线程与session的绑�?
      * @param session
      */
	public static void addThreadSession(HttpSession session) {
		threadSession.put(String.valueOf(Thread.currentThread().getId()), session);

	}
	/**
	 * 移除当前线程与session的绑�?
	 */
	public static void removeThreadSession() {
		String threadId=String.valueOf(Thread.currentThread().getId());
		if(threadSession.containsKey(threadId))
		{
			threadSession.remove(threadId);
			//log.debug("线程session 剩余�?+threadSession.size());
		}

	}
	/**
	 * 获取当前登录的用�?
	 * @return
	 */
	
	public static  Map<String,Object>  getCurrentUser( ) {
		HttpSession currentSession = threadSession.get(String.valueOf(Thread.currentThread().getId()));
		if(currentSession==null)
		{
			return null;
		}
		Object logonUserO = currentSession.getAttribute("logonUser");
		if(logonUserO==null)
		{
			return null;
		}
		return (Map) logonUserO;

	}
	/**
	 * 获取当前登录的用户Id
	 * @return
	 */
	
	public static  Integer getCurrentUserId( ) {
		Map<String, Object> currentUser = getCurrentUser();
		 if(currentUser==null)
		  {
			  return null;
		  }
	  Object userIdO=	currentUser.get("id");
	  if(userIdO==null)
	  {
		  return null;
	  }
		try {
			Integer userId=Integer.parseInt(userIdO.toString());
			return userId;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
			
		}
		

	}
	public  static void  checkSession()
	{
		try {
			
			HashMap<String, HttpSession> sessions = (HashMap<String, HttpSession>) FrameSessionManager.getSessions();
			Map<String, HttpSession> newSessions = (Map<String, HttpSession>) sessions.clone();
			Iterator<Entry<String, HttpSession>> it = newSessions.entrySet()
			.iterator();
	    while (it.hasNext()) {
		   try {
		    Entry<String, HttpSession> entry = it.next();
			HttpSession session = entry.getValue();
			Object logonUserO = session.getAttribute("logonUser");
			if (logonUserO != null) {
			
				Map<String,Object> logonUser=(Map)logonUserO;
				//判断用户是否异常�?��
				Object userLastHeartbeatTimeO = logonUser
						.get(FrameConfig.userLastHeartbeatTime);
				if (userLastHeartbeatTimeO != null) {
					Date userLastHeartbeatTime = (Date) userLastHeartbeatTimeO;
					long cha = (new Date().getTime() - userLastHeartbeatTime
							.getTime()) / 1000;
					// 用户已经 心跳超时，可以判断用户已经异常�?�?了，�?��sesison
					if (cha > FrameConfig.userHeartbeatTimeout) {
						session.invalidate();
					}
				}
				//判断用户挂机超时
				Object userLastRequestTimeO = logonUser
				.get(FrameConfig.userLastRequestTime);
				if (userLastRequestTimeO != null) {
					Date userLastRequestTime = (Date) userLastRequestTimeO;
					long cha = (new Date().getTime() - userLastRequestTime
							.getTime()) / (1000*60);
					// 用户已经 心跳超时，可以判断用户已经异常�?�?了，�?��sesison
					if (cha > FrameConfig.userLeaveMaxTime) {
						session.invalidate();
					}
				}
				
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
