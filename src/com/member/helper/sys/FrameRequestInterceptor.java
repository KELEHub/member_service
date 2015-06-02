package com.member.helper.sys;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.member.common.config.FrameConfig;


 public class FrameRequestInterceptor extends HandlerInterceptorAdapter {
	private final static String localhost="0:0:0:0:0:0:0:1";
	/** 用户心跳的servlet地址*/
	private final static String userHeartbeatRequest="LoginController/userHeartbeat";



	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getServletPath();
		HttpSession session = request.getSession();
		Object logonUserO = session.getAttribute("logonUser");
		String path = request.getServletPath();

		if (logonUserO == null) {
			
			// 说明是未登陆的，非正式的请求后台。一般情况为在URL中直接加上xxx/xx.do
			if(!path.contains(userHeartbeatRequest))
			{

				
				
				
			}
			else{
			   response.getWriter().print("logout");
			}			
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
	@SuppressWarnings("unused")
	private void PrintRedirect(HttpServletRequest request,
			HttpServletResponse response, boolean isRedirect) {
		response.setContentType("text/html; charset=utf-8");
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (isRedirect) {
				out.print("<html><title>Info</title>TimeOut,Redirecting...<script>window.open ('"
						+ request.getContextPath()
						+ "/"
						+ "index.jsp')"
						+ "</script></html>");
			} else {

				out.print("<html><title>Info</title>TimeOut,Click<a href='"
						+ request.getContextPath() + "/" + "index.jsp"
						+ "'>To Login</html>");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	/**
	 * 获取远程访问IP
	 * 
	 * @return
	 */
	protected String getRemoteIpByRequest(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteHost();
		}
		if(ip.equals(localhost))
		{
			ip="localhost";
		}

		return ip;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * controller方法结束后进�?
	 */
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		recordRequestTime(request);
		// 记录日志
		recordOperateLog(request);
		
		
		FrameSessionManager.removeThreadSession();


	}

	private void recordOperateLog(HttpServletRequest request) {
		@SuppressWarnings("unused")
		String requestPath = request.getServletPath();

	}

	/**
	 * 记录请求耗时
	 * 
	 * @param request
	 */
	private void recordRequestTime(HttpServletRequest request) {
		Object requestInTimeO = request.getAttribute("requestInTime");
		if (requestInTimeO != null) {
			Date requestInTime = (Date) requestInTimeO;
			long cha = new Date().getTime() - requestInTime.getTime();
			 String path = request.getServletPath();
			if(!path.contains(userHeartbeatRequest))
			{
			 
			}
		}
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

}
