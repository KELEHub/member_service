package com.member.controller.back;

import java.math.BigDecimal;
import java.util.ArrayList;
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
		
		return "back/integralManager/integralIssueHistory";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getIntegralHistory")
	@ResponseBody
	public Map getIntegralHistory(HttpServletRequest request,Model model) {
		String number = request.getParameter("number");
		String iDisplayLength = request.getParameter("iDisplayLength");	
		String iDisplayStart = request.getParameter("iDisplayStart");
		int pageNumber = Integer.parseInt(iDisplayStart)/Integer.parseInt(iDisplayLength)+1;
		int iTotalRecords = integralManagerService.countIntegralHistoryPoints(number);
		if(iTotalRecords!=0){
			float  t = (float)iTotalRecords/10;
			int cc = (int)Math.ceil(t);
			if(pageNumber>cc){
				pageNumber=1;
			}
		}
		List<AccountDetails> list = integralManagerService.getIntegralHistoryPoints(number,Integer.parseInt(iDisplayLength),pageNumber);
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
		
		model.addAttribute("result", result);
		Map map = new HashMap();
		map.put("aaData", result);
		// 查出来总共有多少条记录
		map.put("iTotalRecords", iTotalRecords);
		map.put("iTotalDisplayRecords",iTotalRecords);
		return map;
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
	
	@RequestMapping(value = "/doRangeIssue", method = RequestMethod.POST)
	public String doRangeIssue(@RequestBody RangeIssueForm form, Model model) {
		try {
			String year = form.getYear();
			String month = form.getMonth();
			int serialNumber = 0;
			if(month.length()==1){
				serialNumber = Integer.parseInt(year+"0"+month);
			}else{
				serialNumber = Integer.parseInt(year+month);
			}
			List<RangeIssueForm> list = integralManagerService.getAvailableRangeIntegral(serialNumber);
			if(list!=null &&list.size()>0){
				for (RangeIssueForm rif : list){
					//获取，更新information表
					Information info = integralManagerService.getInformationById(rif.getUserId());
					info.setRepeatedMoney(info.getRepeatedMoney().add(rif.getAvailableInt()));
					info.setRepeatedAccumulative(info.getRepeatedAccumulative().add(rif.getAvailableInt()));
					info.setShoppingMoney(info.getShoppingMoney().add(rif.getAvailableInt()));
					info.setShoppingAccumulative(info.getShoppingAccumulative().add(rif.getAvailableInt()));
					
					//更新在AccountDetails表中添加一条纪律
					List<AccountDetails> member = integralManagerService.getMemberBycountNumberAndUserNumber(CommonUtil.getServerCountNumber(), info.getNumber());
					if (member==null){
						AccountDetails accountDetail = new AccountDetails();
						accountDetail.setKindData(KindDataEnum.points);
						/**日期类别统计 */
						accountDetail.setDateNumber(CommonUtil.getDateNumber());
						/**流水号 */
						accountDetail.setCountNumber(CommonUtil.getCountNumber());
						/**项目 */
						accountDetail.setProject(ProjectEnum.servicepointsforone);
						/**积分余额 */
						accountDetail.setPointbalance(info.getRepeatedMoney());
						/**葛粮币余额 */
						accountDetail.setGoldmoneybalance(info.getCrmMoney());
						/**收入 */
						accountDetail.setIncome(rif.getAvailableInt());
						/**支出 */
						accountDetail.setPay(new BigDecimal(0));
						/**备注 */
						accountDetail.setRedmin("极差积分发放");
						/**用户ID */
						accountDetail.setUserId(info.getId());
						/**用户登录ID */
						accountDetail.setUserNumber(info.getNumber());
						accountDetail.setCreateTime(new Date());
						
						//更新RepeatedMoneyStatistics表
						
						integralManagerService.saveOrUpdateRelation(info, accountDetail, rif.getUserId(), serialNumber);
					}else{
						AccountDetails memberInfo = member.get(0);
						/**积分余额 */
						memberInfo.setPointbalance(info.getRepeatedMoney());
						/**葛粮币余额 */
						memberInfo.setGoldmoneybalance(info.getCrmMoney());
						/**收入 */
						memberInfo.setIncome(memberInfo.getIncome().add(rif.getAvailableInt()));
						integralManagerService.saveOrUpdateRelation(info, memberInfo, rif.getUserId(), serialNumber);
					}
				}
			}
			return "back/integralManager/rangeIssue";
		} catch (Exception e) {
			e.printStackTrace();
			return "back/integralManager/rangeIssue";
		}
	}
	
}
