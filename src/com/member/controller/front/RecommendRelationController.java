package com.member.controller.front;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.member.entity.Information;
import com.member.services.front.RecommendRelationService;

@Controller
@RequestMapping(value = "/RecommendRelationController")
public class RecommendRelationController {

	@Resource(name = "RecommendRelationServiceImpl")
	public RecommendRelationService recommendRelationService;

	@RequestMapping(value = "/showRecommendRelation",method = RequestMethod.POST)
	public String showRecommendRelation(Model model,HttpSession sesison){
		Object logonUserO = sesison.getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		String userNaemO = (String) logonUserMap.get("username");
		List<Information> result = recommendRelationService.getRecommendRelation(userNaemO);
		model.addAttribute("result", result);
		return "front/salesPromotion/recommendRelation";
	}
}
