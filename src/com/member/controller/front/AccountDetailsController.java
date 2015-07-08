package com.member.controller.front;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.member.beans.back.enumData.KindDataEnum;
import com.member.beans.back.enumData.ProjectEnum;
import com.member.beans.front.AccountBean;
import com.member.entity.AccountDetails;
import com.member.entity.Information;
import com.member.form.front.SearchPointsForm;
import com.member.services.back.InformationService;
import com.member.services.front.AccountDetailsService;
import com.member.util.CommonUtil;


@Controller
@RequestMapping(value = "/AccountDetailsController")
public class AccountDetailsController {
	
	@Resource(name = "AccountDetailsServiceImpl")
	public AccountDetailsService accountDetailsService;
	
	@Resource(name = "InformationServiceImpl")
	public InformationService informationService;
	
	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public String show(Model model,HttpSession sesison) {
		try {
			Object logonUserO = sesison.getAttribute("logonUser");
			Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
			String userNaemO =(String) logonUserMap.get("username");
			Information info = informationService.getInformationByNumber(userNaemO);
			String condition = "userNumber=?";
			List arguments = new ArrayList();
			arguments.add(userNaemO);
			String sigleCodition = "";
			List<AccountBean> beanList = new ArrayList<AccountBean>();
			   sigleCodition = condition;
			  condition= sigleCodition + "and project != ? and project != ?";
			  sigleCodition= sigleCodition + "and (project = ? or project = ?) group by countNumber";
			  arguments.add(ProjectEnum.servicepointsformuch);
			  arguments.add(ProjectEnum.servicepointsforone);
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
			 model.addAttribute("goldmoneybalance",CommonUtil.insertComma(info.getCrmMoney().toString(),2));
			 model.addAttribute("pointsbalance",CommonUtil.insertComma(info.getShoppingMoney().toString(),2));
			 model.addAttribute("serverbalance",CommonUtil.insertComma(info.getRepeatedMoney().toString(),2));
			return "front/account/acountdetails";

		} catch (Exception e) {
			e.printStackTrace();
			return "front/account/acountdetails";
		}

	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectData", method = RequestMethod.POST)
	public String selectData(@RequestBody SearchPointsForm form,Model model,HttpSession sesison) {
		try {
			  if(!"space".equals(form.getYearFlg())){
				  if("space".equals(form.getMonthFlg())){
					  model.addAttribute("error","请选择月份");
					  model.addAttribute("goldFlg", form.getGoldFlg());
					  model.addAttribute("projectFlg", form.getProjectFlg());
					  model.addAttribute("monthFlg", form.getMonthFlg());
					  model.addAttribute("yearFlg", form.getYearFlg());
					  return "front/account/acountdetails";
				  }
			  }
			  if(!"space".equals(form.getMonthFlg())){
				  if("space".equals(form.getYearFlg())){
					  model.addAttribute("error","请选择年");
					  model.addAttribute("goldFlg", form.getGoldFlg());
					  model.addAttribute("projectFlg", form.getProjectFlg());
					  model.addAttribute("monthFlg", form.getMonthFlg());
					  model.addAttribute("yearFlg", form.getYearFlg());
					  return "front/account/acountdetails";
				  }
			  }
			  Object logonUserO = sesison.getAttribute("logonUser");
			  Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
			  String userNaemO =(String) logonUserMap.get("username");
			  Information info = informationService.getInformationByNumber(userNaemO);
			  String condition = "userNumber=?";
			  String sigleCodition="";
			  List arguments = new ArrayList();
			  arguments.add(userNaemO);
			  int flg=0;
			  if(!"space".equals(form.getGoldFlg())){
				  condition= condition + " and kindData=? ";
				  KindDataEnum kindEum=getKindDataEnum(form.getGoldFlg());
				  arguments.add(kindEum);
			  }
			  if(!"space".equals(form.getMonthFlg())){
				  condition= condition + " and createTime>? and createTime<? ";
				  arguments.add(fromDate(form.getYearFlg(),form.getMonthFlg()));
				  arguments.add(toDate(form.getYearFlg(),form.getMonthFlg()));
			  }
			  if(!"space".equals(form.getProjectFlg())){
				  //project
				  if("servicepoints".equals(form.getProjectFlg())){
					  condition= condition + "and (project = ? or project = ?) group by countNumber";
					  arguments.add(ProjectEnum.servicepointsformuch);
					  arguments.add(ProjectEnum.servicepointsforone);
					  flg=1;
				  }else{
					  condition= condition + " and project=?";
					  arguments.add(getProjectEnum(form.getProjectFlg()));
				  }
				  
			  }else{
				  sigleCodition = condition;
				  condition= sigleCodition + "and project != ? and project != ?";
				  sigleCodition= sigleCodition + "and (project = ? or project = ?) group by countNumber";
				  arguments.add(ProjectEnum.servicepointsformuch);
				  arguments.add(ProjectEnum.servicepointsforone);
				  flg=2;
			  }
			  List<AccountBean> beanList = new ArrayList<AccountBean>();
			  
			  
			  
			 if(flg==0){
				 condition = condition+"  order by createTime desc";
				  List<AccountDetails> accountDetailsList = accountDetailsService.getAccountDetailsByNoservicepoints(condition,arguments);
				  for(AccountDetails ad : accountDetailsList){
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
			 }
			 if(flg==1){
				  condition = condition+"  order by countNumber desc";
				 List<AccountDetails> accountDetailsList = accountDetailsService.getAccountDetailsByservicepoints(condition,arguments);
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
			 }
			 if(flg==2){
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
			 }
			  model.addAttribute("result",beanList);
			  model.addAttribute("goldFlg", form.getGoldFlg());
			  model.addAttribute("projectFlg", form.getProjectFlg());
			  model.addAttribute("monthFlg", form.getMonthFlg());
			  model.addAttribute("yearFlg", form.getYearFlg());
			  model.addAttribute("goldmoneybalance",CommonUtil.insertComma(info.getCrmMoney().toString(),2));
			  model.addAttribute("pointsbalance",CommonUtil.insertComma(info.getShoppingMoney().toString(),2));
			  model.addAttribute("serverbalance",CommonUtil.insertComma(info.getRepeatedMoney().toString(),2));
			  return "front/account/acountdetails";

		} catch (Exception e) {
			e.printStackTrace();
			return "front/account/acountdetails";
		}

	}
	
	
	private KindDataEnum getKindDataEnum(String kind){
		if("crmMoney".equals(kind)){
			return KindDataEnum.goldmoney;
		}else{
			return KindDataEnum.points;
		}
	}
	
	private String getKindDataName(KindDataEnum kind){
		if(kind.equals(KindDataEnum.goldmoney)){
			return "葛粮币";
		}else{
			return "积分";
		}
	}
	
	private ProjectEnum getProjectEnum(String project){
		if("tootherman".equals(project)){
			return ProjectEnum.tootherman;
		}
		if("recharge".equals(project)){
			return ProjectEnum.recharge;
		}
		if("fromback".equals(project)){
			return ProjectEnum.fromback;
		}
		if("frompointsadd".equals(project)){
			return ProjectEnum.frompointsadd;
		}
//		if("togoldmoneycut".equals(project)){
//			return ProjectEnum.togoldmoneycut;
//		}
		if("pointcash".equals(project)){
			return ProjectEnum.pointcash;
		}
		if("fromgifts".equals(project)){
			return ProjectEnum.fromgifts;
		}
		if("activateMember".equals(project)){
			return ProjectEnum.activateMember;
		}
		if("cost".equals(project)){
			return ProjectEnum.cost;
		}
		return null;
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
	
	private Date fromDate(String year,String month){
		
		String pattern = "yyyyMMdd"; 
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String toConvertString = "";
		if(month.length()<2){
			 toConvertString = year+"0" +month+"01";
		}else{
			toConvertString = year+month+"01";
		}
		try {
			return format.parse(toConvertString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
    private Date toDate(String year,String month){
		String nextMoth = String.valueOf(Integer.valueOf(month) + 1);
		String pattern = "yyyyMMdd"; 
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String toConvertString = "";
		if(nextMoth.length()<2){
			 toConvertString = year+"0" +nextMoth+"01";
		}else{
			 toConvertString = year+nextMoth+"01";
		}
		try {
			return format.parse(toConvertString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}

}
