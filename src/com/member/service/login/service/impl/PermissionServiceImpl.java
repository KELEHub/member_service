package com.member.service.login.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.service.login.HqlUserRole;
import com.member.service.login.dao.NmsUserDao;
import com.member.service.login.po.NmsRolePermission;
import com.member.service.login.po.NmsUser;
import com.member.service.login.service.PermissionService;

/**
 * 角色服务类
 * 
 * @author zj
 * 
 */
@SuppressWarnings("unchecked")
@Service("PermissionServiceImpl")
public class PermissionServiceImpl implements PermissionService {

	@Resource(name = "NmsUserDaoImpl")
    NmsUserDao nmsUserDao;
	
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<NmsRolePermission> getPermissionsByRoleId(Integer roleId) {
		return (List<NmsRolePermission>) nmsUserDao.queryByHql(
				HqlUserRole.getPermissionByRoleId, roleId);
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<NmsRolePermission> getPermissionsByUserName(String userName) {
		List users = nmsUserDao.queryByHql(HqlUserRole.getUserByName, userName);
		if (users == null || users.size() == 0) {
			return null;
		}

		NmsUser user = (NmsUser) users.get(0);
		return getPermissionsByRoleId(user.getRoleId());
	}
	
	
	


}