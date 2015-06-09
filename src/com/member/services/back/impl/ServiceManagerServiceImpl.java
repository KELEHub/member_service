package com.member.services.back.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.HqlServiceManager;
import com.member.dao.ServiceManagerDao;
import com.member.entity.Information;
import com.member.services.back.ServiceManagerService;

@Service("ServiceManagerServiceImpl")
public class ServiceManagerServiceImpl implements ServiceManagerService{

	@Resource(name = "ServiceManagerDaoImpl")
    ServiceManagerDao serviceManagerDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Information> getServiceByIsService(Integer isService) {
		return (List<Information>) serviceManagerDao.queryByHql(
				HqlServiceManager.getService, isService);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void setTickling(Information information) {
		serviceManagerDao.saveOrUpdate(information);
	}
}
