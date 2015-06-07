package com.member.controller.back;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.Information;
import com.member.form.back.MemberSaveForm;
import com.member.form.back.MemberSearchForm;
import com.member.helper.BaseResult;
import com.member.services.back.MemberManageService;

@Controller
@RequestMapping(value = "/membermanage")
public class MemberManageController {

	@Resource(name="MemberManageServiceImpl")
	private MemberManageService memberManageService;
	
	@RequestMapping(value = "/showActivationMember",method = RequestMethod.POST)
	public String showActivationMember(Model model) {
		MemberSearchForm form = new MemberSearchForm();
		List<Information> result = memberManageService.getActiveMembers(form);
		model.addAttribute("result",result);
		return "back/membermanage/showactivemember";
	}
	
	@RequestMapping(value = "/showNotActivationMember",method = RequestMethod.POST)
	public String showNotActivationMember(Model model) {
		MemberSearchForm form = new MemberSearchForm();
		List<Information> result = memberManageService.getNotActiveMembers(form);
		model.addAttribute("result",result);
		return "back/membermanage/shownotactivemember";
	}
	
	@RequestMapping(value = "/searchActivationMember",method = RequestMethod.POST)
	public String searchActivationMember(@RequestBody MemberSearchForm form,Model model) {
		List<Information> result = memberManageService.getActiveMembers(form);
		model.addAttribute("form",form);
		model.addAttribute("result",result);
		return "back/membermanage/showactivemember";
	}
	
	@RequestMapping(value = "/searchNotActivationMember",method = RequestMethod.POST)
	public String searchNotActivationMember(@RequestBody MemberSearchForm form,Model model) {
		List<Information> result = memberManageService.getNotActiveMembers(form);
		model.addAttribute("form",form);
		model.addAttribute("result",result);
		return "back/membermanage/shownotactivemember";
	}
	
	@RequestMapping(value = "/showMemberDetails",method = RequestMethod.POST)
	public String showMemberDetails(@RequestBody MemberSearchForm form,Model model){
		Information member = memberManageService.getMemberById(form.getId());
		model.addAttribute("form",form);
		model.addAttribute("member",member);
		return "back/membermanage/showmemberdetail";
	}
	
	@RequestMapping(value = "/doMemberLock",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> doMemberLock(@RequestBody MemberSearchForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		memberManageService.updateMemberLockFlag(form.getId());
		result.setMsg("锁定用户成功.");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/doResetPwd",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> doResetPwd(@RequestBody MemberSearchForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		memberManageService.updateMemberPassword(form.getId());
		result.setMsg("重置密码成功.");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/deleteMember",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> deleteMember(@RequestBody MemberSaveForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
//		memberManageService.deleteMember(form);
		result.setMsg("删除会员信息成功.");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/saveMemberDetail",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> saveMemberDetail(@RequestBody MemberSaveForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		memberManageService.updateMemberDetails(form);
		result.setMsg("修改会员详细信息成功.");
		result.setSuccess(true);
		return result;
	}
}
