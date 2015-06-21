package com.member.services.front.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.ApplyQualificationDao;
import com.member.dao.HqlServiceManager;
import com.member.entity.ApplyService;
import com.member.services.front.ApplyQualificationService;

@SuppressWarnings("unchecked")
@Service("ApplyQualificationServiceImpl")
public class ApplyQualificationServiceImpl implements ApplyQualificationService {

	@Resource(name = "ApplyQualificationDaoImpl")
    private ApplyQualificationDao applyQualificationDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<ApplyService> getApplyQualification(String submitNumber) {
		return (List<ApplyService>) applyQualificationDao.queryByHql(
				HqlServiceManager.getApplyQualification,submitNumber);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteApplyQualification(Integer id) {
		String hql = "delete from ApplyService where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(id);
		applyQualificationDao.executeHqlUpdate(hql, list);
	}
}
