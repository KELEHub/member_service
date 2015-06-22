package com.member.services.back.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.InstitutionDao;
import com.member.entity.Information;
import com.member.services.back.InformationService;

@Service("InformationServiceImpl")
public class InformationServiceImpl implements InformationService{
	
	
	@Resource(name = "InstitutionDaoImpl")
    InstitutionDao institutionDao;
	

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Information getInformationById(Integer id) {
		String hql="from Information mr where mr.id=?";
		List arguments = new ArrayList();
		arguments.add(id);
		List<Information> result = (List<Information>)institutionDao.queryByHql(hql,arguments);
		if(result!=null && result.size()>0){
			return result.get(0);
		}
		return null;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Information getInformationByNumber(String number) {
		String hql="from Information mr where mr.number=?";
		List arguments = new ArrayList();
		arguments.add(number);
		List<Information> result = (List<Information>)institutionDao.queryByHql(hql,arguments);
		if(result!=null && result.size()>0){
			return result.get(0);
		}
		return null;
	}

}
