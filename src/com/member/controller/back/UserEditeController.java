package com.member.controller.back;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.beans.back.enumData.KindDataEnum;
import com.member.beans.back.enumData.ProjectEnum;
import com.member.dao.HqlUserRole;
import com.member.dao.NmsUserDao;
import com.member.entity.AccountDetails;
import com.member.entity.EditeHistory;
import com.member.entity.Information;
import com.member.entity.ManageRole;
import com.member.entity.NmsUser;
import com.member.form.back.EditeHistoryForm;
import com.member.form.back.UserEditeForm;
import com.member.helper.BaseResult;
import com.member.services.back.InstitutionService;
import com.member.services.back.RoleManageService;
import com.member.util.CommonUtil;

@Controller
@RequestMapping(value = "/userEditeController")
public class UserEditeController {

	@Resource(name = "NmsUserDaoImpl")
	private NmsUserDao nmsUserDao;

	@Resource(name = "RoleManageServiceImpl")
	private RoleManageService roleManageService;

	@Resource(name = "InstitutionServiceImpl")
	private InstitutionService institutionService;

	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public String show(Model model) {
		return "back/systemset/userEdite";
	}

	@RequestMapping(value = "/serch", method = RequestMethod.POST)
	public String serch(@RequestBody UserEditeForm form, Model model) {
		try {
			// List<NmsUser> users = (List<NmsUser>)nmsUserDao.queryByHql(
			// HqlUserRole.getUserByName, userNaemO);

			Information info = institutionService.getNnmuserByName(form
					.getNumber());
			if(info == null){
				form.setError("该用户不存在");
				model.addAttribute("bean", form);
				return "back/systemset/userEdite";
			}
			if (info.getIsActivate() == 0) {
				form.setError("该用户未激活");
				model.addAttribute("bean", form);
				return "back/systemset/userEdite";
			}
			UserEditeForm uform = new UserEditeForm();
			if (info != null) {
				uform.setNumber(form.getNumber());
				uform.setUserNumber(form.getNumber());
				uform.setCrmAccumulative(CommonUtil.insertComma(info
						.getCrmAccumulative().toString(),2));
				uform.setCrmMoney(CommonUtil.insertComma(info.getCrmMoney().toString(),2));
				uform.setShoppingMoney(CommonUtil.insertComma(info.getShoppingMoney().toString(),2));
				uform.setShoppingAccumulative(CommonUtil.insertComma(info
						.getShoppingAccumulative().toString(),2));
				model.addAttribute("bean", uform);
			}

			return "back/systemset/userEdite";
		} catch (Exception e) {
			e.printStackTrace();
			return "back/systemset/userEdite";
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/set", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> set(@RequestBody UserEditeForm form, Model model,HttpSession sesison) {
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			if(form.getUserNumber()==null || "".equals(form.getUserNumber())){
				result.setMsg("请先查询出结果再修改");
				result.setSuccess(true);
				return result;
			}
			Object logonUserO = sesison.getAttribute("logonUser");
			Map<String,Object> logonUserMap = (	Map<String,Object>) logonUserO;
			String userNaemO =(String) logonUserMap.get("userName");
			List<NmsUser> users = (List<NmsUser>)nmsUserDao.queryByHql(
					HqlUserRole.getUserByName, userNaemO);
			NmsUser user = users.get(0);
			Information info =institutionService.getNnmuserByName(form.getUserNumber());
			String remaind = "修改前的葛粮币："+info.getCrmMoney()+","+"葛粮币累计："+info.getCrmAccumulative()+","+"积分:"+info.getShoppingMoney()+","+"积分累计"+info.getShoppingAccumulative()+";"+"修改后的葛粮币："+form.getCrmMoney()+","+"葛粮币累计："+form.getCrmAccumulative()+";"+"积分："+form.getShoppingMoney()+","+"积分累计："+form.getShoppingAccumulative()+";";
			BigDecimal shopingDif =  new BigDecimal(form.getShoppingMoney().replace(",", "")).subtract(info.getShoppingMoney()) ; 
			int r=shopingDif.compareTo(BigDecimal.ZERO);
			if(r!=0){
				AccountDetails shopingDetails = new AccountDetails();
				shopingDetails.setDateNumber(form.getUserNumber());
				shopingDetails.setCreateTime(new Date());
				shopingDetails.setKindData(KindDataEnum.points);
				
				/**日期类别统计 */
				shopingDetails.setDateNumber(CommonUtil.getDateNumber());
				shopingDetails.setProject(ProjectEnum.fromback);
				/**积分余额 */
				shopingDetails.setPointbalance(new BigDecimal(form.getShoppingMoney().replace(",", "")));
				/**葛粮币余额 */
				shopingDetails.setGoldmoneybalance(info.getCrmMoney());
				if(r==1){
					/**收入 */
					shopingDetails.setIncome(shopingDif);
					/**支出 */
					shopingDetails.setPay(new BigDecimal(0));
				}else if(r==-1){
					/**收入 */
					shopingDetails.setIncome(new BigDecimal(0));
					/**支出 */
					shopingDetails.setPay(shopingDif);
				}
				
				/**备注 */
				shopingDetails.setRedmin("后台调整");
				/**用户ID */
				shopingDetails.setUserId(info.getId());
				institutionService.savaOrUpdate(shopingDetails);
			}
			
			BigDecimal crmMoneyDif =  new BigDecimal(form.getCrmMoney().replace(",", "")).subtract(info.getCrmMoney()) ; 
			int cr=crmMoneyDif.compareTo(BigDecimal.ZERO);
			if(cr!=0){
				AccountDetails crmMoneyDetails = new AccountDetails();
				crmMoneyDetails.setDateNumber(form.getUserNumber());
				crmMoneyDetails.setCreateTime(new Date());
				crmMoneyDetails.setKindData(KindDataEnum.goldmoney);
				
				/**日期类别统计 */
				crmMoneyDetails.setDateNumber(CommonUtil.getDateNumber());
				crmMoneyDetails.setProject(ProjectEnum.fromback);
				/**积分余额 */
				crmMoneyDetails.setPointbalance(new BigDecimal(form.getShoppingMoney().replace(",", "")));
				/**葛粮币余额 */
				crmMoneyDetails.setGoldmoneybalance(new BigDecimal(form.getCrmMoney().replace(",", "")));
				if(cr==1){
					/**收入 */
					crmMoneyDetails.setIncome(crmMoneyDif);
					/**支出 */
					crmMoneyDetails.setPay(new BigDecimal(0));
				}else if(cr==-1){
					/**收入 */
					crmMoneyDetails.setIncome(new BigDecimal(0));
					/**支出 */
					crmMoneyDetails.setPay(crmMoneyDif);
				}
				
				/**备注 */
				crmMoneyDetails.setRedmin("后台调整");
				/**用户ID */
				crmMoneyDetails.setUserId(info.getId());
				institutionService.savaOrUpdate(crmMoneyDetails);
			}
			info.setShoppingAccumulative(new BigDecimal(form.getShoppingAccumulative().replace(",", "")));
			info.setShoppingMoney(new BigDecimal(form.getShoppingMoney().replace(",", "")));
			info.setCrmMoney(new BigDecimal(form.getCrmMoney().replace(",", "")));
			info.setCrmAccumulative(new BigDecimal(form.getCrmAccumulative().replace(",", "")));
			institutionService.savaOrUpdate(info);
			//操作记录
			EditeHistory eh =new EditeHistory();
			eh.setCreateTime(new Date());
			eh.setUserId(user.getId());
			eh.setNumeber(form.getUserNumber());
			eh.setRemaind(remaind);
			institutionService.savaOrUpdate(eh);
			
			result.setMsg("修改成功");
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("修改失败,数值设置不正确");
			result.setSuccess(true);
			return result;
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/editeHistory", method = RequestMethod.POST)
	public String editeHistory(Model model, HttpSession sesison) {

		Object logonUserO = sesison.getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		String userNaemO = (String) logonUserMap.get("userName");

		List<NmsUser> users = (List<NmsUser>) nmsUserDao.queryByHql(
				HqlUserRole.getUserByName, userNaemO);
		NmsUser user = new NmsUser();
		if (users != null && users.size() > 0) {
			user = users.get(0);
			ManageRole role = roleManageService.getRoleById(user.getId());
			List<EditeHistory> list = new ArrayList<EditeHistory>();
			List<EditeHistoryForm> formList = new ArrayList<EditeHistoryForm>();
			if (role.getSuperAdmin() != null && role.getSuperAdmin() == 1) {
				list = institutionService.getEditeHistoryList();
			} else {
				list = institutionService.getEditeHistoryListByUserId(user
						.getId());
			}
			if (list.size() > 0) {
				for (EditeHistory eh : list) {
					EditeHistoryForm form = new EditeHistoryForm();
					form.setCreateDate(String.valueOf(eh.getCreateTime()));
					form.setNumeber(eh.getNumeber());
					form.setOprationMan(user.getUserName());
					form.setRemaind(eh.getRemaind());
					formList.add(form);
				}
			}
			model.addAttribute("result", formList);

		}
		return "back/systemset/editeHistory";

	}

}
