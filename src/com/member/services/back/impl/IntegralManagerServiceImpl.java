package com.member.services.back.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.HqlIntegralManager;
import com.member.dao.IntegralManagerDao;
import com.member.entity.AccountDetails;
import com.member.services.back.IntegralManagerService;

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
}
