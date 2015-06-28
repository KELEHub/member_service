package com.member.services.front.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.beans.back.enumData.KindDataEnum;
import com.member.beans.back.enumData.ProjectEnum;
import com.member.dao.ParameterDao;
import com.member.entity.AccountDetails;
import com.member.entity.Information;
import com.member.entity.SystemParameter;
import com.member.services.front.TransferService;
import com.member.util.CommonUtil;
@Service("TransferServiceImpl")
public class TransferServiceImpl implements TransferService{
	
	@Resource(name = "ParameterDaoImpl")
    ParameterDao parameterDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void transferManager(Information from, Information to,BigDecimal goldValue,SystemParameter parameter) {
		BigDecimal xm = from.getCrmMoney().subtract(goldValue);
		BigDecimal cm = xm.subtract(goldValue.multiply(parameter.getScoreTake()));
		from.setCrmMoney(cm);
		BigDecimal dm = to.getCrmMoney().add(goldValue);
		BigDecimal history = to.getCrmAccumulative().add(goldValue);
		to.setCrmMoney(dm);
		to.setCrmAccumulative(history);
		AccountDetails acFrom = new AccountDetails();
		acFrom.setUserNumber(from.getNumber());
		acFrom.setCreateTime(new Date());
		acFrom.setKindData(KindDataEnum.goldmoney);
		acFrom.setCountNumber(CommonUtil.getCountNumber());
		
		/**日期类别统计 */
		acFrom.setDateNumber(CommonUtil.getDateNumber());
		acFrom.setProject(ProjectEnum.tootherman);
		/**积分余额 */
		acFrom.setPointbalance(from.getShoppingMoney());
		/**葛粮币余额 */
		acFrom.setGoldmoneybalance(cm);
		/**收入 */
		acFrom.setIncome(new BigDecimal(0));
		/**支出 */
		acFrom.setPay(goldValue);
		/**备注 */
		acFrom.setRedmin("会员转账,转入账号"+to.getNumber()+",手续费" +goldValue.multiply(parameter.getScoreTake()));
		/**用户ID */
		acFrom.setUserId(from.getId());
		AccountDetails acTo = new AccountDetails();
		acTo.setUserNumber(to.getNumber());
		acTo.setCreateTime(new Date());
		acTo.setKindData(KindDataEnum.goldmoney);
		acTo.setCountNumber(CommonUtil.getCountNumber());
		
		/**日期类别统计 */
		acTo.setDateNumber(CommonUtil.getDateNumber());
		acTo.setProject(ProjectEnum.tootherman);
		/**积分余额 */
		acTo.setPointbalance(to.getShoppingMoney());
		/**葛粮币余额 */
		acTo.setGoldmoneybalance(dm);
		/**收入 */
		acTo.setIncome(goldValue);
		/**支出 */
		acTo.setPay(new BigDecimal(0));
		/**备注 */
		acTo.setRedmin("会员转账,来源账号"+from.getNumber());
		/**用户ID */
		acTo.setUserId(to.getId());
		parameterDao.saveOrUpdate(from);
		parameterDao.saveOrUpdate(to);
		parameterDao.saveOrUpdate(acFrom);
		parameterDao.saveOrUpdate(acTo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void convertManager(Information info, BigDecimal goldValue,
			SystemParameter parameter) {
		BigDecimal xm = info.getShoppingMoney().subtract(goldValue);
		BigDecimal cm = xm.subtract(goldValue.multiply(parameter.getGlbTake()));
		BigDecimal midCrm = info.getCrmMoney();
		BigDecimal addcermoney = info.getCrmMoney().add(goldValue);
		BigDecimal history = info.getCrmAccumulative().add(goldValue);
		info.setShoppingMoney(cm);
		info.setCrmAccumulative(history);
		info.setCrmMoney(addcermoney);
		AccountDetails acFrom = new AccountDetails();
		acFrom.setUserNumber(info.getNumber());
		acFrom.setCreateTime(new Date());
		acFrom.setKindData(KindDataEnum.points);
		acFrom.setCountNumber(CommonUtil.getCountNumber());
		
		/**日期类别统计 */
		acFrom.setDateNumber(CommonUtil.getDateNumber());
		acFrom.setProject(ProjectEnum.togoldmoneycut);
		/**积分余额 */
		acFrom.setPointbalance(cm);
		/**葛粮币余额 */
		acFrom.setGoldmoneybalance(midCrm);
		/**收入 */
		acFrom.setIncome(new BigDecimal(0));
		/**支出 */
		acFrom.setPay(goldValue);
		/**备注 */
		acFrom.setRedmin("积分转换葛粮币减少"+",手续费" +goldValue.multiply(parameter.getGlbTake()));
		/**用户ID */
		acFrom.setUserId(info.getId());
		AccountDetails acTo = new AccountDetails();
		acTo.setUserNumber(info.getNumber());
		acTo.setCreateTime(new Date());
		acTo.setKindData(KindDataEnum.goldmoney);
		acTo.setCountNumber(CommonUtil.getCountNumber());
		
		/**日期类别统计 */
		acTo.setDateNumber(CommonUtil.getDateNumber());
		acTo.setProject(ProjectEnum.frompointsadd);
		/**积分余额 */
		acTo.setPointbalance(cm);
		/**葛粮币余额 */
		acTo.setGoldmoneybalance(addcermoney);
		/**收入 */
		acTo.setIncome(goldValue);
		/**支出 */
		acTo.setPay(new BigDecimal(0));
		/**备注 */
		acTo.setRedmin("积分转换葛粮币增加");
		/**用户ID */
		acTo.setUserId(info.getId());
		parameterDao.saveOrUpdate(acFrom);
		parameterDao.saveOrUpdate(acTo);
		parameterDao.saveOrUpdate(info);
	}

}
