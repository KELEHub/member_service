package com.member.services.back.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.HqlParameter;
import com.member.dao.ParameterDao;
import com.member.entity.GiftsDetails;
import com.member.entity.GiftsHistory;
import com.member.entity.SendGiftsDetails;
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

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<GiftsDetails>  getGiftsDetails() {
		String hql=
			"from GiftsDetails";
		List<GiftsDetails> dataPermissionCodes=(List<GiftsDetails>) parameterDao.queryByHql(hql);
        if(dataPermissionCodes!=null && dataPermissionCodes.size()>0){
        	return dataPermissionCodes;
        }
        return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public GiftsDetails getGiftsDetailsById(Integer id) {
		String hql=
			"from GiftsDetails where id=?";
		List arguments = new ArrayList();
		arguments.add(id);
		List<GiftsDetails> dataPermissionCodes=(List<GiftsDetails>) parameterDao.queryByHql(hql,arguments);
        if(dataPermissionCodes!=null && dataPermissionCodes.size()>0){
        	return dataPermissionCodes.get(0);
        }
        return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<GiftsDetails> getGiftsDetailsByNumber(String number) {
		String hql=
			"from GiftsDetails where number=?";
		List arguments = new ArrayList();
		arguments.add(number);
		List<GiftsDetails> dataPermissionCodes=(List<GiftsDetails>) parameterDao.queryByHql(hql,arguments);
        if(dataPermissionCodes!=null && dataPermissionCodes.size()>0){
        	return dataPermissionCodes;
        }
        return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<GiftsHistory> getGiftsHistoryAll() {
		String hql=
			"from GiftsHistory";
		List<GiftsHistory> dataPermissionCodes=(List<GiftsHistory>) parameterDao.queryByHql(hql);
        if(dataPermissionCodes!=null && dataPermissionCodes.size()>0){
        	return dataPermissionCodes;
        }
        return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<GiftsHistory> getGiftsHistoryByOperationId(Integer id) {
		String hql=
			"from GiftsHistory where userId=?";
		List arguments = new ArrayList();
		arguments.add(id);
		List<GiftsHistory> dataPermissionCodes=(List<GiftsHistory>) parameterDao.queryByHql(hql,arguments);
        if(dataPermissionCodes!=null && dataPermissionCodes.size()>0){
        	return dataPermissionCodes;
        }
        return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOrUpdate(GiftsDetails gd, GiftsHistory gh,Integer pointNumber) {
		String hql=
			"from SendGiftsDetails t where t.giftsDetailsId=? and t.pointNumber<?";
		List arguments = new ArrayList();
		arguments.add(gd.getId());
		arguments.add(pointNumber);
		List<SendGiftsDetails> dataPermissionCodes=(List<SendGiftsDetails>) parameterDao.queryByHql(hql,arguments);
		if(dataPermissionCodes!=null && dataPermissionCodes.size()>0){
			for(SendGiftsDetails ss : dataPermissionCodes){
				ss.setStauts(1);
				parameterDao.saveOrUpdate(ss);
			}
		}
		parameterDao.saveOrUpdate(gd);
		parameterDao.saveOrUpdate(gh);
		
	}

}
