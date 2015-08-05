package com.member.services.back.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.HqlServiceManager;
import com.member.dao.ServiceManagerDao;
import com.member.entity.ApplyService;
import com.member.entity.ForbidForm;
import com.member.entity.Information;
import com.member.form.back.InformationForm;
import com.member.form.back.MemberSearchForm;
import com.member.services.back.ServiceManagerService;

@SuppressWarnings("unchecked")
@Service("ServiceManagerServiceImpl")
public class ServiceManagerServiceImpl implements ServiceManagerService{

	@Resource(name = "ServiceManagerDaoImpl")
    ServiceManagerDao serviceManagerDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Information> getServiceByIsService(Integer isService,String customerPar,
			int pageSize,int pageNumber) {
		return (List<Information>) serviceManagerDao.queryByHql(
				HqlServiceManager.getService,pageNumber,pageSize, isService);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Information getServiceByNumber(String number) {
		List<Information> result = (List<Information>) serviceManagerDao.queryByHql(
				HqlServiceManager.getServiceByNumber, number);
		if(result!=null){
			return (Information) result.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public ForbidForm getForbidForm() {
		List<ForbidForm> result = (List<ForbidForm>) serviceManagerDao.queryByHql(
				HqlServiceManager.getForbidForm);
		if(result!=null && result.size()>0){
			return (ForbidForm) result.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<ApplyService> getApplyService(String customerPar,
			int pageSize,int pageNumber) {
		return (List<ApplyService>) serviceManagerDao.queryByHql(
				HqlServiceManager.getApproveService,pageNumber,pageSize);
	}
	
	@Override
	public int countApproveServiceData(String customerPar) {
		List result = serviceManagerDao.queryByHql(HqlServiceManager.getApproveService);
		if(result!=null){
			return result.size();
		}
		return 0;
	}
	
	@Override
	public int countServiceManagerData(String customerPar,Integer isService) {
		List result = serviceManagerDao.queryByHql(HqlServiceManager.getService,isService);
		if(result!=null){
			return result.size();
		}
		return 0;
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
	public void forbiddenService(Integer isService,Integer id) {
		String hql = "update Information set isService=? where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(isService);
		list.add(id);
		serviceManagerDao.executeHqlUpdate(hql, list);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateApplyState(Integer state,Integer id,String failureCause) {
		String hql = "update ApplyService set state=?,failureCause=? where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(state);
		list.add(failureCause);
		list.add(id);
		serviceManagerDao.executeHqlUpdate(hql, list);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateForbidForm(Integer ifForbid) {
		String hql = "update ForbidForm set ifForbid=?";
		List<Object> list = new ArrayList<Object>();
		list.add(ifForbid);
		serviceManagerDao.executeHqlUpdate(hql, list);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOrUpdate(Object obj) {
		serviceManagerDao.saveOrUpdate(obj);
	}

	@Override
	public Information getServiceById(Integer id) {
		List<Information> result = (List<Information>) serviceManagerDao.queryByHql(
				HqlServiceManager.getServiceById, id);
		if(result!=null){
			return (Information) result.get(0);
		}else{
			return null;
		}
	}
}
