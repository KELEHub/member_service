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
import com.member.form.front.Transform;
import com.member.services.front.TransferService;
import com.member.util.CommonUtil;
@Service("TransferServiceImpl")
public class TransferServiceImpl implements TransferService{
	
	@Resource(name = "ParameterDaoImpl")
    ParameterDao parameterDao;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void transferManager(Information from, Information to, Transform form,SystemParameter parameter) {
		BigDecimal xm = from.getCrmMoney().subtract(new BigDecimal(form.getToGoldMoney()));
		BigDecimal cm = xm.subtract(parameter.getScoreTake());
		from.setCrmMoney(cm);
		BigDecimal dm = to.getCrmMoney().add(new BigDecimal(form.getToGoldMoney()));
		BigDecimal history = to.getCrmAccumulative().add(new BigDecimal(form.getToGoldMoney()));
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
		acFrom.setPay(new BigDecimal(form.getToGoldMoney()));
		/**备注 */
		acFrom.setRedmin("会员转账,转入账号"+to.getNumber()+",手续费" +parameter.getScoreTake());
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
		acTo.setIncome(new BigDecimal(form.getToGoldMoney()));
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

}
