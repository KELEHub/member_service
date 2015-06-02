package com.member.service.managementService.login.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.member.service.frame.database.dao.impl.BaseDaoImpl;
import com.member.service.login.HqlUserRole;
import com.member.service.login.dao.NmsUserDao;

@Component("NmsUserDaoImpl")
 public class NmsUserDaoImpl extends BaseDaoImpl implements NmsUserDao {

	@SuppressWarnings("unchecked")
	public List<String> getUserDataPermissonCode(Integer userId) {
		
		List<String> dataPermissionCodes=(List<String>) queryByHql(HqlUserRole.getUserDataPermission,userId);

		return dataPermissionCodes;
	}

}
