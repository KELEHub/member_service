package com.member.controller.front;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
			 model.addAttribute("goldmoneybalance",CommonUtil.insertComma(info.getCrmMoney().toString(),2));
			 model.addAttribute("pointsbalance",CommonUtil.insertComma(info.getShoppingMoney().toString(),2));
			 model.addAttribute("serverbalance",CommonUtil.insertComma(info.getRepeatedMoney().toString(),2));
			return "front/account/acountdetails";

		} catch (Exception e) {
			e.printStackTrace();
			return "front/account/acountdetails";
		}

	}
	
	
	
	
	@RequestMapping(value = "/getFrontAccountDataPage")
	@ResponseBody
	public Map getFrontAccountDataPage(HttpServletRequest request,Model model,HttpSession sesison) {
		Object logonUserO = sesison.getAttribute("logonUser");
		Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
		String userNaemO =(String) logonUserMap.get("username");
		Information info = informationService.getInformationByNumber(userNaemO);
		String condition = "userNumber=?";
		List arguments = new ArrayList();
		arguments.add(userNaemO);
		List<AccountBean> result = new ArrayList<AccountBean>();
		int iTotalRecords =0;
		if(userNaemO!=null &&  !"".equals(userNaemO)){
				String iDisplayLength = request.getParameter("iDisplayLength");	
				String iDisplayStart = request.getParameter("iDisplayStart");
				String goldFlg = request.getParameter("goldFlg");
				String monthFlg = request.getParameter("monthFlg");
				String yearFlg = request.getParameter("yearFlg");
				String projectFlg = request.getParameter("projectFlg");
				 if(!"space".equals(goldFlg) && goldFlg!=null){
					  condition= condition + " and kindData=? ";
					  KindDataEnum kindEum=getKindDataEnum(goldFlg);
					  arguments.add(kindEum);
				  }
				  if(!"space".equals(monthFlg) && monthFlg!=null && !"space".equals(yearFlg) && yearFlg!=null){
					  condition= condition + " and createTime>? and createTime<? ";
					  arguments.add(fromDate(yearFlg,monthFlg));
					  arguments.add(toDate(yearFlg,monthFlg));
				  }
				  if(!"space".equals(projectFlg)  && projectFlg!=null){
					  if(ProjectEnum.servicepointsforone.equals(getProjectEnum(projectFlg))){
						  condition= condition + " and (project=? )";
						  arguments.add(ProjectEnum.servicepointsforone);
					  }else{
						  condition= condition + " and project=?";
						  arguments.add(getProjectEnum(projectFlg));
					  }
						  
						  
				  }else{
					  condition= condition + " and project!=? ";
					  arguments.add(ProjectEnum.servicepointsformuch);
				  }
				
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
							pageNumber,userNaemO);
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
			  model.addAttribute("goldFlg", form.getGoldFlg());
			  model.addAttribute("projectFlg", form.getProjectFlg());
			  model.addAttribute("monthFlg", form.getMonthFlg());
			  model.addAttribute("yearFlg", form.getYearFlg());
			  Object logonUserO = sesison.getAttribute("logonUser");
			  Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
			  String userNaemO =(String) logonUserMap.get("username");
			  Information info = informationService.getInformationByNumber(userNaemO);
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
		if("servicepoints".equals(project)){
			return ProjectEnum.servicepointsforone;
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
		if("banckPoints".equals(project)){
			return ProjectEnum.banckPoints;
		}
		if("cancelData".equals(project)){
			return ProjectEnum.cancelData;
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
		if(project.equals(ProjectEnum.servicepointsforone)){
			return "服务积分";
		}
		if(project.equals(ProjectEnum.servicepointsformuch)){
			return "服务积分";
		}
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
		if(project.equals(ProjectEnum.banckPoints)){
			return "积分提现返还";
		}
		if(project.equals(ProjectEnum.cancelData)){
			return "取消申请返还";
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
