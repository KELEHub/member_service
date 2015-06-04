package com.member.controller.back;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.member.entity.ManageRole;
import com.member.form.back.RoleForm;
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
	public void insertRole(RoleForm form){
		roleManageService.insertRole(form);
	}
	
}
