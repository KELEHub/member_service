package com.member.controller.back;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.ApplyService;
import com.member.entity.Information;
import com.member.form.back.InformationForm;
import com.member.form.back.MemberSearchForm;
import com.member.helper.BaseResult;
import com.member.services.back.ServiceManagerService;

@Controller
@RequestMapping(value = "/ServiceManagerController")
public class ServiceManagerController {

	@Resource(name = "ServiceManagerServiceImpl")
	public ServiceManagerService serviceManagerServiceImpl;

	@RequestMapping(value = "/showServiceManager",method = RequestMethod.POST)
	public String showServiceManager(Model model){
		List<Information> result = serviceManagerServiceImpl.getServiceByIsService(1);
		model.addAttribute("result", result);
		return "back/serviceManager/serviceInfo";
	}
	
	@RequestMapping(value = "/showApplyServiceManager",method = RequestMethod.POST)
	public String showApplyServiceManager(Model model){
		List<ApplyService> result = serviceManagerServiceImpl.getApplyService();
		model.addAttribute("result", result);
		return "back/serviceManager/approveService";
	}

	@RequestMapping(value = "/showServiceInfoDetail",method = RequestMethod.POST)
	public String showServiceInfoDetail(@RequestBody MemberSearchForm form,Model model){
		Information result = serviceManagerServiceImpl.getServiceById(form.getId());
		model.addAttribute("form",form);
		model.addAttribute("member", result);
		return "back/serviceManager/serviceInfoDetail";
	}
	
	@RequestMapping(value = "/serviceRecharge",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> serviceRecharge(@RequestBody InformationForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		serviceManagerServiceImpl.updateInfo(form);
		result.setMsg("修改成功.");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/forbiddenService",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> forbiddenService(@RequestBody MemberSearchForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		serviceManagerServiceImpl.forbiddenService(1,form.getId());
		result.setMsg("禁用成功.");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/applyCheckFailure",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> applyCheckFailure(@RequestBody MemberSearchForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		serviceManagerServiceImpl.updateApplyState(1,form.getId());
		result.setMsg("操作成功.");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/applyCheckSuccess",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> applyCheckSuccess(@RequestBody MemberSearchForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		serviceManagerServiceImpl.updateApplyState(1,form.getId());
		result.setMsg("操作成功.");
		result.setSuccess(true);
		return result;
	}
}
