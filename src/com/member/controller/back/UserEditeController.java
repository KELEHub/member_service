package com.member.controller.back;

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

import com.member.dao.HqlUserRole;
import com.member.dao.NmsUserDao;
import com.member.entity.EditeHistory;
import com.member.entity.Information;
import com.member.entity.LimitDeclaration;
import com.member.entity.ManageRole;
import com.member.entity.NmsUser;
import com.member.form.back.DeclarationForm;
import com.member.form.back.EditeHistoryForm;
import com.member.form.back.UserEditeForm;
import com.member.helper.BaseResult;
import com.member.services.back.InstitutionService;
import com.member.services.back.RoleManageService;

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
			if (info.getIsActivate() == 0) {
				form.setError("该用户未激活");
			}
			UserEditeForm uform = new UserEditeForm();
			if (info != null) {
				uform.setNumber(form.getNumber());
				uform.setUserNumber(form.getNumber());
				uform.setCrmAccumulative(String.valueOf(info
						.getCrmAccumulative()));
				uform.setCrmMoney(String.valueOf(info.getCrmMoney()));
				uform.setShoppingMoney(String.valueOf(info.getShoppingMoney()));
				uform.setShoppingAccumulative(String.valueOf(info
						.getShoppingAccumulative()));
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
			if(form.getUserNumber()==null){
				result.setMsg("请先查询出结果再修改");
				result.setSuccess(true);
				return result;
			}
			
			Information info =institutionService.getNnmuserByName(form.getUserNumber());
			String remaind = "修改前的葛粮币："+info.getCrmMoney()+","+"葛粮币累计："+info.getCrmAccumulative()+","+"积分:"+info.getShoppingMoney()+","+"积分累计"+info.getShoppingAccumulative()+";"+"修改后的葛粮币："+form.getCrmMoney()+","+"葛粮币累计："+form.getCrmAccumulative()+";"+"积分："+form.getShoppingMoney()+","+"积分累计："+form.getShoppingAccumulative()+";";
			info.setShoppingAccumulative(Long.valueOf(form.getShoppingAccumulative()));
			info.setShoppingMoney(Long.valueOf(form.getShoppingMoney()));
			info.setCrmMoney(Long.valueOf(form.getCrmMoney()));
			info.setCrmAccumulative(Long.valueOf(form.getCrmAccumulative()));
			institutionService.savaOrUpdate(info);
			//操作记录
			Object logonUserO = sesison.getAttribute("logonUser");
			Map<String,Object> logonUserMap = (	Map<String,Object>) logonUserO;
			String userNaemO =(String) logonUserMap.get("userName");
			List<NmsUser> users = (List<NmsUser>)nmsUserDao.queryByHql(
					HqlUserRole.getUserByName, userNaemO);
			NmsUser user = users.get(0);
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
