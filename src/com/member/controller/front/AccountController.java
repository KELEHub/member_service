package com.member.controller.front;

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
import com.member.entity.Information;
import com.member.entity.Withdrawals;
import com.member.form.back.MemberSearchForm;
import com.member.form.front.MemberChargeApplyForm;
import com.member.form.front.MemberWithdrawalsApplyForm;
import com.member.helper.BaseResult;
import com.member.services.back.InstitutionService;
import com.member.services.front.AccountService;

@Controller
@RequestMapping(value = "/acc")
public class AccountController {

	@Resource(name = "InstitutionServiceImpl")
	private InstitutionService institutionService;
	
	@Resource(name = "AccountServiceImpl")
	private AccountService accountService;
	
	@RequestMapping(value = "/showacccharge",method = RequestMethod.POST)
	public String showAccCharge(HttpServletRequest request,Model model){
		Object logonUserO = request.getSession().getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		Object userId = logonUserMap.get("userId");
		Object userName = logonUserMap.get("username");
		//取得客户的当前登录客户的账户信息
		Information result = institutionService.getInformationById((Integer)userId);
		
		//取得当前登录客户的充值记录
		List<Charge> charList = accountService.getMemberChargeInfoByUserName((String)userName);
		model.addAttribute("result", result);
		model.addAttribute("charList", charList);

		return "front/memberinfo/showcharge";
	}
	
	@RequestMapping(value = "/doacccharge",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> doAccCharge(@RequestBody MemberChargeApplyForm form,Model model){
		BaseResult<Void> result = accountService.doAccCharge(form);
		return result;
	}
	
	@RequestMapping(value = "/showAccChargeDetail",method = RequestMethod.POST)
	public String showAccChargeDetail(@RequestBody MemberChargeApplyForm form,Model model){
		Charge result = accountService.getChargeDetailById(form.getId());
		model.addAttribute("result",result);
		return "front/memberinfo/showaccchargedetail";
	}
	
	@RequestMapping(value = "/showRefuseReason",method = RequestMethod.POST)
	public String showRefuseReason(@RequestBody MemberSearchForm form,Model model){
		Charge result = accountService.getChargeDetailById(form.getId());
		model.addAttribute("result",result);
		return "front/memberinfo/showsrefusereason";
	}
	
	@RequestMapping(value = "/showaccwithdrawals",method = RequestMethod.POST)
	public String showAccWithdrawals(HttpServletRequest request,Model model){
		Object logonUserO = request.getSession().getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		Object userId = logonUserMap.get("userId");
		Object userName = logonUserMap.get("username");
		// 取得客户的当前登录客户的账户信息
		Information result = institutionService
				.getInformationById((Integer) userId);

		// 取得当前登录客户的提现申请记录
		List<Withdrawals> withdrawalsList = accountService.getMemberWithdrawalsInfoByUserName((String)userName);
		model.addAttribute("result", result);
		model.addAttribute("withdrawalsList", withdrawalsList);
		
		return "front/memberinfo/showwithdrawals";
	}
	
	@RequestMapping(value = "/doaccwithdrawals",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> doAccWithdrawals(@RequestBody MemberWithdrawalsApplyForm form,Model model){
		BaseResult<Void> result = accountService.doAccWithdrawals(form);
		return result;
	}
	
	@RequestMapping(value = "/showAccWithdrawalsDetail",method = RequestMethod.POST)
	public String showAccWithdrawalsDetail(@RequestBody MemberWithdrawalsApplyForm form,Model model){
		Withdrawals result = accountService.getWithdrawalsDetailById(form.getId());
		model.addAttribute("result",result);
		return "front/memberinfo/showaccwithdrawalsdetail";
	}
	
	@RequestMapping(value = "/showWithdrawalsRefuseReason",method = RequestMethod.POST)
	public String showWithdrawalsRefuseReason(@RequestBody MemberSearchForm form,Model model){
		Withdrawals result = accountService.getWithdrawalsDetailById(form.getId());
		model.addAttribute("result",result);
		return "front/memberinfo/showsrefusereason";
	}
	
	@RequestMapping(value = "/cancelWithdrawals",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> cancelWithdrawals(@RequestBody MemberWithdrawalsApplyForm form,Model model){
		BaseResult<Void> result = accountService.cancelWithdrawals(form);
		return result;
	}
	
	@RequestMapping(value = "/deleteCharge",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> deleteCharge(@RequestBody MemberSearchForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			
			Charge charge = accountService.getChargeDetailById(form.getId());
			if(charge!=null){
				charge.setStatus(3);
			}
			institutionService.savaOrUpdate(charge);
			result.setMsg("删除成功");
			result.setSuccess(true);
		} catch (Exception e) {
			result.setMsg("删除失败");
			result.setSuccess(false);
		}
		
		return result;
	}
	
}
