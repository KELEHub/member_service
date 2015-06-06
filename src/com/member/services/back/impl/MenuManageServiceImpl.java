package com.member.services.back.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.member.dao.ManageMenuDao;
import com.member.entity.ManageMenu;
import com.member.services.back.MenuManageService;

@Service("MenuManageServiceImpl")
public class MenuManageServiceImpl implements MenuManageService {

	@Resource(name = "ManageMenuDaoImpl")
	private ManageMenuDao manageMenuDao;
	
	public List<ManageMenu> getAllMenu(){
		String hql = "from ManageMenu";
		
		List result = manageMenuDao.queryByHql(hql);
		
		return result;
	}
}
