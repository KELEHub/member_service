package com.member.services.back.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.member.beans.back.enumData.ProjectEnum;
import com.member.dao.HqlIntegralManager;
import com.member.dao.IntegralManagerDao;
import com.member.entity.AccountDetails;
import com.member.entity.RepeatedMoneyStatistics;
import com.member.services.back.IntegralManagerService;

@SuppressWarnings("unchecked")
@Service("IntegralManagerServiceImpl")
public class IntegralManagerServiceImpl implements IntegralManagerService {

	@Resource(name = "IntegralManagerDaoImpl")
    IntegralManagerDao integralManagerDao;
	
	
	@Override
	@Transactional(readOnly=true)
	public List<AccountDetails> getIntegralHistory() {
		return (List<AccountDetails>) integralManagerDao.queryByHql(
				HqlIntegralManager.getIntegralHistory);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<AccountDetails> getIntegralHistoryByUserName(String userName) {
		return (List<AccountDetails>) integralManagerDao.queryByHql(
				HqlIntegralManager.getIntegralHistoryByUserName,userName);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<RepeatedMoneyStatistics> getAvailableRangeIntegral() {
		return (List<RepeatedMoneyStatistics>) integralManagerDao.queryByHql(
				HqlIntegralManager.getIntegralHistoryByUserName);
	}

	@Override
	public List<AccountDetails> getIntegralHistoryPoints() {
		String hql = "from AccountDetails t where t.project=? or t.project=?";
		List arguments = new ArrayList();
		arguments.add(ProjectEnum.fromgifts);
		arguments.add(ProjectEnum.servicepointsforone);
		List<AccountDetails> result = (List<AccountDetails>)integralManagerDao.queryByHql(hql,arguments);
		if(result!=null && result.size()>0){
			return result;
		}
		return null;
	}

	@Override
	public List<AccountDetails> getIntegralHistoryPointsByNumber(String number) {
		String hql = "from AccountDetails t where (t.project=? or t.project=?) and t.userNumber=?";
		List arguments = new ArrayList();
		arguments.add(ProjectEnum.fromgifts);
		arguments.add(ProjectEnum.servicepointsforone);
		arguments.add(number);
		List<AccountDetails> result = (List<AccountDetails>)integralManagerDao.queryByHql(hql,arguments);
		if(result!=null && result.size()>0){
			return result;
		}
		return null;
	}
}
