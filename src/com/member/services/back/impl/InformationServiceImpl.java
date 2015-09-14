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
import com.member.entity.CountService;
import com.member.entity.GiftsDetails;
import com.member.entity.Information;
import com.member.entity.Institution;
import com.member.entity.RepeatedMoneyStatistics;
import com.member.entity.SendGiftsDetails;
import com.member.services.back.InformationService;
import com.member.services.back.InstitutionService;
import com.member.util.CommonUtil;

@SuppressWarnings("unchecked")
@Service("InformationServiceImpl")
public class InformationServiceImpl implements InformationService{
	
	@Resource(name = "InformationServiceImpl")
	public InformationService informationService;
	
	@Resource(name = "InstitutionDaoImpl")
    InstitutionDao institutionDao;
	
	@Resource(name = "InstitutionServiceImpl")
    InstitutionService institutionService;

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
		String hql="from Information mr where mr.activateNumber=?   order by registerDate desc";
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
	public boolean activate(Information info, Information selfInfo,Institution institution, Information recommendInfo ) {
		try {
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
			institutionDao.saveOrUpdate(recommendInfo);
			institutionDao.saveOrUpdate(acFrom);
			//礼包生成信息
			int day = CommonUtil.getDay();
			GiftsDetails gf = new GiftsDetails();
			gf.setUserId(recommendInfo.getId());
			gf.setChildId(info.getId());
			gf.setNumber(recommendInfo.getNumber());
			gf.setGiftEnum(CommonUtil.getGiftEnum());
			if(day==31){
				gf.setCountNumber(CommonUtil.getCountNumber()+1);
			}else{
				gf.setCountNumber(CommonUtil.getCountNumber());
			}
			gf.setDateNumber(CommonUtil.getDateNumber());
			gf.setBatchNo(CommonUtil.getBatchNo());
			gf.setPointNumber(1);
			gf.setName("礼包_"+info.getNumber());
			gf.setCreateTime(new Date());
			institutionDao.saveOrUpdate(gf);
			//详细礼包信息
			int countGifts = 0;
			if(CommonUtil.getGiftEnum().equals(GiftEnum.TEN)|| CommonUtil.getGiftEnum().equals(GiftEnum.TEN)){
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
				if(day==31){
					int year = CommonUtil.getYearNumber();
					int mouth = CommonUtil.getMounthNumber();
					if(mouth+i > 12){
						mouth=mouth+i-12;
						year=year+1;
					}else{
						mouth=mouth+i;
					}
					String mouthStr="";
					if(String.valueOf(mouth).length()==1){
						mouthStr="0"+String.valueOf(mouth);
					}else{
						mouthStr=String.valueOf(mouth);
					}
					realNumber=Integer.valueOf(String.valueOf(year)+mouthStr);
				}else{
					if(countNumber>12){
						realNumber=(CommonUtil.getYearNumber()+1)*100+(i-(13-CommonUtil.getMounthNumber()));
					}else{
						realNumber=CommonUtil.getCountNumber()+i-1;
					}
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
				if(CommonUtil.getGiftEnum().equals(GiftEnum.TEN)){
					sg.setCoupon(160);
				}
				sg.setCreateTime(new Date());
				institutionDao.saveOrUpdate(sg);
			}
			return true;
		
		} catch (Exception e) {
			 e.printStackTrace();
			 return false;
		}
		
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void serverRigster(Information info, Information selfInfo,Institution institution, Information recommendInfo ){
		try {
			//激活人获取50服务积分
//			String hql = "update Information set repeatedMoney=?,repeatedAccumulative=?,shoppingMoney=?,shoppingAccumulative=? where id=?";
//			List<Object> list = new ArrayList<Object>();
//			list.add(selfInfo.getRepeatedMoney().add(new BigDecimal(50)));
//			list.add(selfInfo.getRepeatedAccumulative().add(new BigDecimal(50)));
//			list.add(selfInfo.getShoppingMoney().add(new BigDecimal(50)));
//			list.add(selfInfo.getShoppingAccumulative().add(new BigDecimal(50)));
//			list.add(selfInfo.getId());
//			institutionDao.executeHqlUpdate(hql, list);
			selfInfo.setRepeatedMoney(selfInfo.getRepeatedMoney().add(new BigDecimal(50)));
			selfInfo.setRepeatedAccumulative(selfInfo.getRepeatedAccumulative().add(new BigDecimal(50)));
			selfInfo.setShoppingMoney(selfInfo.getShoppingMoney().add(new BigDecimal(50)));
			selfInfo.setShoppingAccumulative(selfInfo.getShoppingAccumulative().add(new BigDecimal(50)));
			int servercount=0;
			if(selfInfo.getServiceSum()==null){
				servercount=0;
			}else{
				servercount = selfInfo.getServiceSum();
			}
			selfInfo.setServiceSum(servercount+1);
			institutionDao.saveOrUpdate(selfInfo);
			
			//更新在AccountDetails表记录激活人获得服务积分明细
			List<AccountDetails> member = getMemberBycountNumberAndUserNumber(CommonUtil.getServerCountNumber(), selfInfo.getNumber());
			if (member!=null && member.size()>0){
				
				AccountDetails memberInfo = member.get(0);
				/**积分余额 */
				memberInfo.setPointbalance(selfInfo.getRepeatedMoney());
				/**葛粮币余额 */
				memberInfo.setGoldmoneybalance(selfInfo.getCrmMoney());
				/**收入 */
				memberInfo.setIncome(memberInfo.getIncome().add(new BigDecimal(50)));
				institutionDao.saveOrUpdate(memberInfo);
			}else{
				AccountDetails shopingDetails = new AccountDetails();
				shopingDetails.setKindData(KindDataEnum.points);
				/**日期类别统计 */
				shopingDetails.setDateNumber(CommonUtil.getDateNumber());
				/**流水号 */
				shopingDetails.setCountNumber(CommonUtil.getServerCountNumber());
				/**项目 */
				shopingDetails.setProject(ProjectEnum.servicepointsforone);
				/**积分余额 */
				shopingDetails.setPointbalance(selfInfo.getRepeatedMoney());
				/**葛粮币余额 */
				shopingDetails.setGoldmoneybalance(selfInfo.getCrmMoney());
				/**收入 */
				shopingDetails.setIncome(new BigDecimal(50));
				/**支出 */
				shopingDetails.setPay(new BigDecimal(0));
				/**备注 */
				shopingDetails.setRedmin("激活会员");
				/**用户ID */
				shopingDetails.setUserId(selfInfo.getId());
				/**用户登录ID */
				shopingDetails.setUserNumber(selfInfo.getNumber());
				shopingDetails.setCreateTime(new Date());
				institutionDao.saveOrUpdate(shopingDetails);
			}
			//在报单统计表中插入一条记录便于统计
			CountService count = new CountService();
			/**流水号 */
			count.setCountNumber(CommonUtil.getServerCountNumber());
			/**日期类别统计 */
			count.setDateNumber(CommonUtil.getDateNumber());
			/**用户ID */
			count.setUserId(selfInfo.getId());
			/**用户登录ID */
			count.setUserNumber(selfInfo.getNumber());
			count.setCreateTime(new Date());
			institutionDao.saveOrUpdate(count);
			
			//在RepeatedMoneyStatistics表中添加一条记录，用于发放极差积分
			if(!"company".equals(selfInfo.getLeaderServiceNumber().trim())){
				RepeatedMoneyStatistics moneyStatistics = new RepeatedMoneyStatistics();
				moneyStatistics.setCreateTime(new Date());
				moneyStatistics.setDateNumber(CommonUtil.getDateNumber());
				moneyStatistics.setDeclarationId(info.getId());
				moneyStatistics.setDeclarationNumber(info.getNumber());
				moneyStatistics.setDeclarationBenefitId(selfInfo.getLeaderServiceId());
				moneyStatistics.setDeclarationBenefitNumber(selfInfo.getLeaderServiceNumber());
				moneyStatistics.setSerialNumber(CommonUtil.getCountNumber());
				moneyStatistics.setState(0);
				Information leadInfo = informationService.getInformationByNumber(selfInfo.getLeaderServiceNumber());
				if(leadInfo.getBdUse()!= null && leadInfo.getBdUse()==1){
					moneyStatistics.setDbUse(1);
				}else{
					moneyStatistics.setDbUse(0);
				}
				institutionDao.saveOrUpdate(moneyStatistics);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<AccountDetails> getMemberBycountNumberAndUserNumber(Integer countNumber,String userNumber) {
		String hql="from AccountDetails ad where ad.countNumber=? and ad.userNumber=?";
		List arguments = new ArrayList();
		arguments.add(countNumber);
		arguments.add(userNumber);
		List<AccountDetails> result = (List<AccountDetails>)institutionDao.queryByHql(hql,arguments);
		return result;
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
			return 840;
		}
		return 840;
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOrUpfate(Information info) {
		try {
			institutionDao.saveOrUpdate(info);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	@Override
	@Transactional(readOnly = true)
	public int countBankCard(String card) {
		String hql="from Information mr where mr.bankCard=?";
		List arguments = new ArrayList();
		arguments.add(card);
		List<Information> result = (List<Information>)institutionDao.queryByHql(hql,arguments);
		if(result!=null && result.size()>0){
			return result.size();
		}
		return 0;
	}
}
