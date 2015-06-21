package com.member.controller.front;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.ApplyService;
import com.member.form.back.ApplyServiceForm;
import com.member.helper.BaseResult;
import com.member.services.front.ApplyQualificationService;

@Controller
@RequestMapping(value = "/ApplyQualificationController")
public class ApplyQualificationController {

	@Resource(name = "ApplyQualificationServiceImpl")
	public ApplyQualificationService applyQualificationService;

	@RequestMapping(value = "/showApplyQualification",method = RequestMethod.POST)
	public String showApplyQualification(Model model,HttpSession sesison){
		Object logonUserO = sesison.getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		String userNaemO = (String) logonUserMap.get("username");
		
		List<ApplyService> result = applyQualificationService.getApplyQualification(userNaemO);
		model.addAttribute("result", result);
		return "front/salesPromotion/applyQualification";
	}
	
	@RequestMapping(value = "/deleteApplyQualification",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> deleteApplyQualification(@RequestBody ApplyServiceForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		applyQualificationService.deleteApplyQualification(form.getId());
		result.setMsg("删除申请成功.");
		result.setSuccess(true);
		return result;
	}
}
