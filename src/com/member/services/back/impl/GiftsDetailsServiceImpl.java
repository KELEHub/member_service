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
			return result.get(0).toString();
		}

		return null;
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
		AccountDetails shopingDetails = new AccountDetails();
		shopingDetails.setUserNumber(ss.getNumber());
		shopingDetails.setCreateTime(new Date());
		shopingDetails.setKindData(KindDataEnum.points);
		/** 日期类别统计 */
		shopingDetails.setDateNumber(ss.getDateNumber());
		shopingDetails.setProject(ProjectEnum.fromgifts);
		shopingDetails.setCountNumber(CommonUtil.getCountNumber());
		/** 积分余额 */
		shopingDetails.setPointbalance(shoppingMoney);
		/** 葛粮币余额 */
		shopingDetails.setGoldmoneybalance(information.getCrmMoney());
		/** 收入 */
		shopingDetails.setIncome(new BigDecimal(ss.getGoldMoney()));
		/** 支出 */
		shopingDetails.setPay(new BigDecimal(0));

		/** 备注 */
		shopingDetails.setRedmin(ss.getName() + "的第" + ss.getPointNumber()
				+ "期次礼包释放");
		shopingDetails.setChildId(ss.getChildId());
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
			}
			
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}
