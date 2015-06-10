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
import com.member.form.back.TickForm;
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

	@RequestMapping(value = "/replyTickling",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> replyTickling(@RequestBody TickForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
//		serviceManagerServiceImpl.updateTickling(form);
		result.setMsg("回复留言成功.");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/deleteTickling",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> deleteTickling(@RequestBody TickForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
//		serviceManagerServiceImpl.deleteTickling(form);
		result.setMsg("删除留言成功.");
		result.setSuccess(true);
		return result;
	}
}
