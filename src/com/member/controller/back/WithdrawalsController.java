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

import com.member.entity.Information;
import com.member.entity.Withdrawals;
import com.member.form.back.MemberSearchForm;
import com.member.form.back.WithdrawalsCheckForm;
import com.member.form.back.WithdrawalsSearchForm;
import com.member.helper.BaseResult;
import com.member.services.back.MemberManageService;
import com.member.services.back.WithdrawalsService;

@Controller
@RequestMapping(value = "/withdrawals")
public class WithdrawalsController {

	@Resource(name = "WithdrawalsServiceImpl")
	private WithdrawalsService withdrawalsService;

//	@Resource(name="MemberManageServiceImpl")
//	private MemberManageService memberManageService;
	
	@RequestMapping(value = "/showWithdrawalszRecord")
	public String showWithdrawalszRecord(HttpServletRequest request, Model model) {

		Object logonUserO = request.getSession().getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		Object userId = logonUserMap.get("id");
		Object userName = logonUserMap.get("userName");
		List<Withdrawals> result = withdrawalsService
				.getWithdrawalsRecordByMemberNumber("", (Integer) userId,
						(String) userName);
		model.addAttribute("result", result);
		return "back/withdrawals/showwithdrawalsrecord";
	}

	@RequestMapping(value = "/searchWithdrawalszRecord", method = RequestMethod.POST)
	public String searchActivationMember(
			@RequestBody WithdrawalsSearchForm form,
			HttpServletRequest request, Model model) {
		Object logonUserO = request.getSession().getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		Object userId = logonUserMap.get("id");
		Object userName = logonUserMap.get("userName");
		List<Withdrawals> result = withdrawalsService
				.getWithdrawalsRecordByMemberNumber(form.getNumber(),
						(Integer) userId, (String) userName);
		model.addAttribute("result", result);
		model.addAttribute("form", form);
		return "back/withdrawals/showwithdrawalsrecord";
	}
	
	@RequestMapping(value = "/dealWithdrawalszRecord")
	public String dealWithdrawalszRecord(HttpServletRequest request, Model model) {

		List<Withdrawals> result = withdrawalsService
				.getNotDealWithdrawalsRecord("");
		model.addAttribute("result", result);
		return "back/withdrawals/showNotDealwithdrawalsrecord";
	}
	
	@RequestMapping(value = "/searchNotDealWithdrawalszRecord")
	public String searchNotDealWithdrawalszRecord(@RequestBody WithdrawalsSearchForm form,HttpServletRequest request, Model model) {
		List<Withdrawals> result = withdrawalsService
				.getNotDealWithdrawalsRecord(form.getNumber());
		model.addAttribute("result", result);
		return "back/withdrawals/showNotDealwithdrawalsrecord";
	}
	
	@RequestMapping(value = "/agreewithdrawals")
	@ResponseBody
	public BaseResult<Void> agreewithdrawals(@RequestBody WithdrawalsCheckForm form,HttpServletRequest request, Model model) {
		Object logonUserO = request.getSession().getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		Object userName = logonUserMap.get("userName");
		
		BaseResult<Void> result = 
		withdrawalsService.agreewithdrawals(form.getId(),(String)userName);
		return result;
	}
	
	@RequestMapping(value = "/showWithDrawalsDetail",method = RequestMethod.POST)
	public String showMemberDetails(@RequestBody MemberSearchForm form,Model model){
		Withdrawals reslut = withdrawalsService.getWithdrawalsDetailById(form.getId());
//		Information member = memberManageService.getMemberById(form.getId());
		model.addAttribute("reslut",reslut);
		return "back/withdrawals/showWithDrawalsDetail";
	}
}
