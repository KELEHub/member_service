package com.member.services.back;

import java.util.List;

import com.member.form.back.RoleMenuForm;

public interface RoleMenuManageService {
	public List<Integer> getMenuIdsByRole(Integer roleId);
	public void saveRoleMenu(RoleMenuForm form);
}
