package com.member.controller.back;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.beans.back.enumData.KindDataEnum;
import com.member.beans.back.enumData.ProjectEnum;
import com.member.beans.front.AccountBean;
import com.member.entity.AccountDetails;
import com.member.entity.Information;
import com.member.form.back.MemberSearchForm;
import com.member.form.back.SearchBackForm;
import com.member.services.back.InformationService;
import com.member.services.front.AccountDetailsService;
import com.member.util.CommonUtil;
@Controller
@RequestMapping(value = "/BackAccountController")
public class BackAccountController {
	
	@Resource(name = "AccountDetailsServiceImpl")
	public AccountDetailsService accountDetailsService;
	
	@Resource(name = "InformationServiceImpl")
	public InformationService informationService;
	
	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public String show(Model model,HttpSession sesison) {
		try {

			return "back/account/backacountdetails";

		} catch (Exception e) {
			e.printStackTrace();
			return "back/account/backacountdetails";
		}

	}
	
	
	@RequestMapping(value = "/getAccountDataPage")
	@ResponseBody
	public Map getAccountDataPage(HttpServletRequest request,Model model) {
		MemberSearchForm form = new MemberSearchForm();
		String number = request.getParameter("number");
		  List<AccountBean> result = new ArrayList<AccountBean>();
		  int iTotalRecords =0;
		if(number!=null &&  !"".equals(number)){
			  String condition = "userNumber=?";
			  String sigleCodition="";
			  List arguments = new ArrayList();
			  arguments.add(number);
				String iDisplayLength = request.getParameter("iDisplayLength");	
				String iDisplayStart = request.getParameter("iDisplayStart");
				int pageNumber = Integer.parseInt(iDisplayStart)/Integer.parseInt(iDisplayLength)+1;
				iTotalRecords = accountDetailsService.couontDetails(condition,arguments);
				if(iTotalRecords!=0){
					float  t = (float)iTotalRecords/10;
					int cc = (int)Math.ceil(t);
					if(pageNumber>cc){
						pageNumber=1;
					}
				}
			
			  condition = condition+"  order by createTime desc";
				 List<AccountDetails> accountList = accountDetailsService.getAccountDetailsByNoservicepoints(condition,arguments,Integer.parseInt(iDisplayLength),
							pageNumber);
				  for(AccountDetails ad : accountList){
					  AccountBean bean= new AccountBean();
					  bean.setKindData(getKindDataName(ad.getKindData()));
					  bean.setCreateTime(ad.getCreateTime().toString());
					  bean.setIncome(ad.getIncome().toString());
					  bean.setPay(ad.getPay().toString());
					  bean.setPointbalance(ad.getPointbalance().toString());
					  bean.setGoldmoneybalance(ad.getGoldmoneybalance().toString());
					  bean.setRedmin(ad.getRedmin());
					  bean.setProject(getProjectName(ad.getProject()));
					  result.add(bean);
				  }

		}
		
		Map map = new HashMap();
		map.put("aaData", result);
		// 查出来总共有多少条记录
		map.put("iTotalRecords", iTotalRecords);
		map.put("iTotalDisplayRecords",iTotalRecords);
		return map;
	}
	

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectData", method = RequestMethod.POST)
	public String selectData(@RequestBody SearchBackForm form,Model model,HttpSession sesison) {
		try {
			
			  if("".equals(form.getNumber()) || form.getNumber() ==null){
					  model.addAttribute("error","请输入会员号");
					  return "back/account/backacountdetails";
			  }
			  Information info = informationService.getInformationByNumber(form.getNumber());
			  if(info == null){
				  model.addAttribute("error","该会员不存在");
				  return "back/account/backacountdetails";
			  }

			  model.addAttribute("goldFlg", form.getGoldFlg());
			  model.addAttribute("projectFlg", form.getProjectFlg());
			  model.addAttribute("monthFlg", form.getMonthFlg());
			  model.addAttribute("yearFlg", form.getYearFlg());
			  model.addAttribute("number", form.getNumber());
			  model.addAttribute("goldmoneybalance",CommonUtil.insertComma(info.getCrmMoney().toString(),2));
			  model.addAttribute("pointsbalance",CommonUtil.insertComma(info.getShoppingMoney().toString(),2));
			  model.addAttribute("serverbalance",CommonUtil.insertComma(info.getRepeatedMoney().toString(),2));

			  return "back/account/backacountdetails";

		} catch (Exception e) {
			e.printStackTrace();
			return "back/account/backacountdetails";
		}

	}
	
	
	private String getKindDataName(KindDataEnum kind){
		if(kind.equals(KindDataEnum.goldmoney)){
			return "葛粮币";
		}else{
			return "积分";
		}
	}
	
	private String getProjectName(ProjectEnum project){
		if(project.equals(ProjectEnum.tootherman)){
			return "会员转账";
		}
		if(project.equals(ProjectEnum.recharge)){
			return "充值";
		}
		if(project.equals(ProjectEnum.fromback)){
			return "后台调整";
		}
		if(project.equals(ProjectEnum.frompointsadd)){
			return "积分转葛粮币";
		}
//		if(project.equals(ProjectEnum.togoldmoneycut)){
//			return "积分转葛粮币减少";
//		}
		if(project.equals(ProjectEnum.pointcash)){
			return "积分提现";
		}
		if(project.equals(ProjectEnum.servicepointsforone)){
			return "服务积分";
		}
		if(project.equals(ProjectEnum.servicepointsformuch)){
			return "服务积分";
		}
		if(project.equals(ProjectEnum.fromgifts)){
			return "礼包发放";
		}
		if(project.equals(ProjectEnum.activateMember)){
			return "激活会员";
		}
		if(project.equals(ProjectEnum.cost)){
			return "扣费";
		}
		if(project.equals(ProjectEnum.banckPoints)){
			return "积分提现返还";
		}
		if(project.equals(ProjectEnum.cancelData)){
			return "取消申请返还";
		}
		return null;
	}
	
}
