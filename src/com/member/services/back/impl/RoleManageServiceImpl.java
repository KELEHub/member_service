package com.member.services.back.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.ManageRoleDao;
import com.member.entity.ManageRole;
import com.member.entity.ManageUserRoleHub;
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

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<ManageRole> getRoleList() {
		String hql="select mr from ManageRole mr";
		List result = manageRoleDao.queryByHql(hql);
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<ManageRole> getRoleByItemID(Integer id) {
		String hql="select mr from ManageRole mr where mr.id=? ";
		List arguments = new ArrayList();
		arguments.add(id);
		List result = manageRoleDao.queryByHql(hql,arguments);
		return result;
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public ManageRole getRoleById(Integer userId) {
		String hql="select mr from ManageRole mr ,ManageUserRoleHub t where mr.id=t.roleId and t.userId=?";
		List arguments = new ArrayList();
		arguments.add(userId);
	   List<ManageRole> result = (List<ManageRole>)manageRoleDao.queryByHql(hql,arguments);
	   if (result != null && result.size() > 0) {
			return  result.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public ManageUserRoleHub getManageUserRoleHubById(Integer userId) {
		String hql="from ManageUserRoleHub t where t.userId=?";
		List arguments = new ArrayList();
		arguments.add(userId);
	   List<ManageUserRoleHub> result = (List<ManageUserRoleHub>)manageRoleDao.queryByHql(hql,arguments);
	   if (result != null && result.size() > 0) {
			return  result.get(0);
		} else {
			return null;
		}
	}

	@Override
	public boolean editeRole(RoleForm form) {
		String hql="select mr from ManageRole mr where mr.id=? ";
		List arguments = new ArrayList();
		arguments.add(form.getId());
		List result = manageRoleDao.queryByHql(hql,arguments);
		if(result!=null && result.size()>0){
			ManageRole role = (ManageRole)result.get(0);
			role.setRoleDes(form.getRoleDsc());
			role.setRoleNm(form.getRoleNm());
			manageRoleDao.saveOrUpdate(role);
			return true;
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean deleteData(ManageRole role) {
		try {
			String hql="select mr from ManageUserRoleHub mr where mr.roleId=? ";
			List arguments = new ArrayList();
			arguments.add(role.getId());
			List result = manageRoleDao.queryByHql(hql,arguments);
			if(result!=null && result.size()>0){
				List<ManageUserRoleHub> roleHubList=(List<ManageUserRoleHub>)result;
				for(ManageUserRoleHub hub : roleHubList){
					manageRoleDao.delete(hub);
				}
			}
			manageRoleDao.delete(role);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
