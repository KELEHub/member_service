package com.member.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.member.util.LogUtil;

@Component("frameSpringContextInitListener")
public class FrameSpringContextInitListener implements
		ApplicationListener<ContextRefreshedEvent> {// ContextRefreshedEvent为初始化完毕事件，spring还有很多事件可以利用

	private final static Log log = LogFactory
			.getLog(FrameSpringContextInitListener.class);
	public static Boolean springContextInitIsFinished = false;

	/**
	 * 当一个ApplicationContext被初始化或刷新触发 使用spring
	 * MVC之后的webApplicationontext会两次调用此的方法 在web 项目中（spring
	 * mvc），系统会存在两个容器，一个是root application context ,另一个就是我们自己的
	 * projectName-servlet context（作为root application context的子容器）。
	 */

	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getDisplayName().equals(
				"Root WebApplicationContext")) {
			try {
				log.warn("spring 容器初始化完毕");
				springContextInitIsFinished = true;
			} catch (Exception e) {
				e.printStackTrace();
				LogUtil.logError(log, e);
			}

		}
	}

	/**
	 * 等待spring 容器初始化完成
	 */
	public static void waitSpringContextInit() {
		try {
			if (springContextInitIsFinished) {
				return;
			} else {
				while (true) {
					if (springContextInitIsFinished) {
						break;
					} else {
						Thread.sleep(1 * 1000);
					}
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
			LogUtil.logError(log, e);

		}
	}

	
	
	
}
