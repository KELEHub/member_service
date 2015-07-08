package com.member.controller.front;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.BankService;
import com.member.entity.ForbidForm;
import com.member.entity.Information;
import com.member.entity.Institution;
import com.member.form.front.ActivateForm;
import com.member.form.front.RegisterForm;
import com.member.helper.BaseResult;
import com.member.services.back.InformationService;
import com.member.services.back.InstitutionService;
import com.member.services.back.ParameterService;
import com.member.services.back.ServiceManagerService;
import com.member.util.CommonUtil;

@Controller
@RequestMapping(value = "/RegisterController")
public class RegisterController {
	
	@Resource(name = "InformationServiceImpl")
	public InformationService informationService;
	
	@Resource(name = "ParameterServiceImpl")
	public ParameterService parameterService;
	
	@Resource(name = "InstitutionServiceImpl")
	public InstitutionService institutionService;
	
	@Resource(name = "ServiceManagerServiceImpl")
	public ServiceManagerService serviceManagerService;

	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public String show(Model model, HttpSession sesison) {
		try {
			Institution institution = institutionService.getInstitutionInfo();
		    List<BankService> bankServiceList = institutionService.getBankServiceInfo();
			String number = getNumber();
			while (number == null) {
				number= getNumber();
			}
			model.addAttribute("number",number);
			model.addAttribute("list", bankServiceList);
			model.addAttribute("registerMoney", "消费会员：￥"+institution.getRegisterGold());
			return "front/register/register";

		} catch (Exception e) {
			e.printStackTrace();
			return "front/register/register";
		}

	}
	
	
	@RequestMapping(value = "/change",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> change(@RequestBody RegisterForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		String number = getNumber();
		while (number == null) {
			number= getNumber();
		}
		result.setMsg(number);
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/select",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> select(@RequestBody RegisterForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		Information ad = informationService.getInformationByNumber(form.getRefereeNumber());
		if(ad != null){
			result.setMsg(ad.getName());
		}else{
			result.setMsg("此用户不存在");
		}
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> register(@RequestBody RegisterForm form, Model model, HttpSession sesison) {
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			Object logonUserO = sesison.getAttribute("logonUser");
			Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
			String userNaemO =(String) logonUserMap.get("username");
			ForbidForm ifForbid = serviceManagerService.getForbidForm();
			if(ifForbid == null || ifForbid.getIfForbid() == null || ifForbid.getIfForbid() == 1){
				result.setMsg("现在禁止报单，请等待解禁");
				result.setSuccess(true);
				return result;
			}
			if(userNaemO == null){
				result.setMsg("登录账号已损毁，请重新登录");
				result.setSuccess(true);
				return result;
			}
			Information activateNumber = informationService.getInformationByNumber(userNaemO);
			Information check = informationService.getInformationByNumber(form
					.getNumber());
			if (check != null) {
				result.setMsg("注册失败，账号已存在");
				result.setSuccess(false);
				return result;
			}
			Information checkRefer = informationService
					.getInformationByNumber(form.getRefereeNumber());
			if (checkRefer == null) {
				result.setMsg("注册失败，推荐人不存在");
				result.setSuccess(false);
				return result;
			}
			if (form.getIdentity() == null || "".equals(form.getIdentity())) {
				result.setMsg("身份证必须填写");
				result.setSuccess(false);
				return result;
			}
			if (form.getPhoneNumber() == null
					|| "".equals(form.getPhoneNumber())) {
				result.setMsg("电话号码必须填写");
				result.setSuccess(false);
				return result;
			}
			if (form.getUsername() == null
					|| "".equals(form.getUsername())) {
				result.setMsg("姓名必须填写");
				result.setSuccess(false);
				return result;
			}
			if (form.getBankname() == null || form.getBankCard() == null
					|| form.getBankAddress() == null
					|| form.getBankProvince() == null
					|| form.getBankCounty() == null
					|| form.getBankCity() == null
					|| "".equals(form.getBankname())
					|| "".equals(form.getBankCard())
					|| "".equals(form.getBankAddress())
					|| "".equals(form.getBankProvince())
					|| "".equals(form.getBankCounty())
					|| "".equals(form.getBankCity())) {
				result.setMsg("银行信息必须填写完整");
				result.setSuccess(false);
				return result;
			}

			Information newInfo = new Information();
			newInfo.setNumber(form.getNumber());
			newInfo.setName(form.getUsername());
			newInfo.setRecommendNumber(form.getRefereeNumber());
			newInfo.setRecommendId(checkRefer.getId());
			newInfo.setActivateId(activateNumber.getId());
			newInfo.setActivateNumber(activateNumber.getNumber());
			newInfo.setIdentity(form.getIdentity());
			newInfo.setPhoneNumber(form.getPhoneNumber());
			newInfo.setPassword("123");
			newInfo.setBankName(form.getBankname());
			newInfo.setBankProvince(form.getBankProvince());
			newInfo.setBankCity(form.getBankCity());
			newInfo.setBankCounty(form.getBankCounty());
			newInfo.setBankAddress(form.getBankAddress());
			newInfo.setBankCard(form.getBankCard());
			newInfo.setLinkProvince(form.getBankProvince());
			newInfo.setLinkCity(form.getBankCity());
			newInfo.setLinkCounty(form.getBankCounty());
			newInfo.setLinkAddress(form.getBankAddress());
			newInfo.setRegisterDate(new Date());
			newInfo.setIsService(0);
			newInfo.setIsActivate(0);
			newInfo.setIsLock(0);
			newInfo.setCrmMoney(new BigDecimal(0));
			newInfo.setCrmAccumulative(new BigDecimal(0));
			newInfo.setBonusMoney(new BigDecimal(0));
			newInfo.setBonusAccumulative(new BigDecimal(0));
			newInfo.setRepeatedMoney(new BigDecimal(0));
			newInfo.setRepeatedAccumulative(new BigDecimal(0));
			newInfo.setShoppingMoney(new BigDecimal(0));
			newInfo.setShoppingAccumulative(new BigDecimal(0));
			newInfo.setProtectPassword("123");
			institutionService.savaOrUpdate(newInfo);
			result.setSuccess(true);
			result.setMsg("注册成功");
			return result;
		} catch (Exception e) {
			result.setMsg("注册失败，请重试");
			result.setSuccess(false);
			return result;
		}
	}
	
	@RequestMapping(value = "/showActivate", method = RequestMethod.POST)
	public String showActivate(Model model, HttpSession sesison) {
		try {
			  Object logonUserO = sesison.getAttribute("logonUser");
			  Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
			  String userNaemO =(String) logonUserMap.get("username");
			  Information ad = informationService.getInformationByNumber(userNaemO);
		      List<Information> informationServiceList = informationService.getInformationForNoActivate(userNaemO);
		      List<ActivateForm> list = new ArrayList<ActivateForm>();
		      if(informationServiceList!=null && informationServiceList.size()>0){
		    	  for(Information info:informationServiceList){
		    		  ActivateForm acForm = new ActivateForm();
		    		  acForm.setNumber(info.getNumber());
		    		  acForm.setName(info.getName());
		    		  acForm.setPhoneNumber(info.getPhoneNumber());
		    		  acForm.setRecommendNumber(info.getRecommendNumber());
		    		  acForm.setRegisterDate(info.getRegisterDate().toString());
		    		  acForm.setIdentity(info.getIdentity());
		    		  String address= info.getLinkProvince()+","+info.getLinkCity()+","+info.getLinkCounty()+","+info.getLinkAddress();
		    		  acForm.setLinkAddress(address);
		    		  if(info.getIsActivate()==1){
		    			  acForm.setActivate("已激活["+info.getActiveDate()+"]");
		    			  acForm.setFlg("1");
		    		  }else{
		    			  acForm.setActivate("未激活");
		    			  acForm.setFlg("0");
		    		  }
		    		  list.add(acForm);
		    	  }
		      }
		    
			model.addAttribute("goldmoneybalance",CommonUtil.insertComma(ad.getCrmMoney().toString(), 2));
			model.addAttribute("result", list);
			return "front/register/activate";

		} catch (Exception e) {
			e.printStackTrace();
			return "front/register/activate";
		}
	}
	
	
	@RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> deleteUser(@RequestBody ActivateForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			ForbidForm ifForbid = serviceManagerService.getForbidForm();
			if(ifForbid == null || ifForbid.getIfForbid() == null || ifForbid.getIfForbid() == 1){
				result.setMsg("现在禁止报单，请等待解禁");
				result.setSuccess(true);
				return result;
			}
			Information ad = informationService.getInformationByNumber(form.getNumber());
			if(ad!=null){
				informationService.deleteData(ad);
			}
			result.setMsg("删除成功");
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			result.setMsg("删除失败，请重试");
			result.setSuccess(true);
			return result;
		}
	}
	
