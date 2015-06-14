package com.member.controller.back;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.member.entity.AccountDetails;
import com.member.services.back.IntegralManagerService;

@Controller
@RequestMapping(value = "/IntegralManagerController")
public class IntegralManagerController {
	
	@Resource(name = "IntegralManagerServiceImpl")
	public IntegralManagerService integralManagerService;

	@RequestMapping(value = "/showIntegralHistory",method = RequestMethod.POST)
	public String showIntegralHistory(Model model){
		List<AccountDetails> result = integralManagerService.getIntegralHistory();
		model.addAttribute("result", result);
		return "back/integralManager/integralIssueHistory";
	}
	
	@RequestMapping(value = "/showIntegralIssueManager",method = RequestMethod.POST)
	public String showIntegralIssueManager(Model model){
		List<AccountDetails> result = integralManagerService.getIntegralHistory();
		model.addAttribute("result", result);
		return "back/integralManager/integralIssue";
	}

}
