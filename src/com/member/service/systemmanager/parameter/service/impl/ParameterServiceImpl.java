package com.member.service.systemmanager.parameter.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.member.service.systemmanager.parameter.HqlParameter;
import com.member.service.systemmanager.parameter.dao.ParameterDao;
import com.member.service.systemmanager.parameter.po.SystemParameter;
import com.member.service.systemmanager.parameter.service.ParameterService;
@Service("ParameterServiceImpl")
public class ParameterServiceImpl implements ParameterService{
	
	@Resource(name = "ParameterDaoImpl")
    ParameterDao parameterDao;
	

	@SuppressWarnings("unchecked")
	public SystemParameter getSystemParameter() {
		
		List<SystemParameter> dataPermissionCodes=(List<SystemParameter>) parameterDao.queryByHql(HqlParameter.getSystemParameter);
        if(dataPermissionCodes!=null && dataPermissionCodes.size()>0){
        	return dataPermissionCodes.get(0);
        }
        return null;
		
	}

}
