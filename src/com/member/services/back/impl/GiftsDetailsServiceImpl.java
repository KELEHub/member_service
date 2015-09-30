package com.member.services.back.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.beans.back.enumData.BatchNoEnum;
import com.member.beans.back.enumData.KindDataEnum;
import com.member.beans.back.enumData.ProjectEnum;
import com.member.dao.GiftsDao;
import com.member.entity.AccountDetails;
import com.member.entity.GiftsDetails;
import com.member.entity.Information;
import com.member.entity.SendGiftsDetails;
import com.member.services.back.GiftsDetailsService;
import com.member.services.back.InformationService;
import com.member.services.back.ParameterService;
import com.member.util.CommonUtil;

@Service("GiftsDetailsServiceImpl")
public class GiftsDetailsServiceImpl implements GiftsDetailsService {

	@Resource(name = "GiftsDaoImpl")
	private GiftsDao giftsDao;

	@Resource(name = "InformationServiceImpl")
	public InformationService informationService;

	@Resource(name = "ParameterServiceImpl")
	public ParameterService parameterService;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<SendGiftsDetails> getGiftsDetailsList(Integer countNumber,
			BatchNoEnum batchNo) {
		String hql = "from SendGiftsDetails mr where mr.countNumber<? and  mr.batchNo=? and mr.stauts=0";
		List arguments = new ArrayList();
		arguments.add(countNumber);
		arguments.add(batchNo);
		List<SendGiftsDetails> result = (List<SendGiftsDetails>) giftsDao
				.queryByHql(hql, arguments);
		if (result != null && result.size() > 0) {
			return result;
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String getCountGoldAll(Integer countNumber, BatchNoEnum batchNo) {
		String sql = "select SUM(mr.goldMoney) from SendGiftsDetails mr where mr.countNumber<? and  mr.batchNo=? and mr.stauts=0";
		List arguments = new ArrayList();
		arguments.add(countNumber);
		arguments.add(batchNo);
		List<?> result = giftsDao.queryByHql(sql, arguments);
		if (result != null && result.size() > 0) {
			if(result.get(0) != null){
				return result.get(0).toString();
			}
		}

		return "0";
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String getCountCouponAll(Integer countNumber, BatchNoEnum batchNo) {
		String sql = "select SUM(mr.coupon) from SendGiftsDetails mr where mr.countNumber<? and  mr.batchNo=? and mr.stauts=0";
		List arguments = new ArrayList();
		arguments.add(countNumber);
		arguments.add(batchNo);
		List<?> result = giftsDao.queryByHql(sql, arguments);
		if (result != null && result.size() > 0) {
			if(result.get(0) != null){
				return result.get(0).toString();
			}
		}

		return "0";
	}
	
	

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void senGold(SendGiftsDetails ss) {

		Information information = informationService.getInformationByNumber(ss
				.getNumber());
		GiftsDetails giftsDetails = parameterService.getGiftsDetailsById(ss
				.getGiftsDetailsId());
		BigDecimal shoppingMoney = information.getShoppingMoney().add(
				new BigDecimal(ss.getGoldMoney()));
		BigDecimal shoppingAccumulative = information.getShoppingAccumulative()
				.add(new BigDecimal(ss.getGoldMoney()));
		giftsDetails.setPointNumber(giftsDetails.getPointNumber() + 1);
		information.setShoppingMoney(shoppingMoney);
		information.setShoppingAccumulative(shoppingAccumulative);
		//消费卷
		if(ss.getCoupon()!=null && !"".equals(ss.getCoupon())&& ss.getCoupon()!=0){
			
			BigDecimal coupon = information.getCoupon().add(
					new BigDecimal(ss.getCoupon()));
			BigDecimal couponAccumulative = information.getCouponAccumulative().add(
					new BigDecimal(ss.getCoupon()));
			information.setCoupon(coupon);
			information.setCouponAccumulative(couponAccumulative);
		}
		AccountDetails shopingDetails = new AccountDetails();
		shopingDetails.setUserNumber(ss.getNumber());
		shopingDetails.setCreateTime(new Date());
		shopingDetails.setKindData(KindDataEnum.points);
		/** 日期类别统计 */
		shopingDetails.setDateNumber(getDataNumber(ss.getCountNumber(),ss.getBatchNo()));
		shopingDetails.setProject(ProjectEnum.fromgifts);
		shopingDetails.setCountNumber(CommonUtil.getCountNumber());
		/** 积分余额 */
		shopingDetails.setPointbalance(shoppingMoney);
		/** 葛粮币余额 */
		shopingDetails.setGoldmoneybalance(information.getCrmMoney());
		/** 收入 */
		if(ss.getCoupon()!=null && !"".equals(ss.getCoupon())&& ss.getCoupon()!=0){
			AccountDetails couponDetails = new AccountDetails();
			couponDetails.setIncome(new BigDecimal(ss.getCoupon()));
			couponDetails.setUserNumber(ss.getNumber());
			couponDetails.setCreateTime(new Date());
			couponDetails.setKindData(KindDataEnum.xfpp);
			/** 日期类别统计 */
			couponDetails.setDateNumber(getDataNumber(ss.getCountNumber(),ss.getBatchNo()));
			couponDetails.setProject(ProjectEnum.fromgifts);
			couponDetails.setCountNumber(CommonUtil.getCountNumber());
			/** 积分余额 */
			couponDetails.setPointbalance(shoppingMoney);
			/** 葛粮币余额 */
			couponDetails.setGoldmoneybalance(information.getCrmMoney());
			/** 消费卷余额 */
			couponDetails.setXfmoneybalance(information.getCoupon());
			/** 支出 */
			couponDetails.setPay(new BigDecimal(0));
			/** 备注 */
			couponDetails.setRedmin(ss.getName() + "的第" + ss.getPointNumber()
					+ "期次礼包释放,释放"+ss.getCoupon()+"消费卷");
			couponDetails.setChildId(ss.getChildId());
			/** 用户ID */
			couponDetails.setUserId(information.getId());
			giftsDao.saveOrUpdate(couponDetails);
		}
		shopingDetails.setIncome(new BigDecimal(ss.getGoldMoney()));
	
		/** 支出 */
		shopingDetails.setPay(new BigDecimal(0));

		/** 备注 */
		shopingDetails.setRedmin(ss.getName() + "的第" + ss.getPointNumber()
					+ "期次礼包释放");
	
		shopingDetails.setChildId(ss.getChildId());
		/** 消费卷余额 */
		shopingDetails.setXfmoneybalance(information.getCoupon());
		/** 用户ID */
		shopingDetails.setUserId(information.getId());
		giftsDao.saveOrUpdate(information);
		giftsDao.saveOrUpdate(giftsDetails);
		giftsDao.saveOrUpdate(shopingDetails);
		giftsDao.delete(ss);

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean DeleteGifts(String number) {
		try {
			String selectHql = "from Information t where t.number=?";
			List arguments = new ArrayList();
			arguments.add(number);
			List<Information> selectResult = (List<Information>) giftsDao
			.queryByHql(selectHql, arguments);
			Information info = null;
			if(selectResult!=null && selectResult.size()>0){
				info=selectResult.get(0);
			}
		
			if(info!=null){
				
				//删除推荐人的礼包信息
				String hql = "from GiftsDetails mr where mr.childId=?";
				List argumentsId = new ArrayList();
				argumentsId.add(info.getId());
				List<GiftsDetails> result = (List<GiftsDetails>) giftsDao
						.queryByHql(hql, argumentsId);
				if (result != null && result.size() > 0) {
					for (GiftsDetails gs : result) {
						giftsDao.delete(gs);
					}
				}
				String Sendhql = "from SendGiftsDetails md where md.childId=?";
				List<SendGiftsDetails> senResult = (List<SendGiftsDetails>) giftsDao
						.queryByHql(Sendhql, argumentsId);
				if (senResult != null && senResult.size() > 0) {
					for (SendGiftsDetails sd : senResult) {
						giftsDao.delete(sd);
					}
				}
				//删除本人的礼包信息
				String selfhql = "from GiftsDetails mr where mr.number=?";
				List argumentsSelfId = new ArrayList();
				argumentsSelfId.add(info.getNumber());
				List<GiftsDetails> selfResult = (List<GiftsDetails>) giftsDao
				.queryByHql(selfhql, argumentsSelfId);
				if (selfResult != null && selfResult.size() > 0) {
					for (GiftsDetails gs : selfResult) {
						giftsDao.delete(gs);
					}
				}
				
				String sendSelfhql = "from SendGiftsDetails md where md.number=?";
				List<SendGiftsDetails> sendSelfResult = (List<SendGiftsDetails>) giftsDao
						.queryByHql(sendSelfhql, argumentsSelfId);
				if (sendSelfResult != null && sendSelfResult.size() > 0) {
					for (SendGiftsDetails sd : sendSelfResult) {
						giftsDao.delete(sd);
					}
				}
				
			}
			
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	
	private String getDataNumber(int countnumber,BatchNoEnum batchNo){
		if(BatchNoEnum.FIRST.equals(batchNo)){
			return String.valueOf(countnumber)+"01";
		}else if(BatchNoEnum.SECOND.equals(batchNo)){
			return String.valueOf(countnumber)+"02";
		}else if(BatchNoEnum.THREE.equals(batchNo)){
			return String.valueOf(countnumber)+"03";
		}
		return null;
	}
}
