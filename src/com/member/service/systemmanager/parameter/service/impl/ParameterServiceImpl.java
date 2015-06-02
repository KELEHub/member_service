package com.member.service.systemmanager.parameter.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.service.systemmanager.parameter.HqlParameter;
import com.member.service.systemmanager.parameter.bean.ParameterBean;
import com.member.service.systemmanager.parameter.dao.ParameterDao;
import com.member.service.systemmanager.parameter.po.SystemParameter;
import com.member.service.systemmanager.parameter.service.ParameterService;
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
	public boolean setSystemParameter(SystemParameter systemParameter,ParameterBean pb) {
		
			systemParameter.setDayCount(Integer.valueOf(pb.getDayCount()));
			systemParameter.setGoldFlg(pb.getGoldFlg());
			systemParameter.setGoldMax(Integer.valueOf(pb.getGoldMax()));
			systemParameter.setGoldMin(Integer.valueOf(pb.getGoldMin()));
			systemParameter.setGoldTake(Integer.valueOf(pb.getGoldTake()));
			systemParameter.setScoreMax(Integer.valueOf(pb.getScoreMax()));
			systemParameter.setScoreMin(Integer.valueOf(pb.getScoreMin()));
			systemParameter.setScoreTake(Integer.valueOf(pb.getScoreTake()));
			systemParameter.setGlbMin(Integer.valueOf(pb.getGlbMin()));
			systemParameter.setGlbTake(Integer.valueOf(pb.getGlbTake()));
			systemParameter.setGlbMax(Integer.valueOf(pb.getGlbMax()));
			systemParameter.setScoreInTake(Integer.valueOf(pb.getScoreInTake()));
			systemParameter.setScoreInMin(Integer.valueOf(pb.getScoreInMin()));
	       	parameterDao.saveOrUpdate(systemParameter);
		
             return true;
		
	}

}
