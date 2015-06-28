package com.member.controller.back;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.Charge;
import com.member.form.ChargeOperForm;
import com.member.form.back.MemberSearchForm;
import com.member.helper.BaseResult;
import com.member.services.back.ChargeService;

@Controller
@RequestMapping(value = "/charge")
public class ChargeController {

	@Resource(name = "ChargeServiceImpl")
	private ChargeService chargeService;
	

	@RequestMapping(value = "/showChargeRecord")
	public String showChargezRecord(HttpServletRequest request, Model model) {
		ChargeOperForm form = new ChargeOperForm();
		form.setStatus(1);
		List<Charge> result = chargeService.getChargeList(form);
		model.addAttribute("result", result);
		return "back/charge/showchargerecord";
	}

	@RequestMapping(value = "/searchChargeRecord", method = RequestMethod.POST)
	public String searchActivationMember(
			@RequestBody ChargeOperForm form,
			HttpServletRequest request, Model model) {
		form.setStatus(1);
		List<Charge> result = chargeService.getChargeList(form);
		model.addAttribute("result", result);
		model.addAttribute("form", form);
		return "back/charge/showchargerecord";
	}
	
	@RequestMapping(value = "/dealChargeRecord")
	public String dealChargezRecord(HttpServletRequest request, Model model) {
		ChargeOperForm form = new ChargeOperForm();
		form.setStatus(0);
		List<Charge> result = chargeService.getChargeList(form);
		model.addAttribute("result", result);
		return "back/charge/shownotdealchargerecord";
	}
	
	@RequestMapping(value = "/searchDealChargezRecord")
	public String searchNotDealChargezRecord(@RequestBody ChargeOperForm form,HttpServletRequest request, Model model) {
		form.setStatus(0);
		List<Charge> result = chargeService.getChargeList(form);
		model.addAttribute("result", result);
		model.addAttribute("form", form);
		return "back/charge/shownotdealchargerecord";
	}
	
	@RequestMapping(value = "/agreeCharge")
	@ResponseBody
	public BaseResult<Void> agreeCharge(@RequestBody ChargeOperForm form,HttpServletRequest request, Model model) {
		Object logonUserO = request.getSession().getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		Object userName = logonUserMap.get("userName");
		
		BaseResult<Void> result = 
				chargeService.agreeCharge(form,(String)userName);
		return result;
	}
	
	@RequestMapping(value = "/showChargeDetail",method = RequestMethod.POST)
	public String showMemberDetails(@RequestBody MemberSearchForm form,Model model){
		Charge result = chargeService.getChargeDetailById(form.getId());
		model.addAttribute("result",result);
		return "back/charge/showsdetail";
	}
}
