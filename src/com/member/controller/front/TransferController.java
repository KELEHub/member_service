package com.member.controller.front;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.Information;
import com.member.entity.SystemParameter;
import com.member.form.front.Transform;
import com.member.helper.BaseResult;
import com.member.services.back.InformationService;
import com.member.services.back.ParameterService;
import com.member.services.front.TransferService;
import com.member.util.CommonUtil;

@Controller
@RequestMapping(value = "/TransferController")
public class TransferController {
	
	@Resource(name = "InformationServiceImpl")
	public InformationService informationService;
	
	@Resource(name = "TransferServiceImpl")
	public TransferService transferService;
	
	@Resource(name = "ParameterServiceImpl")
	public ParameterService parameterService;
	
	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public String show(Model model,HttpSession sesison) {
		try {
			  Object logonUserO = sesison.getAttribute("logonUser");
			  Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
			  String userNaemO =(String) logonUserMap.get("username");
			  Information ad = informationService.getInformationByName(userNaemO);
			  if(ad.getCrmMoney()==null || "".equals(ad.getCrmMoney())){
				  model.addAttribute("goldmoneybalance","0.00");
			  }else{
				  model.addAttribute("goldmoneybalance",CommonUtil.insertComma(ad.getCrmMoney().toString(), 2));
			  }
			  return "front/transfer/transfer";

		} catch (Exception e) {
			e.printStackTrace();
			return "front/transfer/transfer";
		}

	}
	
	@RequestMapping(value = "/transferData", method = RequestMethod.POST)
	@ResponseBody
	public  BaseResult<Void> transferData(@RequestBody Transform form,Model model,HttpSession sesison) {
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			  result.setSuccess(true);
			  Object logonUserO = sesison.getAttribute("logonUser");
			  Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
			  String userNaemO =(String) logonUserMap.get("username");
			  SystemParameter parameter = parameterService
				.getSystemParameter();
			  Information ad = informationService.getInformationByName(userNaemO);
			  Information toInfo = informationService.getInformationByName(form.getToUserNumber());
			  if(form.getToUserNumber().equals(userNaemO)){
				  result.setMsg("自己不能给自己转账");
				  return result;
			  }
			  if(ad.getCrmMoney().compareTo(new BigDecimal(form.getToGoldMoney()))==-1){
				  result.setMsg("葛粮币余额不足");
				  return result;
			  }
			  if(toInfo==null){
				  result.setMsg("用户不存在");
				  return result;
			  }
			  if(ad.getLeaderServiceNumber()==null){
				  result.setMsg("对不起，您没有上级报单中心");
				  return result;
			  }
			  if(toInfo.getServiceNumber()==null){
				  result.setMsg("转账对象不是您的报单中心范围");
				  return result;
			  }
			  if(!toInfo.getServiceNumber().equals(ad.getLeaderServiceNumber())){
				  result.setMsg("转账对象不是您的报单中心范围");
				  return result;
			  }
			  if(new BigDecimal(form.getToGoldMoney()).compareTo(new BigDecimal(0))==-1){
				  result.setMsg("转账金额必须大于0");
				  return result;
			  }
			  if(new BigDecimal(form.getToGoldMoney()).compareTo(parameter.getScoreMax())==1){
				  result.setMsg("转账金额大于最高转账金额");
				  return result;
			  }
			  if(new BigDecimal(form.getToGoldMoney()).compareTo(parameter.getScoreMin())==-1){
				  result.setMsg("转账金额小于最低转账金额");
				  return result;
			  }
			  if(!form.getPayPassword().equals(ad.getProtectPassword())){
				  result.setMsg("二级密码不正确");
				  return result;
			  }
			  transferService.transferManager(ad, toInfo, form,parameter);
			  result.setMsg("转账成功");
			  result.setMsgCode(CommonUtil.insertComma(ad.getCrmMoney().toString(), 2));
			  return result;

		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("转账失败，请重试");
			return result;
		}

	}

	
	@RequestMapping(value = "/select",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> select(@RequestBody Transform form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		
		Information ad = informationService.getInformationByName(form.getToUserNumber());
		if(ad != null){
			result.setMsg(ad.getName());
		}else{
			result.setMsg("此用户不存在");
		}
		result.setSuccess(true);
		return result;
	}
	

}
