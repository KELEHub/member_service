package com.member.services.back.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.member.beans.back.enumData.KindDataEnum;
import com.member.beans.back.enumData.ProjectEnum;
import com.member.dao.AccountDetailsDao;
import com.member.dao.ChargeDao;
import com.member.dao.InformationDao;
import com.member.dao.ParameterDao;
import com.member.entity.AccountDetails;
import com.member.entity.Charge;
import com.member.entity.Information;
import com.member.entity.SystemParameter;
import com.member.form.ChargeOperForm;
import com.member.helper.BaseResult;
import com.member.helper.dao.impl.BaseDaoImpl;
import com.member.services.back.ChargeService;

@Service("ChargeServiceImpl")
@Transactional
public class ChargeServiceImpl extends BaseDaoImpl implements  ChargeService{

	@Resource(name = "ChargeDaoImpl")
	private  ChargeDao chargeDao;

	@Resource(name = "InformationDaoImpl")
	private InformationDao informationDao; 
	
	@Resource(name = "AccountDetailsDaoImpl")
	private AccountDetailsDao accountDetailsDao;
	
	@Resource(name = "ParameterDaoImpl")
	private ParameterDao parameterDao;
	
	@Override
	public List<Charge> getChargeList(ChargeOperForm form) {
		Map<String, Object> arguments = new HashMap<String, Object>();
		String roleQuery = "from Charge s where 1=1";
		if(form.getNumber()!=null && !"".equals(form.getNumber())){
			roleQuery+=" and s.number=:number ";
			arguments.put("number", form.getNumber());
		}
		if(form.getStatus()!=null){
			roleQuery+=" and s.status=:status ";
			arguments.put("status", form.getStatus());
		}
		List result = chargeDao.queryByHql(roleQuery, arguments);
		return result;
	}

	@Override
	public Charge getChargeDetailById(Integer id) {
		String roleQuery = "from Charge s where s.id=?";
		List result = chargeDao.queryByHql(roleQuery, id);
		return (Charge) result.get(0);
	}

	@Override
	public void updateOrderList(ChargeOperForm form) {
		Charge result = getChargeDetailById(form.getId());
		result.setStatus(1);
		chargeDao.update(result);
	}

	@Override
	public void saveOrderList(ChargeOperForm form) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrderList(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BaseResult<Void> agreeCharge(ChargeOperForm form) {
		BaseResult<Void> result = new BaseResult<Void>();
		String query = "from Charge s where status='0' and s.id=?";
		List queryResult = chargeDao.queryByHql(query, form.getId());
		if(queryResult==null || queryResult.size()==0){
			result.setSuccess(false);
			result.setMsg("充值申请数据异常.");
			return result;
		}
		
		//取得当前审核的充值记录项目.
		Charge singleResult = (Charge) queryResult.get(0);
		//提现金额
		BigDecimal tradeAmt = singleResult.getChageAmt();
		
		SystemParameter syspar = getSystemParameter();
		
		/**	积分充值最小金额: */
		BigDecimal scoreInMin = syspar.getScoreInMin();
		
		/**	积分充值手续费: */
		BigDecimal scoreInTake = syspar.getScoreInTake();
		
		if(tradeAmt.compareTo(scoreInMin)==-1){
			result.setSuccess(false);
			result.setMsg("低于单笔充值最低金额.");
			return result;
		}
		// 取得客户账户信息
		Information ifm = getActInfoByNumber(singleResult.getNumber());

		// 进行充值处理.
		// 1.增加会员账户普通积分
		BigDecimal shoppingMoney = ifm.getShoppingMoney();//普通积分
		BigDecimal afterShopingMoney = shoppingMoney.add(tradeAmt);
		// 1.增加会员账户服务积分
		BigDecimal repeatedMoey = ifm.getRepeatedMoney();//服务积分
		BigDecimal afterRepeatedMoey = repeatedMoey.add(tradeAmt);
	
		ifm.setShoppingMoney(afterShopingMoney);// 普通积分
		ifm.setRepeatedMoney(afterRepeatedMoey);//服务积分
		informationDao.update(ifm);

		// 2.更新充值信息的手续费，状态和实际金额。
		singleResult.setChargesurplus(scoreInTake);
		singleResult.setRealGetAmt(tradeAmt.subtract(scoreInTake));
		singleResult.setStatus(1);
		chargeDao.update(singleResult);

		// 3.插入账户明细表
		AccountDetails insertAccountDetails = new AccountDetails();
		/** 种类 */
		insertAccountDetails.setKindData(KindDataEnum.points);
		Calendar d1 = Calendar.getInstance();
		Date nowDate = d1.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String nowDateStr = format.format(nowDate);

		int day = d1.get(Calendar.DAY_OF_MONTH);
		String dateNumber = "";
		if (day <= 10) {
			dateNumber = "01";
		} else if (day <= 20) {
			dateNumber = "02";
		} else {
			dateNumber = "03";

		}
		/** 日期类别统计 */
		insertAccountDetails.setDateNumber(nowDateStr.substring(0, 6)
				+ dateNumber);
		/** 项目 */
		insertAccountDetails.setProject(ProjectEnum.recharge);
		/** 积分余额 */
		insertAccountDetails.setPointbalance(afterShopingMoney);
		/** 葛粮币余额 */
		insertAccountDetails.setGoldmoneybalance(ifm.getCrmMoney());
		/** 收入 */
		insertAccountDetails.setIncome(tradeAmt.subtract(scoreInTake));
		/** 支出 */
		insertAccountDetails.setPay(scoreInTake);
		/** 备注 */
		insertAccountDetails.setRedmin("充值 || " + nowDateStr);
		/** 用户ID */
		insertAccountDetails.setUserId(ifm.getId());
		/** createTime 创建时间 */
		insertAccountDetails.setCreateTime(new Date());
		accountDetailsDao.save(insertAccountDetails);

		result.setSuccess(true);
		result.setMsg("充值操作成功.");
		return result;
	}
	
	private Information getActInfoByNumber(String number){
		String hqlQuery = " from Information s where s.isActivate=1 and s.isLock=0 and s.number=?";
		List result = informationDao.queryByHql(hqlQuery, number);
		if(result!=null){
			return (Information) result.get(0);
		}else{
			return null;
		}
	}
	
	private SystemParameter getSystemParameter(){
		String hqlQuery = " from SystemParameter s";
		List result = parameterDao.queryByHql(hqlQuery);
		if(result!=null){
			return (SystemParameter) result.get(0);
		}else{
			return null;
		}
	}

}
