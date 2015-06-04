package com.member.services.back;

import java.util.List;

import com.member.entity.ManageRole;
import com.member.form.back.RoleForm;

public interface RoleManageService {
	public void insertRole(RoleForm form);
	public List<ManageRole> getRoleList();
}
