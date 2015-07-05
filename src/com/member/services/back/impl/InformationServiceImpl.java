package com.member.services.back.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.beans.back.enumData.GiftEnum;
import com.member.beans.back.enumData.KindDataEnum;
import com.member.beans.back.enumData.ProjectEnum;
import com.member.dao.InstitutionDao;
import com.member.entity.AccountDetails;
import com.member.entity.GiftsDetails;
import com.member.entity.Information;
import com.member.entity.Institution;
import com.member.entity.SendGiftsDetails;
import com.member.services.back.InformationService;
import com.member.services.back.InstitutionService;
import com.member.util.CommonUtil;

@Service("InformationServiceImpl")
public class InformationServiceImpl implements InformationService{
	
	
	@Resource(name = "InstitutionDaoImpl")
    InstitutionDao institutionDao;
	
	@Resource(name = "InstitutionServiceImpl")
    InstitutionService institutionService;
	
	
	
	

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
	public void activate(Information info, Information selfInfo,Institution institution, Information recommendInfo ) {
		info.setIsActivate(1);
		info.setActiveDate(new Date());
		int count =0;
		if(recommendInfo.getRecommendCount()!=null){
			count = recommendInfo.getRecommendCount();
		}
		recommendInfo.setRecommendCount(count+1);
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
		//礼包生成信息
		GiftsDetails gf = new GiftsDetails();
		gf.setUserId(recommendInfo.getId());
		gf.setChildId(info.getId());
		gf.setNumber(recommendInfo.getNumber());
		gf.setGiftEnum(CommonUtil.getGiftEnum());
		gf.setCountNumber(CommonUtil.getCountNumber());
		gf.setDateNumber(CommonUtil.getDateNumber());
		gf.setBatchNo(CommonUtil.getBatchNo());
		gf.setPointNumber(1);
		gf.setName("礼包_"+info.getNumber());
		gf.setCreateTime(new Date());
		institutionDao.saveOrUpdate(gf);
		//详细礼包信息
		int countGifts = 0;
		if(CommonUtil.getGiftEnum().equals(GiftEnum.TEN)){
			countGifts = 10;
		}else{
			countGifts = 5;
		}
		Institution inst = institutionService.getInstitutionInfo();
		for(int i =1;i<=countGifts;i++){
			SendGiftsDetails sg = new SendGiftsDetails();
			sg.setUserId(recommendInfo.getId());
			sg.setChildId(info.getId());
			sg.setNumber(recommendInfo.getNumber());
			sg.setGiftEnum(CommonUtil.getGiftEnum());
			int countNumber = CommonUtil.getMounthNumber()+i-1;
			int realNumber = 0;
			if(countNumber>12){
				realNumber=(CommonUtil.getYearNumber()+1)*100+(i-(13-CommonUtil.getMounthNumber()));
			}else{
				realNumber=CommonUtil.getCountNumber()+i-1;
			}
			sg.setCountNumber(realNumber);
			sg.setDateNumber(CommonUtil.getDateNumber());
			sg.setBatchNo(CommonUtil.getBatchNo());
			sg.setPointNumber(1);
			sg.setName("礼包_"+info.getNumber());
			sg.setCreateTime(new Date());
			sg.setPointNumber(i);
			sg.setGoldMoney(getGoldMoney(inst,i,CommonUtil.getGiftEnum()));
			sg.setGiftsDetailsId(gf.getId());
			sg.setCreateTime(new Date());
			institutionDao.saveOrUpdate(sg);
		}
		//获取50服务积分
		
		
	}
	
	
	private Integer getGoldMoney(Institution inst,int countNumber,GiftEnum gift){
		if(gift.equals(GiftEnum.FIVE)){
			if(countNumber==1){
				return inst.getPreaFirst();
			}else if(countNumber==2){
				return inst.getPreaSecond();
			}else if(countNumber==3){
				return inst.getPreaThree();
			}else if(countNumber==4){
				return inst.getPreaFour();
			}else if(countNumber==5){
				return inst.getPreaFive();
			}
		}else{
			return 1000;
		}
		return 1000;
	}
}
