package com.member.services.back.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.member.beans.back.MenuBean;
import com.member.dao.HqlUserRole;
import com.member.dao.NmsUserDao;
import com.member.entity.NmsUser;
import com.member.services.back.NmUserService;

@Service("NmUserServiceImpl")
@Transactional
public class NmUserServiceImpl implements NmUserService{
	
	@Resource(name = "NmsUserDaoImpl")
	private NmsUserDao nmsUserDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<MenuBean> getMenuByUserId(Integer userId) {
		return nmsUserDao.getMenuByUserId(userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<NmsUser> getNmsUserAll() {
		List<NmsUser> list = (List<NmsUser>)nmsUserDao.queryByHql(HqlUserRole.getNmsUserAll);
		return list;
	}
	

}
