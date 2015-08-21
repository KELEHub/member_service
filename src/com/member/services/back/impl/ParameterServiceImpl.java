package com.member.services.back.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<GiftsDetails>  getGiftsDetails(String number, Integer pageSize,
			Integer pageNumber) {
		List<GiftsDetails> dataPermissionCodes = null;
		String hql=
			"from GiftsDetails  ";
		if(number!=null && !"".equals(number)){
			hql = hql + " where number = :number";
			Map<String, Object> arguments = new HashMap<String, Object>();
			arguments.put("number", number);
			hql = hql + " order by createTime desc";
			dataPermissionCodes=(List<GiftsDetails>) parameterDao.queryByHql(hql,pageNumber,pageSize,arguments);
		}else{
			hql = hql + " order by createTime desc";
			dataPermissionCodes=(List<GiftsDetails>) parameterDao.queryByHql(hql,pageNumber,pageSize);
		}
		
		
        if(dataPermissionCodes!=null && dataPermissionCodes.size()>0){
        	return dataPermissionCodes;
        }
        return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public int  countGiftsDetails(String number) {
		List<GiftsDetails> dataPermissionCodes = null;
		String hql=
			"from GiftsDetails  ";
		if(number!=null && !"".equals(number)){
			hql = hql + " where number = :number";
			Map<String, Object> arguments = new HashMap<String, Object>();
			arguments.put("number", number);
			hql = hql + " order by createTime desc";
			dataPermissionCodes=(List<GiftsDetails>) parameterDao.queryByHql(hql, arguments);
		}else{
			hql = hql + " order by createTime desc";
			dataPermissionCodes=(List<GiftsDetails>) parameterDao.queryByHql(hql);
		}
		
		
        if(dataPermissionCodes!=null && dataPermissionCodes.size()>0){
        	return dataPermissionCodes.size();
        }
        return 0;
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
			"from GiftsDetails where number=?  order by createTime desc";
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
			"from GiftsHistory order by createTime desc";
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
			"from GiftsHistory where userId=?  order by createTime desc";
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
	public void saveOrUpdate(GiftsDetails gd, GiftsHistory gh,Integer pointNumber,Boolean flg) {
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
		if(flg){
			String sdql=
				"from SendGiftsDetails t where t.giftsDetailsId=? ";
			List arguments2 = new ArrayList();
			arguments2.add(gd.getId());
			List<SendGiftsDetails> updateNumberCodes=(List<SendGiftsDetails>) parameterDao.queryByHql(sdql,arguments2);
			if(updateNumberCodes!=null && updateNumberCodes.size()>0){
				for(SendGiftsDetails dss : updateNumberCodes){
					dss.setNumber(gd.getNumber());
					dss.setUserId(gd.getUserId());
					parameterDao.saveOrUpdate(dss);
				}
			}
			
		}
		parameterDao.saveOrUpdate(gd);
		parameterDao.saveOrUpdate(gh);
		
	}

}
