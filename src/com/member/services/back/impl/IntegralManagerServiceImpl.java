package com.member.services.back.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.beans.back.enumData.ProjectEnum;
import com.member.dao.HqlIntegralManager;
import com.member.dao.HqlServiceManager;
import com.member.dao.IntegralManagerDao;
import com.member.entity.AccountDetails;
import com.member.entity.Information;
import com.member.entity.RepeatedMoneyStatistics;
import com.member.form.back.IntegralHistoryForm;
import com.member.form.back.RangeIssueForm;
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
	public List<RangeIssueForm> getAvailableRangeIntegral(int serialNumber) {
		List<Object> list = (List<Object>) integralManagerDao.queryByHql(
				HqlIntegralManager.getRangeIssueBySerialNumber,serialNumber);
		if (list!=null &&list.size()>0){
			List<RangeIssueForm> rangeList = new ArrayList<RangeIssueForm>();
			for(Object obj : list){
				RangeIssueForm rif = new RangeIssueForm();
				rif.setUserId(((Map<String, Integer>)obj).get("declarationBenefitId"));
				rif.setUserNumber(((Map<String, String>)obj).get("declarationBenefitNumber"));
				long count = ((Map<String, Long>)obj).get("countNumber");
				rif.setAvailableInt(new BigDecimal(count*20));
				rangeList.add(rif);
			}
			return rangeList;
		}
		 return null;
	}

	
	@Transactional(readOnly=true)
	public List<IntegralHistoryForm> getIntegralHistoryPoints(String number,int pageSize,int pageNumber) {
		String hql = "select new map(userNumber as userNumber,case when max(t.project)=:gifts then sum(income) end as points,case when max(t.project)=:servermuch then sum(income) end as serverpoints,sum(income) as totalpoints,to_char(createtime, 'YYYY-MM-DD') as datetime) from AccountDetails t where (t.project=:gifts or t.project=:servermuch)  ";
		Map<String, Object> arguments = new HashMap<String, Object>();
		arguments.put("gifts", ProjectEnum.fromgifts);
		arguments.put("servermuch", ProjectEnum.servicepointsformuch);
		if(number != null && !"".equals(number)){
			hql=hql+" and userNumber = :number";
			arguments.put("number", number);
		}
		
		hql=hql+" group by to_char(createtime, 'YYYY-MM-DD'),userNumber order by to_char(createtime, 'YYYY-MM-DD') desc";
		List<Object> result = (List<Object>)integralManagerDao.queryByHql(hql,pageNumber,pageSize,arguments);
		List<IntegralHistoryForm> formList = new ArrayList<IntegralHistoryForm>();
		if(result!=null && result.size()>0){
			for (Object obj : result) {
				IntegralHistoryForm rif = new IntegralHistoryForm();
				String datetime = ((Map<String, String>) obj)
						.get("datetime");
				rif.setCreateTime(datetime);
				String points = null;
				if(((Map<String, BigDecimal>) obj)
						.get("points")!=null){
					points = String.valueOf(((Map<String, BigDecimal>) obj)
							.get("points"));
				}
				
				rif.setPoints(points);
				String serverpoints = null;
				if(((Map<String, BigDecimal>) obj)
						.get("serverpoints")!=null){
					serverpoints= String.valueOf(((Map<String, BigDecimal>) obj)
							.get("serverpoints"));
				}
				rif.setServerpoints(serverpoints);
				String totalpoints= null;
				if(((Map<String, BigDecimal>) obj)
						.get("totalpoints")!=null){
					totalpoints = String.valueOf(((Map<String, BigDecimal>) obj)
							.get("totalpoints"));
				}
				rif.setTotalpoints(totalpoints);
				String usernumber=((Map<String, String>) obj)
				.get("userNumber");
				rif.setUserNumber(usernumber);
				formList.add(rif);
			}
			
			
			return formList;
		}
		return formList;
	}
	@Override
	@Transactional(readOnly=true)
	public int countIntegralHistoryPoints(String number){
		String hql = "select new map(userNumber,case when max(t.project)=:gifts then sum(income) end as points,case when max(t.project)=:servermuch then sum(income) end as serverpoints,sum(income) as totalpoints,to_char(createtime, 'YYYY-MM-DD') as datetime) from AccountDetails t where (t.project=:gifts or t.project=:servermuch)  ";
		Map<String, Object> arguments = new HashMap<String, Object>();
		arguments.put("gifts", ProjectEnum.fromgifts);
		arguments.put("servermuch", ProjectEnum.servicepointsformuch);
		if(number != null && !"".equals(number)){
			hql=hql+" and userNumber = :number";
			arguments.put("number", number);
		}
		
		hql=hql+" group by to_char(createtime, 'YYYY-MM-DD'),userNumber order by to_char(createtime, 'YYYY-MM-DD') desc";
	
		List<Object> result = (List<Object>)integralManagerDao.queryByHql(hql,arguments);
		if(result!=null && result.size()>0){
			return result.size();
		}
		return 0;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<IntegralHistoryForm> getFromgiftsHistoryPoints(int pageSize,int pageNumber,String number) {
		String hql = "select new map(userNumber as userNumber,sum(income) as income,max(pointbalance) as pointbalance,to_char(createtime, 'YYYY-MM-DD') as datetime)from AccountDetails t where t.project=:gifts  ";
		Map<String, Object> arguments = new HashMap<String, Object>();
		if(number!=null && !"".equals(number)){
			hql=hql + " and userNumber=:usernumber";
			arguments.put("usernumber", number);
		}
		hql = hql +" group by to_char(createtime, 'YYYY-MM-DD'),userNumber  order by to_char(createtime, 'YYYY-MM-DD') desc";
		arguments.put("gifts", ProjectEnum.fromgifts);
		List<Object> result = (List<Object>)integralManagerDao.queryByHql(hql,pageNumber,pageSize,arguments);
		List<IntegralHistoryForm> formList = new ArrayList<IntegralHistoryForm>();
		if(result!=null && result.size()>0){
			for (Object obj : result){
				IntegralHistoryForm ad = new IntegralHistoryForm();
				String datetime = ((Map<String, String>) obj)
				.get("datetime");
				String points =null;
				if(((Map<String, BigDecimal>) obj)
						.get("income")!=null){
					points = String.valueOf(((Map<String, BigDecimal>) obj)
							.get("income"));
				}
				String balance=null;
				if(((Map<String, BigDecimal>) obj)
						.get("pointbalance")!=null){
					balance = String.valueOf(((Map<String, BigDecimal>) obj)
							.get("pointbalance"));
				}
				String usernumber=((Map<String, String>) obj)
				.get("userNumber");
				ad.setIncome(points);
				ad.setPointbalance(balance);
				ad.setCreateTime(datetime);
				ad.setUserNumber(usernumber);
				formList.add(ad);
			}
			
		}
		return formList;
	}

	@Override
	@Transactional(readOnly=true)
	public List<AccountDetails> getIntegralHistoryPointsByNumber(String number) {
		String hql = "from AccountDetails t where (t.project=? or t.project=?) and t.userNumber=?  order by createTime desc";
		List arguments = new ArrayList();
		arguments.add(ProjectEnum.fromgifts);
		arguments.add(ProjectEnum.servicepointsformuch);
		arguments.add(number);
		List<AccountDetails> result = (List<AccountDetails>)integralManagerDao.queryByHql(hql,arguments);
		if(result!=null && result.size()>0){
			return result;
		}
		return null;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Information getInformationByNumber(String number) {
		List<Information> result = (List<Information>) integralManagerDao.queryByHql(
				HqlServiceManager.getServiceByNumber, number);
		if(result!=null){
			return (Information) result.get(0);
		}else{
			return null;
		}
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<AccountDetails> getMemberBycountNumberAndUserNumber(Integer countNumber,String userNumber) {
		String hql="from AccountDetails ad where ad.countNumber=? and ad.userNumber=?";
		List arguments = new ArrayList();
		arguments.add(countNumber);
		arguments.add(userNumber);
		List<AccountDetails> result = (List<AccountDetails>)integralManagerDao.queryByHql(hql,arguments);
		return result;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOrUpdateRelation(Information info,AccountDetails ad,String BenefitNumber,Integer serialNumber) {
		integralManagerDao.saveOrUpdate(info);
		integralManagerDao.saveOrUpdate(ad);
		String hql = "from RepeatedMoneyStatistics  where dbUse !=1 and declarationBenefitNumber=? and serialNumber<?";
		List<Object> list = new ArrayList<Object>();
		list.add(BenefitNumber);
		list.add(serialNumber);
		List<RepeatedMoneyStatistics> results = (List<RepeatedMoneyStatistics>)integralManagerDao.queryByHql(hql, list);
		if(results!=null && results.size()>0){
			for(RepeatedMoneyStatistics s : results){
				integralManagerDao.delete(s);
			}
		}
	}

	@Override
	public int countFromgiftsHistoryPoints(String number) {
		String hql = "select new map(userNumber as userNumber,sum(income) as income,to_char(createtime, 'YYYY-MM-DD') as datetime)from AccountDetails t where t.project=:gifts  ";
		Map<String, Object> arguments = new HashMap<String, Object>();
		if(number!=null && !"".equals(number)){
			hql=hql + " and userNumber=:usernumber";
			arguments.put("usernumber", number);
		}
		hql = hql +" group by to_char(createtime, 'YYYY-MM-DD'),userNumber  order by to_char(createtime, 'YYYY-MM-DD') desc";
		arguments.put("gifts", ProjectEnum.fromgifts);
		List<Object> result = (List<Object>)integralManagerDao.queryByHql(hql,arguments);
		if(result!=null && result.size()>0){
			return result.size();
		}
		return 0;
	}
}
