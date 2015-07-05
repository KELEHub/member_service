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
import com.member.entity.ApplyService;
import com.member.entity.Information;
import com.member.entity.RepeatedMoneyStatistics;
import com.member.form.back.ApplyServiceForm;
import com.member.form.back.InformationForm;
import com.member.form.back.MemberSearchForm;
import com.member.helper.BaseResult;
import com.member.services.back.ServiceManagerService;
import com.member.util.CommonUtil;

@Controller
@RequestMapping(value = "/ServiceManagerController")
public class ServiceManagerController {

	@Resource(name = "ServiceManagerServiceImpl")
	public ServiceManagerService serviceManagerService;
	
	@RequestMapping(value = "/showServiceManager",method = RequestMethod.POST)
	public String showServiceManager(Model model){
		List<Information> result = serviceManagerService.getServiceByIsService(1);
		model.addAttribute("result", result);
		return "back/serviceManager/serviceInfo";
	}
	
	@RequestMapping(value = "/showApplyServiceManager",method = RequestMethod.POST)
	public String showApplyServiceManager(Model model){
		List<ApplyService> result = serviceManagerService.getApplyService();
		model.addAttribute("result", result);
		return "back/serviceManager/approveService";
	}

	@RequestMapping(value = "/showServiceInfoDetail",method = RequestMethod.POST)
	public String showServiceInfoDetail(@RequestBody MemberSearchForm form,Model model){
		Information result = serviceManagerService.getServiceById(form.getId());
		model.addAttribute("form",form);
		model.addAttribute("member", result);
		return "back/serviceManager/serviceInfoDetail";
	}
	
	@RequestMapping(value = "/serviceRecharge",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> serviceRecharge(@RequestBody InformationForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		serviceManagerService.updateInfo(form);
		result.setMsg("修改成功.");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/forbiddenService",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> forbiddenService(@RequestBody MemberSearchForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		serviceManagerService.forbiddenService(1,form.getId());
		result.setMsg("禁用成功.");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/applyCheckFailure",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> applyCheckFailure(@RequestBody ApplyServiceForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		serviceManagerService.updateApplyState(1,form.getId(),form.getFailureCause());
		result.setMsg("操作成功.");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/applyCheckSuccess",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> applyCheckSuccess(@RequestBody ApplyServiceForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		//更改申请表信息，更新上级报单中心的information信息
		serviceManagerService.updateApplyState(1,form.getId(),"");
//		Information superiorInfo = serviceManagerService.getServiceById(form.getSubmitId());
//		superiorInfo.setRepeatedMoney(superiorInfo.getRepeatedMoney().add(new BigDecimal(50)));
//		superiorInfo.setRepeatedAccumulative(superiorInfo.getRepeatedAccumulative().add(new BigDecimal(50)));
//		superiorInfo.setShoppingMoney(superiorInfo.getShoppingMoney().add(new BigDecimal(50)));
//		superiorInfo.setShoppingAccumulative(superiorInfo.getShoppingAccumulative().add(new BigDecimal(50)));
//		serviceManagerService.saveOrUpdate(superiorInfo);
		
		//更新下级报单中心information表信息
		Information juniorInfo = serviceManagerService.getServiceById(form.getApplyId());
		juniorInfo.setIsService(1);
		juniorInfo.setLeaderServiceId(form.getSubmitId());
		juniorInfo.setLeaderServiceNumber(form.getSubmitNumber());
		serviceManagerService.saveOrUpdate(juniorInfo);
		
		//在AccountDetails表记录上级报单中心获得服务积分明细
//		AccountDetails shopingDetails = new AccountDetails();
//		shopingDetails.setKindData(KindDataEnum.points);
//		/**日期类别统计 */
//		shopingDetails.setDateNumber(CommonUtil.getDateNumber());
//		/**流水号 */
//		shopingDetails.setCountNumber(CommonUtil.getCountNumber());
//		/**项目 */
//		shopingDetails.setProject(ProjectEnum.servicepointsforone);
//		/**积分余额 */
//		shopingDetails.setPointbalance(superiorInfo.getRepeatedMoney());
//		/**葛粮币余额 */
//		shopingDetails.setGoldmoneybalance(superiorInfo.getCrmMoney());
//		/**收入 */
//		shopingDetails.setIncome(new BigDecimal(50));
//		/**支出 */
//		shopingDetails.setPay(new BigDecimal(0));
//		/**备注 */
//		shopingDetails.setRedmin("审核报单中心通过");
//		/**用户ID */
//		shopingDetails.setUserId(superiorInfo.getId());
//		/**用户登录ID */
//		shopingDetails.setUserNumber(superiorInfo.getNumber());
//		shopingDetails.setCreateTime(new Date());
//		serviceManagerService.saveOrUpdate(shopingDetails);
		
		//在RepeatedMoneyStatistics表中添加一条记录
//		RepeatedMoneyStatistics moneyStatistics = new RepeatedMoneyStatistics();
//		moneyStatistics.setCreateTime(new Date());
//		moneyStatistics.setDateNumber(CommonUtil.getDateNumber());
//		moneyStatistics.setDeclarationId(superiorInfo.getId());
//		moneyStatistics.setDeclarationNumber(superiorInfo.getNumber());
//		moneyStatistics.setDeclarationBenefitId(superiorInfo.getLeaderServiceId());
//		moneyStatistics.setDeclarationBenefitNumber(superiorInfo.getLeaderServiceNumber());
//		moneyStatistics.setSerialNumber(CommonUtil.getCountNumber());
//		moneyStatistics.setState(0);
//		serviceManagerService.saveOrUpdate(moneyStatistics);
		
		result.setMsg("操作成功.");
		result.setSuccess(true);
		return result;
	}
}
