package com.member.services.back.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.ManageRoleDao;
import com.member.entity.ManageRole;
import com.member.form.back.RoleForm;
import com.member.services.back.RoleManageService;

@Service("RoleManageServiceImpl")
@Transactional
public class RoleManageServiceImpl implements RoleManageService {
	
	@Resource(name = "ManageRoleDaoImpl")
	private ManageRoleDao manageRoleDao;
	
	public void insertRole(RoleForm form){
		ManageRole role = new ManageRole();
		role.setRoleDes(form.getRoleDsc());
		role.setRoleNm(form.getRoleNm());
		manageRoleDao.save(role);
	}

	@Override
	public List<ManageRole> getRoleList() {
		String hql="select mr from ManageRole mr";
		List result = manageRoleDao.queryByHql(hql);
		return result;
	}
}
