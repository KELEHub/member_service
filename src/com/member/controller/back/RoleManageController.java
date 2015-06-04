package com.member.controller.back;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.ManageRole;
import com.member.form.back.RoleForm;
import com.member.helper.BaseResult;
import com.member.services.back.RoleManageService;

@Controller
@RequestMapping(value = "/rolemanage")
public class RoleManageController {

	@Resource(name = "RoleManageServiceImpl")
	private RoleManageService roleManageService;
	@RequestMapping(value = "/showRoleManage",method = RequestMethod.POST)
	public String showRoleManage(Model model){
		List<ManageRole> result = roleManageService.getRoleList();
		model.addAttribute("result", result);
		return "back/membermanage/roleManage";
	}
	
	@RequestMapping(value = "/insertRole",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> insertRole(@RequestBody RoleForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		roleManageService.insertRole(form);
		result.setMsg("添加角色成功.");
		result.setSuccess(true);
		return result;
	}
	
}
