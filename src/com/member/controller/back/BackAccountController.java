package com.member.controller.back;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.member.beans.back.enumData.KindDataEnum;
import com.member.beans.back.enumData.ProjectEnum;
import com.member.beans.front.AccountBean;
import com.member.entity.AccountDetails;
import com.member.entity.Information;
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
			  String condition = "userNumber=?";
			  String sigleCodition="";
			  List arguments = new ArrayList();
			  arguments.add(form.getNumber());
				  sigleCodition = condition;
				  condition= sigleCodition + "and project != ? and project != ?";
				  sigleCodition= sigleCodition + "and (project = ? or project = ?) group by countNumber";
				  arguments.add(ProjectEnum.servicepointsformuch);
				  arguments.add(ProjectEnum.servicepointsforone);
			  List<AccountBean> beanList = new ArrayList<AccountBean>();
			  
				 sigleCodition = sigleCodition+"  order by countNumber desc";
				 List<AccountDetails> accountDetailsList = accountDetailsService.getAccountDetailsByservicepoints(sigleCodition,arguments);
				 if(accountDetailsList!=null && accountDetailsList.size()>0){
					 for(AccountDetails ads : accountDetailsList){
						 AccountBean bean= new AccountBean();
						  bean.setKindData("积分");
						  bean.setCreateTime(ads.getCountNumber().toString());
						  bean.setIncome(ads.getIncome().toString());
						  bean.setPay(ads.getPay().toString());
						  bean.setRedmin("该天的服务积分合计");
						  bean.setProject("服务积分");
						  beanList.add(bean);
					 }
				 }
				 condition = condition+"  order by createTime desc";
				 List<AccountDetails> accountList = accountDetailsService.getAccountDetailsByNoservicepoints(condition,arguments);
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
					  beanList.add(bean);
				  }
			  model.addAttribute("result",beanList);
			  model.addAttribute("goldFlg", form.getGoldFlg());
			  model.addAttribute("projectFlg", form.getProjectFlg());
			  model.addAttribute("monthFlg", form.getMonthFlg());
			  model.addAttribute("yearFlg", form.getYearFlg());
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
		if(project.equals(ProjectEnum.fromgifts)){
			return "礼包发放";
		}
		if(project.equals(ProjectEnum.activateMember)){
			return "激活会员";
		}
		if(project.equals(ProjectEnum.cost)){
			return "扣费";
		}
		return null;
	}
	
}
