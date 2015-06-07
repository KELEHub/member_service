package com.member.controller.back;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.dao.HqlUserRole;
import com.member.dao.NmsUserDao;
import com.member.entity.ManageRole;
import com.member.entity.ManageUserRoleHub;
import com.member.entity.NmsUser;
import com.member.form.back.CreateUserForm;
import com.member.helper.BaseResult;
import com.member.services.back.LimitDeclarationService;
import com.member.services.back.NmUserService;
import com.member.services.back.ParameterService;
import com.member.services.back.RoleManageService;

@Controller
@RequestMapping(value = "/CreateUserController")
public class CreateUserController {

	@Resource(name = "ParameterServiceImpl")
	public ParameterService parameterService;

	@Resource(name = "RoleManageServiceImpl")
	private RoleManageService roleManageService;

	@Resource(name = "NmUserServiceImpl")
	private NmUserService nmUserService;

	@Resource(name = "LimitDeclarationServiceImpl")
	private LimitDeclarationService limitDeclarationService;

	@Resource(name = "NmsUserDaoImpl")
	private NmsUserDao nmsUserDao;

	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public String show(Model model) {
		try {
			// 角色获取
			List<ManageRole> roleList = roleManageService.getRoleList();
			// 获取用户
			List<NmsUser> NmsUserList = nmUserService.getNmsUserAll();
			//
			List<CreateUserForm> result = new ArrayList<CreateUserForm>();
			if (roleList != null && roleList.size() > 0) {
				model.addAttribute("roleList", roleList);
			}

			if (NmsUserList != null && NmsUserList.size() > 0) {
				for (NmsUser user : NmsUserList) {
					CreateUserForm form = new CreateUserForm();
					form.setUsername(user.getUserName());
					ManageRole role = roleManageService.getRoleById(user
							.getId());
					form.setRoleNm(role.getRoleNm());
					form.setRoleDes(role.getRoleDes());
					result.add(form);
				}
				model.addAttribute("result", result);
			}

			return "back/systemset/createUser";

		} catch (Exception e) {
			e.printStackTrace();
			return "back/systemset/createUser";
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/set", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> set(@RequestBody CreateUserForm form, Model model) {
		BaseResult<Void> result = new BaseResult<Void>();
		try {

			if (form.getUsername() == null || form.getPassword() == null
					|| "".equals(form.getUsername())
					|| "".equals(form.getPassword())) {
				result.setMsg("用户名或密码不能为空");
				result.setSuccess(true);
				return result;
			}
			List users = nmsUserDao.queryByHql(HqlUserRole.getUserByName, form
					.getUsername());
			if (users != null && users.size() > 0) {
				result.setMsg("添加失败,用户名存在");
				result.setSuccess(true);
				return result;
			}
			NmsUser nmsUser = new NmsUser();
			nmsUser.setUserName(form.getUsername());
			nmsUser.setUserPassword(form.getPassword());
			nmsUser.setCreateTime(new Date());
			limitDeclarationService.saveOrUpdate(nmsUser);

			ManageUserRoleHub mu = new ManageUserRoleHub();
			mu.setRoleId(Integer.valueOf(form.getId()));
			mu.setUserId(nmsUser.getId());
			limitDeclarationService.saveOrUpdate(mu);
			result.setMsg("创建用户成功");
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("创建用户失败，请重新尝试");
			result.setSuccess(true);
			return result;
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> delete(@RequestBody CreateUserForm form, Model model) {
		BaseResult<Void> result = new BaseResult<Void>();
		try {

			List<NmsUser> users = (List<NmsUser>) nmsUserDao.queryByHql(
					HqlUserRole.getUserByName, form.getUsername());
			if (users != null && users.size() > 0) {
				NmsUser deUser = users.get(0);
				ManageUserRoleHub mb = roleManageService
						.getManageUserRoleHubById(deUser.getId());
				limitDeclarationService.deleteData(mb);
				limitDeclarationService.deleteData(deUser);
			}
			result.setMsg("删除用户成功");
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("删除用户失败，请重新尝试");
			result.setSuccess(true);
			return result;
		}

	}

}
