package com.member.services.back.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.beans.back.enumData.ProjectEnum;
import com.member.dao.HqlParameter;
import com.member.dao.ParameterDao;
import com.member.entity.AccountDetails;
import com.member.entity.LimitDeclaration;
import com.member.services.back.LimitDeclarationService;

@Service("LimitDeclarationServiceImpl")
public class LimitDeclarationServiceImpl implements LimitDeclarationService{
	
	@Resource(name = "ParameterDaoImpl")
    ParameterDao parameterDao;
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public LimitDeclaration getLimitDeclarationInfo() {
		
		List<LimitDeclaration> dataPermissionCodes=(List<LimitDeclaration>) parameterDao.queryByHql(HqlParameter.getLimitDeclarationInfo);
        if(dataPermissionCodes!=null && dataPermissionCodes.size()>0){
        	return dataPermissionCodes.get(0);
        }
        return null;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean LimitDeclaration(LimitDeclaration limitDeclaration) {
		try {
	       	parameterDao.saveOrUpdate(limitDeclaration);
            return true;
		} catch (Exception e) {
			return false;
		}
		
			
		
	}


	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public int getCountThisWeek(Date date,ProjectEnum project) {
		List arguments = new ArrayList();
		arguments.add(date);
		arguments.add(project);
		List<AccountDetails> accountDetailsList=(List<AccountDetails>) parameterDao.queryByHql(HqlParameter.getThisWeekData,arguments);
		if (accountDetailsList != null && accountDetailsList.size() > 0) {
			return  accountDetailsList.size();
		} else {
			return 0;
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public int getCountAll(ProjectEnum project) {
		List arguments = new ArrayList();
		arguments.add(project);
		List<AccountDetails> accountDetailsList=(List<AccountDetails>) parameterDao.queryByHql(HqlParameter.getDtaALL,arguments);
		if (accountDetailsList != null && accountDetailsList.size() > 0) {
			return  accountDetailsList.size();
		} else {
			return 0;
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean saveOrUpdate(Object ob) {
		try {
	       	parameterDao.saveOrUpdate(ob);
            return true;
		} catch (Exception e) {
			return false;
		}
		
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean deleteData(Object ob) {
		try {
	       	parameterDao.delete(ob);
            return true;
		} catch (Exception e) {
			return false;
		}
	}

}
