package com.member.services.back.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.HqlParameter;
import com.member.dao.ParameterDao;
import com.member.entity.SystemParameter;
import com.member.services.back.ParameterService;
@Service("ParameterServiceImpl")
public class ParameterServiceImpl implements ParameterService{
	
	@Resource(name = "ParameterDaoImpl")
    ParameterDao parameterDao;
	

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public SystemParameter getSystemParameter() {
		
		List<SystemParameter> dataPermissionCodes=(List<SystemParameter>) parameterDao.queryByHql(HqlParameter.getSystemParameter);
        if(dataPermissionCodes!=null && dataPermissionCodes.size()>0){
        	return dataPermissionCodes.get(0);
        }
        return null;
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean setSystemParameter(SystemParameter systemParameter) {
		try {
	       	parameterDao.saveOrUpdate(systemParameter);
            return true;
		} catch (Exception e) {
			return false;
		}
		
			
		
	}

}
