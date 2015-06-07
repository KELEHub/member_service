package com.member.services.back.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.ManageRoleMenuHubDao;
import com.member.entity.ManageRoleMenuHub;
import com.member.form.back.RoleMenuForm;
import com.member.services.back.RoleMenuManageService;

@Service("RoleMenuManageServiceImpl")
public class RoleMenuManageServiceImpl implements
		RoleMenuManageService {
	

	@Resource(name = "ManageRoleMenuHubDaoImpl")
	private ManageRoleMenuHubDao manageRoleMenuHubDao;
	
	@Override
	public List<Integer> getMenuIdsByRole(Integer roleId) {
		String hql ="select mrmh.menuid from ManageRoleMenuHub mrmh where mrmh.roleId=?";
		List result = manageRoleMenuHubDao.queryByHql(hql,roleId);
		return result;
	}

	@Override
	@Transactional
	public void saveRoleMenu(RoleMenuForm form) {
		//删除原来的角色菜单配置。
		String deletehql = "delete ManageRoleMenuHub s where s.roleId=? ";
		manageRoleMenuHubDao.executeHqlUpdate(deletehql, form.getId());
		
		//插入新的角色菜单配置。
		List<Integer> menuIds = form.getMenuids();
		for(Integer menuId : menuIds){
			ManageRoleMenuHub insertManageRoleMenuHub = new ManageRoleMenuHub();
			insertManageRoleMenuHub.setMenuid(menuId);
			insertManageRoleMenuHub.setRoleId(form.getId());
			manageRoleMenuHubDao.save(insertManageRoleMenuHub);
		}
	}
}
