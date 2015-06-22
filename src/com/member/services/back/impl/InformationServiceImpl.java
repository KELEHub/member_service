package com.member.services.back.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.beans.back.enumData.KindDataEnum;
import com.member.beans.back.enumData.ProjectEnum;
import com.member.dao.InstitutionDao;
import com.member.entity.AccountDetails;
import com.member.entity.Information;
import com.member.entity.Institution;
import com.member.entity.SystemParameter;
import com.member.services.back.InformationService;
import com.member.util.CommonUtil;

@Service("InformationServiceImpl")
public class InformationServiceImpl implements InformationService{
	
	
	@Resource(name = "InstitutionDaoImpl")
    InstitutionDao institutionDao;
	
	
	

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Information getInformationById(Integer id) {
		String hql="from Information mr where mr.id=?";
		List arguments = new ArrayList();
		arguments.add(id);
		List<Information> result = (List<Information>)institutionDao.queryByHql(hql,arguments);
		if(result!=null && result.size()>0){
			return result.get(0);
		}
		return null;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Information getInformationByNumber(String number) {
		String hql="from Information mr where mr.number=?";
		List arguments = new ArrayList();
		arguments.add(number);
		List<Information> result = (List<Information>)institutionDao.queryByHql(hql,arguments);
		if(result!=null && result.size()>0){
			return result.get(0);
		}
		return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Information> getInformationForNoActivate(String number) {
		String hql="from Information mr where mr.recommendNumber=?";
		List arguments = new ArrayList();
		arguments.add(number);
		List<Information> result = (List<Information>)institutionDao.queryByHql(hql,arguments);
		if(result!=null && result.size()>0){
			return result;
		}
		return null;
		
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteData(Information info) {
		institutionDao.delete(info);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void activate(Information info, Information selfInfo,Institution institution ) {
		info.setIsActivate(1);
		info.setActiveDate(new Date());
		int count =0;
		if(selfInfo.getRecommendCount()!=null){
			count = selfInfo.getRecommendCount();
		}
		selfInfo.setRecommendCount(count+1);
		BigDecimal midMoney = selfInfo.getCrmMoney().subtract(new BigDecimal(institution.getRegisterGold()));
		selfInfo.setCrmMoney(midMoney);
		AccountDetails acFrom = new AccountDetails();
		acFrom.setUserNumber(selfInfo.getNumber());
		acFrom.setCreateTime(new Date());
		acFrom.setKindData(KindDataEnum.goldmoney);
		acFrom.setCountNumber(CommonUtil.getCountNumber());
		
		/**日期类别统计 */
		acFrom.setDateNumber(CommonUtil.getDateNumber());
		acFrom.setProject(ProjectEnum.activateMember);
		/**积分余额 */
		acFrom.setPointbalance(selfInfo.getShoppingMoney());
		/**葛粮币余额 */
		acFrom.setGoldmoneybalance(midMoney);
		/**收入 */
		acFrom.setIncome(new BigDecimal(0));
		/**支出 */
		acFrom.setPay(new BigDecimal(institution.getRegisterGold()));
		/**备注 */
		acFrom.setRedmin("激活会员，会员号："+info.getNumber());
		/**用户ID */
		acFrom.setUserId(selfInfo.getId());
		institutionDao.saveOrUpdate(info);
		institutionDao.saveOrUpdate(selfInfo);
		institutionDao.saveOrUpdate(acFrom);
		
		
	}

}
