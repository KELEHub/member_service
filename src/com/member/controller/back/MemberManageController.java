package com.member.controller.back;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
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

import com.member.beans.back.enumData.KindDataEnum;
import com.member.beans.back.enumData.ProjectEnum;
import com.member.entity.AccountDetails;
import com.member.entity.Information;
import com.member.entity.TestPageEntity;
import com.member.form.back.MemberDeleteForm;
import com.member.form.back.MemberSaveForm;
import com.member.form.back.MemberSearchForm;
import com.member.helper.BaseResult;
import com.member.services.back.GiftsDetailsService;
import com.member.services.back.InformationService;
import com.member.services.back.MemberManageService;
import com.member.services.front.AccountDetailsService;
import com.member.util.CommonUtil;

@SuppressWarnings("unchecked")
@Controller
@RequestMapping(value = "/membermanage")
public class MemberManageController {

	@Resource(name="MemberManageServiceImpl")
	private MemberManageService memberManageService;
	
	@Resource(name="GiftsDetailsServiceImpl")
	private GiftsDetailsService giftsDetailsService;
	
	@Resource(name = "InformationServiceImpl")
	public InformationService informationService;
	
	@Resource(name = "AccountDetailsServiceImpl")
	public AccountDetailsService accountDetailsService;
	
	@RequestMapping(value = "/showActivationMember",method = RequestMethod.POST)
	public String showActivationMember(Model model) {
		return "back/membermanage/showactivemember";
	}
	
	@RequestMapping(value = "/getActivationMemberPage")
	@ResponseBody
	public Map getActivationMemberPage(HttpServletRequest request,Model model) {
		MemberSearchForm form = new MemberSearchForm();
		String number = request.getParameter("number");
		String recommendNumber = request.getParameter("recommendNumber");
		String serviceNumber = request.getParameter("serviceNumber");
		String iDisplayLength = request.getParameter("iDisplayLength");	
		String iDisplayStart = request.getParameter("iDisplayStart");
		int pageNumber = Integer.parseInt(iDisplayStart)/Integer.parseInt(iDisplayLength)+1;
		List<Information> result = memberManageService.getActiveMembers(form,number,recommendNumber,serviceNumber,
				Integer.parseInt(iDisplayLength),
				pageNumber);
		int iTotalRecords = memberManageService.countData(form,number,recommendNumber,serviceNumber,1);
		model.addAttribute("result", result);
		Map map = new HashMap();
		map.put("aaData", result);
		// 查出来总共有多少条记录
		map.put("iTotalRecords", iTotalRecords);
		map.put("iTotalDisplayRecords",iTotalRecords);
		return map;
	}
	
	@RequestMapping(value = "/showNotActivationMember",method = RequestMethod.POST)
	public String showNotActivationMember(Model model) {
		return "back/membermanage/shownotactivemember";
	}
	
//	@RequestMapping(value = "/getNotActivationMemberPage")
//	@ResponseBody
//	public Map getNotActivationMemberPage(HttpServletRequest request,Model model) {
//		MemberSearchForm form = new MemberSearchForm();
//		String customerPar = request.getParameter("customerPar");
//		String iDisplayLength = request.getParameter("iDisplayLength");
//		String iDisplayStart = request.getParameter("iDisplayStart");
//		int pageNumber = Integer.parseInt(iDisplayStart)/Integer.parseInt(iDisplayLength)+1;
//		List<Information> result = memberManageService.getNotActiveMembers(form,customerPar,
//				Integer.parseInt(iDisplayLength),
//				pageNumber);
//		int iTotalRecords = memberManageService.countData(form,customerPar,0);
//		model.addAttribute("result", result);
//		Map map = new HashMap();
//		map.put("aaData", result);
//		// 查出来总共有多少条记录
//		map.put("iTotalRecords", iTotalRecords);
//		map.put("iTotalDisplayRecords",iTotalRecords);
//		return map;
//	}
	
	@RequestMapping(value = "/showMemberManagement",method = RequestMethod.POST)
	public String showMemberManagement(Model model) {
		return "back/membermanage/memberManagement";
	}
	
//	@RequestMapping(value = "/getMemberManagementPage")
//	@ResponseBody
//	public Map getMemberManagementPage(HttpServletRequest request,Model model) {
//		MemberSearchForm form = new MemberSearchForm();
//		String customerPar = request.getParameter("customerPar");
//		String iDisplayLength = request.getParameter("iDisplayLength");
//		String iDisplayStart = request.getParameter("iDisplayStart");
//		int pageNumber = Integer.parseInt(iDisplayStart)/Integer.parseInt(iDisplayLength)+1;
//		List<Information> result = memberManageService.getActiveMembers(form,customerPar,
//				Integer.parseInt(iDisplayLength),
//				pageNumber);
//		int iTotalRecords = memberManageService.countData(form,customerPar,1);
//		model.addAttribute("result", result);
//		Map map = new HashMap();
//		map.put("aaData", result);
//		// 查出来总共有多少条记录
//		map.put("iTotalRecords", iTotalRecords);
//		map.put("iTotalDisplayRecords",iTotalRecords);
//		return map;
//	}
	
//	@RequestMapping(value = "/searchMemberManagement",method = RequestMethod.POST)
//	public Map searchMemberManagement(@RequestBody MemberSearchForm form,Model model) {
//		List<Information> result = memberManageService.getActiveMembers(form,null,1,1);
//		model.addAttribute("form",form);
//		model.addAttribute("result",result);
//		Map map = new HashMap();
//		map.put("aaData", result);
//		// 查出来总共有多少条记录
//		map.put("iTotalRecords", 1);
//		map.put("iTotalDisplayRecords",1);
//		return map;
////		return "back/membermanage/memberManagement";
//	}
	
	@RequestMapping(value = "/searchActivationMember",method = RequestMethod.POST)
	public String searchActivationMember(@RequestBody MemberSearchForm form,Model model) {
		List<Information> result = null;
		model.addAttribute("form",form);
		model.addAttribute("result",result);
		return "back/membermanage/showactivemember";
	}
	
	@RequestMapping(value = "/searchNotActivationMember",method = RequestMethod.POST)
	public String searchNotActivationMember(@RequestBody MemberSearchForm form,Model model) {
		List<Information> result =null;
		model.addAttribute("form",form);
		model.addAttribute("result",result);
		return "back/membermanage/shownotactivemember";
	}
	
	@RequestMapping(value = "/showMemberDetails",method = RequestMethod.POST)
	public String showMemberDetails(@RequestBody MemberSearchForm form,Model model){
		Information member = memberManageService.getMemberById(form.getId());
		member.setServiceCount(accountDetailsService.getCountServerPointByNumber(member.getNumber(),String.valueOf(CommonUtil.getCountNumber())));
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
