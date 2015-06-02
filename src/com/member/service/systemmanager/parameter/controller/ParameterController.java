package com.member.service.systemmanager.parameter.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.member.service.systemmanager.parameter.po.SystemParameter;
import com.member.service.systemmanager.parameter.service.ParameterService;

@Controller
@RequestMapping(value = "/ParameterController")
public class ParameterController {
	
	@Resource(name = "ParameterServiceImpl")
	public ParameterService parameterService;
	
	
	@RequestMapping(value = "/show")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response)
			 {
		// 带参数重定向  view.setViewName("redirect:/index{id}");
		String basePath="/";
		//System.out.println(a);
		ModelAndView mv=new ModelAndView();
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			String path = request.getContextPath();
			 basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			 SystemParameter systemParameter = parameterService.getSystemParameter();
			 if(systemParameter!=null){
				 mv.setViewName("redirect:/systemParameter.jsp");
			}else{
				 mv.setViewName("redirect:/systemParameter.jsp");
			}
		
			    return mv;
		} catch (Exception e) {
			  e.printStackTrace();
			  try {
					response.sendRedirect(basePath+"login.jsp");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;

	}
	

}
