package com.member.services.back.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.member.beans.back.MenuBean;
import com.member.dao.NmsUserDao;
import com.member.services.back.NmUserService;

@Service("NmUserServiceImpl")
@Transactional
public class NmUserServiceImpl implements NmUserService{
	
	@Resource(name = "NmsUserDaoImpl")
	private NmsUserDao nmsUserDao;
	
	@Override
	public List<MenuBean> getMenuByUserId(Integer userId) {
		return nmsUserDao.getMenuByUserId(userId);
	}
	

}
