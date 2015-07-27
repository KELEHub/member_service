package com.member.services.front.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.member.beans.back.enumData.ProjectEnum;
import com.member.dao.ParameterDao;
import com.member.entity.AccountDetails;
import com.member.entity.CountService;
import com.member.form.back.StatisticsForm;
import com.member.services.front.AccountDetailsService;

@Service("AccountDetailsServiceImpl")
public class AccountDetailsServiceImpl implements AccountDetailsService {

	@Resource(name = "ParameterDaoImpl")
	private ParameterDao parameterDao;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<AccountDetails> getAccountDetailsByUserNumber(String userNumber) {
		String hql = "from AccountDetails where userNumber=?";
		List arguments = new ArrayList();
		arguments.add(userNumber);
		List<AccountDetails> list = (List<AccountDetails>) parameterDao
				.queryByHql(hql, arguments);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<AccountDetails> getAccountDetailsByNoservicepoints(
			String condition, List argumenrs,int pagesize,int pageNumber) {
		String hql = "from AccountDetails where " + condition;
		List<AccountDetails> list = (List<AccountDetails>) parameterDao
				.queryByHql(hql, pageNumber,pagesize,argumenrs);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<AccountDetails> getAccountDetailsByservicepoints(
			String condition, List argumenrs) {
		String hql = "select new AccountDetails(sum(income) as income,sum(pay) as pay,countNumber) from AccountDetails where "
				+ condition;
		List<?> list = parameterDao.queryByHql(hql, argumenrs);
		if (list != null && list.size() > 0) {
			return (List<AccountDetails>) list;
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<StatisticsForm> getAccountDetailsGroupBy(
			ProjectEnum servicepointsforone, ProjectEnum recharge,
			ProjectEnum fromgifts, ProjectEnum togoldmoneycut) {

		String hql = "select new map(t.dateNumber as dateNumber,sum(case when t.project =? then 1 else 0 end) as countBill, sum(case when t.project =? then t.income else 0 end) as countRecharge,sum(case when project =? then t.income else 0 end) as countFromgifts,sum(case when project =? then t.pay else 0 end) as countPointcash)from AccountDetails t group by t.dateNumber";
		String pql = "select new map(t.dateNumber as dateNumber,count(*) as countBill)from CountService t group by t.dateNumber";
		List arguments = new ArrayList();
		arguments.add(servicepointsforone);
		arguments.add(recharge);
		arguments.add(fromgifts);
		arguments.add(togoldmoneycut);
		List<Object> list = (List<Object>) parameterDao.queryByHql(hql,
				arguments);
		List<Object> serviceList = (List<Object>) parameterDao.queryByHql(pql);
		if (list != null && list.size() > 0) {
			List<StatisticsForm> rangeList = new ArrayList<StatisticsForm>();
			for (Object obj : list) {
				StatisticsForm rif = new StatisticsForm();
				String dateNumber = ((Map<String, String>) obj)
						.get("dateNumber");
				for(Object sb : serviceList){
					if(dateNumber.equals(((Map<String, String>) sb).get("dateNumber"))){
						rif.setCountBill(String.valueOf(((Map<String, Integer>)  sb)
								.get("countBill")));
						break;
					}
				}
				rif.setDateNumber(dateNumber.substring(0, 4) + "年"
						+ dateNumber.substring(4, 6) + "月第"
						+ dateNumber.substring(7, 8) + "期次");
				
				rif.setCountFromgifts(String
						.valueOf(((Map<String, Integer>) obj)
								.get("countFromgifts")));
				rif.setCountPointcash(String
						.valueOf(((Map<String, Integer>) obj)
								.get("countPointcash")));
				rif.setCountRecharge(String
						.valueOf(((Map<String, Integer>) obj)
								.get("countRecharge")));
				rangeList.add(rif);
			}
			return rangeList;
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Map<String, Integer> getCountAll(ProjectEnum servicepointsforone,
			ProjectEnum recharge, ProjectEnum fromgifts,
			ProjectEnum togoldmoneycut) {

		String hql = "select new map(sum(case when t.project =? then 1 else 0 end) as countBill, sum(case when t.project =? then t.income else 0 end) as countRecharge,sum(case when project =? then t.income else 0 end) as countFromgifts,sum(case when project =? then t.pay else 0 end) as countPointcash)from AccountDetails t";
		List arguments = new ArrayList();
		arguments.add(servicepointsforone);
		arguments.add(recharge);
		arguments.add(fromgifts);
		arguments.add(togoldmoneycut);
		List<Object> list = (List<Object>) parameterDao.queryByHql(hql,
				arguments);
		if (list != null && list.size() > 0) {
			return (Map<String, Integer>) list.get(0);
		}
		return null;
	}

	@Override
	public int getCountServerPointByNumber(String number,String dataNumber) {
		String hql = " from CountService where userNumber=? and (dateNumber=? or dateNumber=? or dateNumber=?)";
		List arguments = new ArrayList();
		arguments.add(number);
		arguments.add(dataNumber+"01");
		arguments.add(dataNumber+"02");
		arguments.add(dataNumber+"03");
		List<?> list = parameterDao.queryByHql(hql, arguments);
		if (list != null && list.size() > 0) {
			return list.size();
		}
		return 0;
	}

	@Override
	public int couontDetails(String condition, List argumenrs) {
		String hql = "from AccountDetails where " + condition;
		List<AccountDetails> list = (List<AccountDetails>) parameterDao
				.queryByHql(hql, argumenrs);
		if(list!=null && list.size()>0){
			return list.size();
		}
		return 0;
	}

	@Override
	public int countService() {
		String hql = "from CountService ";
		List<CountService> list = (List<CountService>) parameterDao
				.queryByHql(hql);
		if (list != null && list.size() > 0) {
			return list.size();
		}
		return 0;
	}

}
