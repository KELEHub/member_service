package com.member.helper.sys;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.member.common.config.FrameConfig;
import com.member.helper.dao.BaseDao;
import com.member.util.FrameDatabaseUtil;
import com.member.util.FrameSpringContextUtil;

@SuppressWarnings("serial")
public class FrameInitServlet extends HttpServlet {

	ThreadPoolTaskExecutor frameKeepPool;
	BaseDao baseDao;
	 /**
     * 定义ServletConfig对象来接收配置的初始化参�?
    */
	 @Override
	   public void init(ServletConfig config) throws ServletException {
		 frameKeepPool = (ThreadPoolTaskExecutor) FrameSpringContextUtil.getBean("FrameKeepPool");
		 baseDao=FrameDatabaseUtil.getBaseDaoImpl();
		 checkSession();
		 //glossaryInit();
    }
	
	
//	private void snmpInit() {
//		try {
//			FrameSnmpTrapListener frameSnmpTrapListener = (FrameSnmpTrapListener) FrameSpringContextUtil
//					.getBean("frameSnmpTrapListener");
//			frameSnmpTrapListener.init();
//		} catch (Exception e) {
//			e.printStackTrace();
//			LogUtil.logError("启动告警 监听异常",log, e);
//		}
//
//		
//	}
	/**
	 * 通过�?��用户session的心跳时间，来判断用户是否异常�?出�?如果异常�?�� �?��session
	 */
	 private void checkSession() {
		 try {
			frameKeepPool.execute(new Runnable() {
					public void run() {
						while (true) {
							try {
								Thread.sleep(FrameConfig.checkSessionInterval * 1000);
								FrameSessionManager.checkSession();
							} catch (Exception e) {
								
								e.printStackTrace();
							}
						}

					
						
					}
				});
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	

}
