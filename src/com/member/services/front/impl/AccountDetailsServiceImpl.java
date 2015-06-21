package com.member.services.front.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.ParameterDao;
import com.member.entity.AccountDetails;
import com.member.services.front.AccountDetailsService;
@Service("AccountDetailsServiceImpl")
public class AccountDetailsServiceImpl implements AccountDetailsService{
	
	@Resource(name = "ParameterDaoImpl")
	private ParameterDao parameterDao;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public AccountDetails getAccountDetailsByUserNumber(String userNumber) {
		String hql ="from AccountDetails where userNumber=?";
		List arguments = new ArrayList();
		arguments.add(userNumber);
		List<AccountDetails> list = (List<AccountDetails>)parameterDao.queryByHql(hql,arguments);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<AccountDetails>  getAccountDetailsByNoservicepoints(String condition,List argumenrs) {
		String hql ="from AccountDetails where " + condition;
		List<AccountDetails> list = (List<AccountDetails>)parameterDao.queryByHql(hql,argumenrs);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<AccountDetails> getAccountDetailsByservicepoints(
			String condition, List argumenrs) {
		String hql ="select new AccountDetails(sum(income) as income,sum(pay) as pay,countNumber) from AccountDetails where " + condition;
		List<?> list = parameterDao.queryByHql(hql,argumenrs);
		if(list !=null && list.size()>0){
			return  (List<AccountDetails>)list;
		}
		
		return null;
	}
	

}
