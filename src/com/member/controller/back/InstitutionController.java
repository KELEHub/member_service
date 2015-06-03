package com.member.controller.back;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.member.beans.back.ParameterBean;
import com.member.entity.Institution;
import com.member.entity.SystemParameter;
import com.member.services.back.InstitutionService;

@Controller
@RequestMapping(value = "/InstitutionController")
public class InstitutionController {
	
	@Resource(name = "InstitutionServiceImpl")
	public InstitutionService institutionService;
	
	@RequestMapping(value = "/show")
	public ModelAndView show(HttpServletRequest request,
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
			 Institution institution = institutionService.getInstitutionInfo();
			 if(institution!=null){
				 mv.setViewName("redirect:/institution.jsp");
			}else{
				 mv.setViewName("redirect:/institution.jsp");
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
	
	
	@RequestMapping(value = "/set")
	public ModelAndView set(HttpServletRequest request,
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
			Institution institution = institutionService.getInstitutionInfo();
			if(institution == null){
				institution = new Institution();
				institution.setSystemData("system");
				institution.setCreateTime(new Date());
			}
			institutionService.setInstitution(institution);
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
