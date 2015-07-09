package com.member.controller.back;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.beans.back.enumData.KindDataEnum;
import com.member.beans.back.enumData.ProjectEnum;
import com.member.entity.AccountDetails;
import com.member.entity.Information;
import com.member.form.back.MemberDeleteForm;
import com.member.form.back.MemberSaveForm;
import com.member.form.back.MemberSearchForm;
import com.member.helper.BaseResult;
import com.member.services.back.GiftsDetailsService;
import com.member.services.back.InformationService;
import com.member.services.back.MemberManageService;
import com.member.util.CommonUtil;

@Controller
@RequestMapping(value = "/membermanage")
public class MemberManageController {

	@Resource(name="MemberManageServiceImpl")
	private MemberManageService memberManageService;
	
	@Resource(name="GiftsDetailsServiceImpl")
	private GiftsDetailsService giftsDetailsService;
	
	@Resource(name = "InformationServiceImpl")
	public InformationService informationService;
	
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
	
	@RequestMapping(value = "/showMemberManagement",method = RequestMethod.POST)
	public String showMemberManagement(Model model) {
		MemberSearchForm form = new MemberSearchForm();
		List<Information> result = memberManageService.getActiveMembers(form);
		model.addAttribute("result",result);
		return "back/membermanage/memberManagement";
	}
	
	@RequestMapping(value = "/searchMemberManagement",method = RequestMethod.POST)
	public String searchMemberManagement(@RequestBody MemberSearchForm form,Model model) {
		List<Information> result = memberManageService.getActiveMembers(form);
		model.addAttribute("form",form);
		model.addAttribute("result",result);
		return "back/membermanage/memberManagement";
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
		memberManageService.deleteMember(form);
		result.setMsg("删除会员信息成功.");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/saveMemberDetail",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> saveMemberDetail(@RequestBody MemberSaveForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		
		Information info = informationService.getInformationByNumber(form.getNumber());
		if(!info.getBankCard().equals(form.getBankCard())){
			if(informationService.countBankCard(form.getBankCard())>=2){
				result.setMsg("系统已存在两张相同的银行卡，请换卡");
				result.setSuccess(true);
				return result;
			}
		}
		
		memberManageService.updateMemberDetails(form);
		result.setMsg("修改会员详细信息成功.");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/deleteActiveMember",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> deleteActiveMember(@RequestBody MemberDeleteForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		AccountDetails serviceAD = new AccountDetails();
		AccountDetails memberAD = new AccountDetails();
		BigDecimal sum = new BigDecimal(0.00);
		BigDecimal shoppingMoneySurplus = new BigDecimal(0.00);
		BigDecimal repeatedMoneySurplus = new BigDecimal(0.00);
//		if (form.getIsService()==1 && form.getLeaderServiceId()!= null){
			List<Information> memberInfo = memberManageService.getMemberInfoById(form.getActivateId());
			serviceAD.setKindData(KindDataEnum.points);
			serviceAD.setDateNumber(CommonUtil.getDateNumber());
			serviceAD.setCountNumber(CommonUtil.getCountNumber());
			serviceAD.setProject(ProjectEnum.cost);
			serviceAD.setPointbalance(memberInfo.get(0).getShoppingMoney());
			serviceAD.setGoldmoneybalance(memberInfo.get(0).getCrmMoney());
			serviceAD.setIncome(new BigDecimal(0));
			serviceAD.setPay(new BigDecimal(50));
			serviceAD.setUserId(form.getLeaderServiceId());
			serviceAD.setUserNumber(form.getLeaderServiceNumber());
			serviceAD.setCreateTime(new Date());
//		}
		if (form.getRecommendNumber()!=null){
			//查询是否需要删除礼包，如果需要，则查询账户明细表
			Boolean ifDelete = giftsDetailsService.DeleteGifts(form.getNumber());
//			if (ifDelete){
//				List<AccountDetails> adList = memberManageService.getAccountDetailsByProjectAndUserId(ProjectEnum.fromgifts,form.getId());
//				
//				for (AccountDetails ad : adList){
//					sum = sum.add(ad.getIncome());
//				}
//				List<Information> memberInfo = memberManageService.getMemberInfoById(form.getRecommendId());
//				memberAD.setKindData(KindDataEnum.points);
//				memberAD.setDateNumber(CommonUtil.getDateNumber());
//				memberAD.setCountNumber(CommonUtil.getCountNumber());
//				memberAD.setProject(ProjectEnum.cost);
//				memberAD.setPointbalance(memberInfo.get(0).getShoppingMoney());
//				memberAD.setGoldmoneybalance(memberInfo.get(0).getCrmMoney());
//				memberAD.setIncome(new BigDecimal(0));
//				memberAD.setPay(sum);
//				memberAD.setUserId(form.getRecommendId());
//				memberAD.setUserNumber(form.getRecommendNumber());
//				memberAD.setCreateTime(new Date());
//				
//				shoppingMoneySurplus = memberInfo.get(0).getShoppingMoney().subtract(sum);
//				repeatedMoneySurplus = memberInfo.get(0).getRepeatedMoney().subtract(sum);
//			}
		}
		memberManageService.deleteActiveMember(form.getId(),form.getNumber(), form.getIsService(), form.getRecommendId(), form.getActivateId(),serviceAD,memberAD,sum,shoppingMoneySurplus,repeatedMoneySurplus);
		result.setMsg("删除会员成功.");
		result.setSuccess(true);
		return result;
	}
}
