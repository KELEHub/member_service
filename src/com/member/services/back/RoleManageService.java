package com.member.services.back;

import java.util.List;

import com.member.entity.ManageRole;
import com.member.entity.ManageUserRoleHub;
import com.member.form.back.RoleForm;

public interface RoleManageService {
	public void insertRole(RoleForm form);
	public List<ManageRole> getRoleList();
	public ManageRole getRoleById(Integer userId);
	public ManageUserRoleHub getManageUserRoleHubById(Integer userId);
	public List<ManageRole> getRoleByItemID(Integer id);
	public boolean editeRole(RoleForm form);
}
