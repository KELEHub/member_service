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
import com.member.form.front.ConvertForm;
import com.member.helper.BaseResult;
import com.member.services.back.InformationService;
import com.member.services.back.ParameterService;
import com.member.services.front.TransferService;
import com.member.util.CommonUtil;

@Controller
@RequestMapping(value = "/ConvertController")
public class ConvertController {
	
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
				  model.addAttribute("shoppingMoney","0.00");
			  }else{
				  model.addAttribute("shoppingMoney",CommonUtil.insertComma(ad.getShoppingMoney().toString(), 2));
			  }
			  return "front/convert/convert";

		} catch (Exception e) {
			e.printStackTrace();
			return "front/convert/convert";
		}

	}
	
	
	@RequestMapping(value = "/convertData", method = RequestMethod.POST)
	@ResponseBody
	public  BaseResult<Void> transferData(@RequestBody ConvertForm form,Model model,HttpSession sesison) {
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			  result.setSuccess(true);
			  Object logonUserO = sesison.getAttribute("logonUser");
			  Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
			  String userNaemO =(String) logonUserMap.get("username");
			  SystemParameter parameter = parameterService
				.getSystemParameter();
			  Information ad = informationService.getInformationByName(userNaemO);
			  if(form.getToCmrMoney()==null || new BigDecimal(0).compareTo(getValue(form.getToCmrMoney().replace(",", "")))==0){
				  result.setMsg("转换金额不能为空");
				  return result;
			  }
			  BigDecimal mid;
			  if(ad.getRepeatedMoney() != null){
				   mid = ad.getShoppingMoney().subtract(ad.getRepeatedMoney());
			  }else{
				  mid = ad.getShoppingMoney();
			  }
			  if(mid.compareTo(getValue(form.getToCmrMoney().replace(",", "")).add(parameter.getGlbTake()))==-1){
				  result.setMsg("可装换的积分余额不足");
				  return result;
			  }
			  if(getValue(form.getToCmrMoney().replace(",", "")).compareTo(parameter.getGlbMin())==-1){
				  result.setMsg("转换金额不能低于最低转换金额");
				  return result;
			  }
			  if(!form.getPayPassword().equals(ad.getProtectPassword())){
				  result.setMsg("二级密码不正确");
				  return result;
			  }
			  transferService.convertManager(ad,getValue(form.getToCmrMoney().replace(",", "")),parameter);
			  result.setMsg("转换成功");
			  result.setMsgCode(CommonUtil.insertComma(ad.getShoppingMoney().toString(), 2));
			  return result;

		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("转换失败，请重试");
			return result;
		}

	}
	
	private BigDecimal getValue(String s){
	 	BigDecimal b = new BigDecimal(s); 
	 	return b.setScale(2, BigDecimal.ROUND_DOWN);
}

}
