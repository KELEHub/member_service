package com.member.controller.back;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.AccountDetails;
import com.member.entity.RepeatedMoneyStatistics;
import com.member.form.back.UserEditeForm;
import com.member.helper.BaseResult;
import com.member.services.back.IntegralManagerService;

@Controller
@RequestMapping(value = "/IntegralManagerController")
public class IntegralManagerController {
	
	@Resource(name = "IntegralManagerServiceImpl")
	public IntegralManagerService integralManagerService;

	@RequestMapping(value = {"/showIntegralHistory","/queryStatistics"},method = RequestMethod.POST)
	public String showIntegralHistory(Model model){
		List<AccountDetails> result = integralManagerService.getIntegralHistory();
		model.addAttribute("result", result);
		return "back/integralManager/integralIssueHistory";
	}
	
	@RequestMapping(value = "/showRangeIntegralIssueManager",method = RequestMethod.POST)
	public String showRangeIntegralIssueManager(Model model){
		List<RepeatedMoneyStatistics> result = integralManagerService.getAvailableRangeIntegral();
		model.addAttribute("result", result);
		return "back/integralManager/rangeIssue";
	}
	
//	@RequestMapping(value = "/queryStatistics",method = RequestMethod.POST)
//	@ResponseBody
//	public BaseResult<Void> queryStatistics(@RequestBody UserEditeForm form,Model model){
//		BaseResult<Void> result = new BaseResult<Void>();
//		List<AccountDetails> detailsList = integralManagerService.getIntegralHistoryByUserName(form.getUserNumber());
//		model.addAttribute("result", detailsList);
//		result.setSuccess(true);
//		return result;
//	}
	
}
