package com.member.controller.front;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.ApplyService;
import com.member.entity.Information;
import com.member.form.back.ApplyServiceForm;
import com.member.helper.BaseResult;
import com.member.services.front.ApplyQualificationService;

@Controller
@RequestMapping(value = "/ApplyQualificationController")
public class ApplyQualificationController {

	@Resource(name = "ApplyQualificationServiceImpl")
	public ApplyQualificationService applyQualificationService;

	@RequestMapping(value = "/showApplyQualification",method = RequestMethod.POST)
	public String showApplyQualification(Model model,HttpSession sesison){
		Object logonUserO = sesison.getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		String userNaemO = (String) logonUserMap.get("username");
		
		List<ApplyService> result = applyQualificationService.getApplyQualification(userNaemO);
		model.addAttribute("result", result);
		return "front/salesPromotion/applyQualification";
	}
	
	@RequestMapping(value = "/deleteApplyQualification",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> deleteApplyQualification(@RequestBody ApplyServiceForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		applyQualificationService.deleteApplyQualification(form.getId());
		result.setMsg("删除申请成功");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/queryMemberInfo",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> queryMemberInfo(@RequestBody ApplyServiceForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		List<Information> infoList = applyQualificationService.queryMemberInfoByNumber(form.getApplyNumber());
		if (infoList!=null&&infoList.size()!=0){
			if (infoList.get(0).getIsService()==1){
				result.setMsg("申请会员已是报单中心");
				result.setSuccess(false);
			}else{
				Information info = infoList.get(0);
				result.setMsgCode("姓名："+info.getName()+"；银行名称："+info.getBankName()+
						"；银行账号："+info.getBankCard()+"；银行地址："+info.getBankAddress()+
						"；联系电话："+info.getPhoneNumber());
				result.setExtension(info.getId().toString());
				result.setSuccess(true);
			}
		}else{
			result.setMsg("不存在该会员");
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping(value = "/submitApply",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> submitApply(@RequestBody ApplyServiceForm form,Model model,HttpSession sesison){
		BaseResult<Void> result = new BaseResult<Void>();
		Object logonUserO = sesison.getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		String userNaemO = (String) logonUserMap.get("username");
		List<Information> infoList = applyQualificationService.queryMemberInfoByNumber(userNaemO);
		if (infoList!=null&&infoList.size()!=0){
			if (infoList.get(0).getProtectPassword().equals(form.getProtectPassword())){
				ApplyService as = new ApplyService();
				as.setApplyId(form.getApplyId());
				as.setApplyNumber(form.getApplyNumber());
				as.setSubmitId(infoList.get(0).getId());
				as.setSubmitNumber(infoList.get(0).getNumber());
				as.setSubmitReason(form.getSubmitReason());
				as.setApplyDate(new Date());
				as.setState(0);
				applyQualificationService.submitApply(as);
				result.setMsg("提交申请成功");
				result.setSuccess(true);
			}else{
				result.setMsg("二级密码输入错误");
				result.setSuccess(false);
			}
		}
		return result;
	}
}
