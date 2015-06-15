package com.member.controller.back;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.member.beans.back.enumData.ProjectEnum;
import com.member.entity.AccountDetails;
import com.member.entity.RepeatedMoneyStatistics;
import com.member.form.back.IntegralHistoryForm;
import com.member.form.back.RangeIssueForm;
import com.member.services.back.IntegralManagerService;
import com.member.util.CommonUtil;

@Controller
@RequestMapping(value = "/IntegralManagerController")
public class IntegralManagerController {
	
	@Resource(name = "IntegralManagerServiceImpl")
	public IntegralManagerService integralManagerService;

	@RequestMapping(value = {"/showIntegralHistory"},method = RequestMethod.POST)
	public String showIntegralHistory(Model model){
		List<AccountDetails> list = integralManagerService.getIntegralHistoryPoints();
		List<IntegralHistoryForm> result = new ArrayList<IntegralHistoryForm>();
		if(list!=null &&list.size()>0){
			for(AccountDetails ad : list){
				IntegralHistoryForm form =new IntegralHistoryForm();
				form.setCreateTime(ad.getCreateTime().toString());
				form.setIncome(ad.getIncome().toString());
				form.setPay(ad.getPay().toString());
				form.setPointbalance(ad.getPointbalance().toString());
				form.setUserNumber(ad.getUserNumber());
				if(ad.getProject().equals(ProjectEnum.servicepointsformuch)){
					form.setProject("极差服务积分");
				}else{
					form.setProject("礼包释放积分");
				}
				result.add(form);
			}
		}
		model.addAttribute("result", result);
		return "back/integralManager/integralIssueHistory";
	}
	
	@RequestMapping(value = "/showRangeIntegralIssueManager",method = RequestMethod.POST)
	public String showRangeIntegralIssueManager(Model model){
		List<RangeIssueForm> result = integralManagerService.getAvailableRangeIntegral(CommonUtil.getCountNumber());
		model.addAttribute("result", result);
		return "back/integralManager/rangeIssue";
	}
	
	
	@RequestMapping(value = "/serch", method = RequestMethod.POST)
	public String serch(@RequestBody IntegralHistoryForm form, Model model) {
		try {

			List<AccountDetails> list  = integralManagerService.getIntegralHistoryPointsByNumber(form
					.getUserNumber());
			List<IntegralHistoryForm> result = new ArrayList<IntegralHistoryForm>();
			if(list!=null &&list.size()>0){
				for(AccountDetails ad : list){
					IntegralHistoryForm ifForm =new IntegralHistoryForm();
					ifForm.setCreateTime(ad.getCreateTime().toString());
					ifForm.setIncome(ad.getIncome().toString());
					ifForm.setPay(ad.getPay().toString());
					ifForm.setPointbalance(ad.getPointbalance().toString());
					ifForm.setUserNumber(ad.getUserNumber());
					if(ad.getProject().equals(ProjectEnum.servicepointsformuch)){
						form.setProject("极差服务积分");
					}else{
						form.setProject("礼包释放积分");
					}
					result.add(ifForm);
				}
			}

			model.addAttribute("result", result);
			return "back/integralManager/integralIssueHistory";
		} catch (Exception e) {
			e.printStackTrace();
			return "back/integralManager/integralIssueHistory";
		}

	}
	
}
