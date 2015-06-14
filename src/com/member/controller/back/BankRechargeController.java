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
import com.member.entity.BankRechargeHistory;
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
@RequestMapping(value = "/BankRechargeController")
public class BankRechargeController {

	@Resource(name = "InstitutionServiceImpl")
	public InstitutionService institutionService;

	@Resource(name = "NmsUserDaoImpl")
	private NmsUserDao nmsUserDao;

	@Resource(name = "RoleManageServiceImpl")
	private RoleManageService roleManageService;

	@RequestMapping(value = "/show")
	public String show(Model model) {

		return "back/systeminfo/bankRecharge";

	}

	@RequestMapping(value = "/serch", method = RequestMethod.POST)
	public String serch(@RequestBody UserEditeForm form, Model model) {
		try {
			// List<NmsUser> users = (List<NmsUser>)nmsUserDao.queryByHql(
			// HqlUserRole.getUserByName, userNaemO);

			Information info = institutionService.getNnmuserByName(form
					.getNumber());
			if (info == null) {
				form.setError("该用户不存在");
				model.addAttribute("bean", form);
				return "back/systeminfo/bankRecharge";
			}
			if (info.getIsActivate() == 0) {
				form.setError("该用户未激活");
				model.addAttribute("bean", form);
				return "back/systeminfo/bankRecharge";
			}
			UserEditeForm uform = new UserEditeForm();
			if (info != null) {
				uform.setNumber(info.getNumber());
				uform.setUserNumber(info.getNumber());
				uform.setUserName(info.getName());
				// if(info.getCrmMoney()!=null&&!"".equals(info.getCrmMoney())){
				// uform.setCrmMoney(CommonUtil.insertComma(info.getCrmMoney().toString(),2));
				// }
				model.addAttribute("bean", uform);
			}

			return "back/systeminfo/bankRecharge";
		} catch (Exception e) {
			e.printStackTrace();
			return "back/systeminfo/bankRecharge";
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/set", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> set(@RequestBody UserEditeForm form, Model model,
			HttpSession sesison) {
		BaseResult<Void> result = new BaseResult<Void>();
		try {
			if (form.getUserNumber() == null || "".equals(form.getUserNumber())) {
				result.setMsg("请先查询出结果再修改");
				result.setSuccess(true);
				return result;
			}
			Object logonUserO = sesison.getAttribute("logonUser");
			Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
			String userNaemO = (String) logonUserMap.get("userName");
			List<NmsUser> users = (List<NmsUser>) nmsUserDao.queryByHql(
					HqlUserRole.getUserByName, userNaemO);
			NmsUser user = users.get(0);
			Information info = institutionService.getNnmuserByName(form
					.getUserNumber());
			String remaind = "为后台充值葛粮币金额：" + form.getCrmMoney();
			BigDecimal addMoney = info.getCrmMoney().add(
					getValue(form.getCrmMoney().replace(",", "")));
			AccountDetails shopingDetails = new AccountDetails();
			shopingDetails.setUserNumber(form.getUserNumber());
			shopingDetails.setCreateTime(new Date());
			shopingDetails.setKindData(KindDataEnum.goldmoney);

			/** 日期类别统计 */
			shopingDetails.setDateNumber(CommonUtil.getDateNumber());
			shopingDetails.setProject(ProjectEnum.recharge);
			/** 积分余额 */
			shopingDetails.setPointbalance(info.getShoppingMoney());
			/** 葛粮币余额 */
			shopingDetails.setGoldmoneybalance(addMoney);
			/** 收入 */
			shopingDetails.setIncome(getValue(form.getCrmMoney().replace(",",
					"")));
			/** 支出 */
			shopingDetails.setPay(new BigDecimal(0));

			/** 备注 */
			shopingDetails.setRedmin("充值");
			/** 用户ID */
			shopingDetails.setUserId(info.getId());
			institutionService.savaOrUpdate(shopingDetails);

			info.setCrmMoney(addMoney);
			institutionService.savaOrUpdate(info);
			// 操作记录
			BankRechargeHistory eh = new BankRechargeHistory();
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
	@RequestMapping(value = "/bankRechargeHistory", method = RequestMethod.POST)
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
			List<BankRechargeHistory> list = new ArrayList<BankRechargeHistory>();
			List<EditeHistoryForm> formList = new ArrayList<EditeHistoryForm>();
			if (role.getSuperAdmin() != null && role.getSuperAdmin() == 1) {
				list = institutionService.getBankRechargeHistoryList();
			} else {
				list = institutionService.getBankRechargeHistoryListByUserId(user
						.getId());
			}
			if (list!= null && list.size() > 0) {
				for (BankRechargeHistory eh : list) {
					List<NmsUser> oprations = (List<NmsUser>) nmsUserDao.queryByHql(
							HqlUserRole.getUserById, eh.getUserId());
					NmsUser opration = oprations.get(0);
					EditeHistoryForm form = new EditeHistoryForm();
					form.setCreateDate(String.valueOf(eh.getCreateTime()));
					form.setNumeber(eh.getNumeber());
					form.setOprationMan(opration.getUserName());
					form.setRemaind(eh.getRemaind());
					formList.add(form);
				}
			}
			model.addAttribute("result", formList);

		}
		return "back/systeminfo/bankRechargeHistory";

	}

	private BigDecimal getValue(String s) {
		BigDecimal b = new BigDecimal(s);
		return b.setScale(2, BigDecimal.ROUND_DOWN);
	}

}
