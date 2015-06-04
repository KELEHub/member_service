package com.member.controller.back;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.member.beans.back.ParameterBean;
import com.member.entity.SystemParameter;
import com.member.services.back.ParameterService;

@Controller
@RequestMapping(value = "/ParameterController")
public class ParameterController {
	
	@Resource(name = "ParameterServiceImpl")
	public ParameterService parameterService;
	
	
	@RequestMapping(value = "/show",method = RequestMethod.POST)
	public String show(Model model)
			 {
		try {
			 SystemParameter systemParameter = parameterService.getSystemParameter();
			 if(systemParameter!=null){
				 return "back/parameter/systemParameter";
			}else{
				return "back/parameter/systemParameter";
			}
		
		} catch (Exception e) {
			  e.printStackTrace();
			  return "back/parameter/systemParameter";
		}

	}
	
	@RequestMapping(value = "/set")
	public ModelAndView set(HttpServletRequest request,
			HttpServletResponse response, String dayCount, String goldFlg,
			String goldMax, String goldMin, String goldTake, String scoreMax,
			String scoreMin, String scoreTake, String glbMax, String glbMin,
			String glbTake, String scoreInMin, String scoreInTake) {
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
			SystemParameter systemParameter = parameterService
					.getSystemParameter();
			if(systemParameter == null){
				systemParameter = new SystemParameter();
				systemParameter.setSystemData("system");
				systemParameter.setCreateTime(new Date());
			}
			ParameterBean pb = new ParameterBean();
			pb.setDayCount(dayCount);
			pb.setGoldFlg(goldFlg);
			pb.setGoldMax(goldMax);
			pb.setGoldMin(goldMin);
			pb.setGoldTake(goldTake);
			pb.setScoreMax(scoreMax);
			pb.setScoreMin(scoreMin);
			pb.setScoreTake(scoreTake);
			pb.setGlbMin(glbMin);
			pb.setGlbTake(glbTake);
			pb.setGlbMax(glbMax);
			pb.setScoreInTake(scoreInTake);
			pb.setScoreInMin(scoreInMin);
			parameterService.setSystemParameter(systemParameter, pb);
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