	@RequestMapping(value = "/activateUser",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> activateUser(@RequestBody ActivateForm form,Model model,HttpSession sesison){
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			Object logonUserO = sesison.getAttribute("logonUser");
			  Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
			  String userNaemO =(String) logonUserMap.get("username");
				ForbidForm ifForbid = serviceManagerService.getForbidForm();
				if(ifForbid == null || ifForbid.getIfForbid() == null || ifForbid.getIfForbid() == 1){
					result.setMsg("现在禁止报单，请等待解禁");
					result.setSuccess(true);
					return result;
				}
			  //激活人
			Information selfInfo = informationService.getInformationByNumber(userNaemO);
			 //被激活对象
			Information ad = informationService.getInformationByNumber(form.getNumber());
			if(!ad.getActivateNumber().equals(userNaemO)){
				result.setMsg("登录账号已损毁，请重新登录");
				result.setSuccess(true);
				return result;
			}
			//推荐人
			Information recommendInfo = informationService.getInformationByNumber(ad.getRecommendNumber());
			
			Institution institution = institutionService.getInstitutionInfo();
			if(selfInfo.getCrmMoney().compareTo(new BigDecimal(institution.getRegisterGold()))==-1){
				result.setMsg("葛粮币余额不足");
				result.setSuccess(true);
				return result;
			}
			//礼包生成和激活会员
			informationService.activate(ad, selfInfo, institution,recommendInfo);
			result.setMsg("激活成功");
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			result.setMsg("激活失败，请重试");
			result.setSuccess(true);
			return result;
		}
	}

	private String getNumber() {
		Random r = new Random();
		int x = r.nextInt(999999);
		String number = null;
		if (x > 100000) {
			 number = String.valueOf(x);
			 Information info = informationService.getInformationByNumber(number);
			 if(info!=null){
				 return null;
			 }else{
				 return number;
			 }
		}
		return null;
	}

}
