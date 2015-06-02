package com.member.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.member.dao.HqlUserRole;
import com.member.dao.NmsUserDao;
import com.member.helper.dao.impl.BaseDaoImpl;

@Component("NmsUserDaoImpl")
 public class NmsUserDaoImpl extends BaseDaoImpl implements NmsUserDao {

	@SuppressWarnings("unchecked")
	public List<String> getUserDataPermissonCode(Integer userId) {
		
		List<String> dataPermissionCodes=(List<String>) queryByHql(HqlUserRole.getUserDataPermission,userId);

		return dataPermissionCodes;
	}

}
