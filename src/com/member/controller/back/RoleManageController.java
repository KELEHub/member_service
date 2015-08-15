package com.member.controller.back;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.beans.back.enumData.GiftEnum;
import com.member.entity.GiftsDetails;
import com.member.entity.Information;
import com.member.entity.ManageMenu;
import com.member.entity.ManageRole;
import com.member.form.back.GiftsForm;
import com.member.form.back.RoleForm;
import com.member.form.back.RoleMenuForm;
import com.member.helper.BaseResult;
import com.member.services.back.MenuManageService;
import com.member.services.back.NmUserService;
import com.member.services.back.RoleManageService;
import com.member.services.back.RoleMenuManageService;

@Controller
@RequestMapping(value = "/rolemanage")
public class RoleManageController {

	@Resource(name = "RoleManageServiceImpl")
	private RoleManageService roleManageService;
	
	@Resource(name = "MenuManageServiceImpl")
	private MenuManageService menuManageService;
	
	@Resource(name = "NmUserServiceImpl")
	private NmUserService nmUserService;
	
	@Resource(name = "RoleMenuManageServiceImpl")
	private RoleMenuManageService roleMenuManageService;
	
	@RequestMapping(value = "/showRoleManage",method = RequestMethod.POST)
	public String showRoleManage(Model model){
		List<ManageRole> result = roleManageService.getRoleList();
		model.addAttribute("result", result);
		return "back/systemset/roleManage";
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
	
	@RequestMapping(value = "/showAllocationAuth",method = RequestMethod.POST)
	public String allocationAuth(@RequestBody RoleForm form,HttpServletRequest request,Model model){
//		List<ManageMenu> menuList = menuManageService.getAllMenu();
		model.addAttribute("form", form);
//		model.addAttribute("menuList", menuList);
		//取得当前用户的权限菜单
//		Object logonUserO = request.getAttribute("logonUser");
//		Map<String,Object> logonUserMap = (Map<String,Object>) logonUserO;
//		Object useid = logonUserMap.get("id");
		List<Integer> result = roleMenuManageService.getMenuIdsByRole(form.getId());
		//取得当前角色对应的菜单信息.
		model.addAttribute("result", result.toString());

		return "back/systemset/showAllocationAuth";
	}
	
	@RequestMapping(value = "/getMenuInfo",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<List<ManageMenu>> getMenuInfo(@RequestBody RoleForm form,Model model){
		List<ManageMenu> menuList = menuManageService.getAllMenu();
		BaseResult<List<ManageMenu>> result = new BaseResult<List<ManageMenu>>();
		result.setMsg("添加角色成功.");
		result.setSuccess(true);
		result.setResult(menuList);
		return result;
	}
	
	@RequestMapping(value = "/saveAllocationAuth",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> saveAllocationAuth(@RequestBody RoleMenuForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		roleMenuManageService.saveRoleMenu(form);
		result.setMsg("分配权限成功.");
		result.setSuccess(true);
		return result;
	}
	
	
	@RequestMapping(value = "/showDialog", method = RequestMethod.POST)
	public String showDialog(@RequestBody RoleForm form, Model model) {
		List<ManageRole> list = roleManageService.getRoleByItemID(form.getId());
		if(list!=null && list.size()>0){
			RoleForm bean = new RoleForm();
			bean.setId(list.get(0).getId());
			bean.setRoleDsc(list.get(0).getRoleDes());
			bean.setRoleNm(list.get(0).getRoleNm());
			model.addAttribute("bean",bean);
		}
		return "back/systemset/roleeidte";
	}
	
	@RequestMapping(value = "/editeContent",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> editeContent(@RequestBody RoleForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		boolean flg = roleManageService.editeRole(form);
		if(flg){
			result.setMsg("修改角色成功.");
		}else{
			result.setMsg("修改角色失败.");
		}
		result.setSuccess(true);
		return result;
	}
	
}
