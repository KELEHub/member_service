package com.member.controller.back;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.member.services.back.TicklingManagerService;

@Controller
@RequestMapping(value = "/TicklingManagerController")
public class TicklingManagerController {

	@Resource(name = "TicklingManagerServiceImpl")
	public TicklingManagerService ticklingManagerService;

	@RequestMapping(value = "/show")
	public ModelAndView show(HttpServletRequest request,
			HttpServletResponse response) {
		// 带参数重定向 view.setViewName("redirect:/index{id}");
		String basePath = "/";
		// System.out.println(a);
		ModelAndView mv = new ModelAndView();
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			String path = request.getContextPath();
			basePath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + path + "/";
			// SystemParameter systemParameter =
			// releaseNoticeService.getSystemParameter();
			// if(systemParameter!=null){
			// mv.setViewName("redirect:/systemParameter.jsp");
			// }else{
			// mv.setViewName("redirect:/systemParameter.jsp");
			// }
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.sendRedirect(basePath + "login.jsp");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	@RequestMapping(value = "/set")
	public ModelAndView set(HttpServletRequest request,
			HttpServletResponse response, int userId, String title,
			String content, int category, String desc) {
		// 带参数重定向 view.setViewName("redirect:/index{id}");
		String basePath = "/";
		// System.out.println(a);
		ModelAndView mv = new ModelAndView();
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			String path = request.getContextPath();
			basePath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + path + "/";
			// SystemParameter systemParameter = releaseNoticeService
			// .getSystemParameter();
			// if(systemParameter == null){
			// systemParameter = new SystemParameter();
			// systemParameter.setSystemData("system");
			// systemParameter.setCreateTime(new Date());
			// }
			// releaseNoticeService.setSystemParameter(systemParameter, pb);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.sendRedirect(basePath + "login.jsp");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;

	}
}
