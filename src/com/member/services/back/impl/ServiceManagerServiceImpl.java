package com.member.services.back.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.HqlServiceManager;
import com.member.dao.ServiceManagerDao;
import com.member.entity.ApplyService;
import com.member.entity.Information;
import com.member.form.back.InformationForm;
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
	@Transactional(readOnly=true)
	public Information getServiceById(Integer id) {
		List<Information> result = (List<Information>) serviceManagerDao.queryByHql(
				HqlServiceManager.getServiceById, id);
		if(result!=null){
			return (Information) result.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<ApplyService> getApplyService() {
		return (List<ApplyService>) serviceManagerDao.queryByHql(
				HqlServiceManager.getApproveService);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateInfo(InformationForm msf) {
		String hql = "update Information set number=?,crmMoney=?,crmAccumulative=?,shoppingMoney=?,shoppingAccumulative=? where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(msf.getNumber());
		list.add(msf.getCrmMoney());
		list.add(msf.getCrmAccumulative());
		list.add(msf.getShoppingMoney());
		list.add(msf.getShoppingAccumulative());
		list.add(msf.getId());
		serviceManagerDao.executeHqlUpdate(hql, list);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void setTickling(Information information) {
		serviceManagerDao.saveOrUpdate(information);
	}
}
