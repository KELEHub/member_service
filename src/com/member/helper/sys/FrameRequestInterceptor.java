package com.member.helper.sys;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.member.common.config.FrameConfig;


 public class FrameRequestInterceptor extends HandlerInterceptorAdapter {
	/** 用户心跳的servlet地址*/
	private final static String userHeartbeatRequest="LoginController/userHeartbeat";



	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		Object logonUserO = session.getAttribute("logonUser");
		String path = request.getServletPath();

		if (logonUserO == null) {
			return false;
		}
		Map<String,Object> logonUser=(Map<String,Object> )logonUserO;
		if(path!=null)
		{
			if(!path.contains(userHeartbeatRequest))
			{
				logonUser.put(FrameConfig.userLastRequestTime, new Date());
			}
			
		}
		request.setAttribute("requestInTime", new Date());
		FrameSessionManager.addThreadSession(session);
		return true;
	}




	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}





}
