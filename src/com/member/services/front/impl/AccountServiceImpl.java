package com.member.services.front.impl;

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
import com.member.dao.WithdrawalsDao;
import com.member.entity.AccountDetails;
import com.member.entity.Charge;
import com.member.entity.Information;
import com.member.entity.SystemParameter;
import com.member.entity.Withdrawals;
import com.member.form.front.MemberChargeApplyForm;
import com.member.form.front.MemberWithdrawalsApplyForm;
import com.member.helper.BaseResult;
import com.member.services.front.AccountService;
import com.member.util.CommonUtil;

@Service("AccountServiceImpl")
@Transactional
public class AccountServiceImpl implements AccountService{
	
	@Resource(name = "ChargeDaoImpl")
	private  ChargeDao chargeDao;
	
	@Resource(name = "WithdrawalsDaoImpl")
	private WithdrawalsDao withdrawalsDao;
	
	@Resource(name = "InformationDaoImpl")
	private InformationDao informationDao;
	
	@Resource(name = "ParameterDaoImpl")
	private ParameterDao parameterDao;
	
	@Resource(name = "AccountDetailsDaoImpl")
	private AccountDetailsDao accountDetailsDao;
	
	@Override
	public List<Charge> getMemberChargeInfoByUserName(String userName) {
		String roleQuery = "from Charge s where s.number=?   order by chargeDate desc";
		List result = chargeDao.queryByHql(roleQuery, userName);
		return result;
	}

	@Override
	public List<Withdrawals> getMemberWithdrawalsInfoByUserName(String userName) {
		String withdrawalsQuery = "from Withdrawals s where s.number=?  order by tradeDate desc";
		List withdrawalsResult = withdrawalsDao.queryByHql(withdrawalsQuery, userName);
		return withdrawalsResult;
	}

	@Override
	public BaseResult<Void> doAccCharge(MemberChargeApplyForm form) {
		BaseResult<Void> result = new BaseResult<Void>();
		
		//验证用户的二级密码是否正确
		boolean isRightPwd = checkProtectPassword(form.getNumber(), form.getProtectPassword());
		if(!isRightPwd){
			result.setMsg("您输入的二级密码不正确");
			result.setSuccess(false);
			return result;
		}
		
		//充值的判断
		BigDecimal tradeAmt = form.getChageAmt();
		
		//取得系统参数
		SystemParameter syspar = getSystemParameter();

		
		/**	积分充值手续费: */
		BigDecimal scoreInTakeRate = syspar.getScoreInTake();
		//百分比计算手续费
		BigDecimal scoreInTake=scoreInTakeRate.multiply(tradeAmt);
		
		/**	积分充值最小金额: */
		BigDecimal scoreInMin = syspar.getScoreInMin();
		
		if(tradeAmt.compareTo(scoreInMin)==-1){
			result.setSuccess(false);
			result.setMsg("低于单笔充值最低金额.");
			return result;
		}
		
		//将充值信息保存到充值信息表中
		Charge insertCharge = new Charge();
		/**会员登录ID */
		insertCharge.setNumber(form.getNumber());
		
		/**交易流水号*/
		insertCharge.setTradeNo(CommonUtil.getCountNumber()+"");
		
		/**充值类型*/
		insertCharge.setChargeType("葛粮币充值");
		/**充值日期*/
		insertCharge.setChargeDate(new Date());
		
		/**充值金额*/
		insertCharge.setChageAmt(tradeAmt);
		
		/**实际得到金额*/
		insertCharge.setRealGetAmt(tradeAmt.subtract(scoreInTake));

		/**充值手续费*/
		insertCharge.setChargesurplus(scoreInTake);
		
		/**充值状态 0：未处理，1：已处理*/
		insertCharge.setStatus(0);
		
		/**充值种类*/
		insertCharge.setChargeCategory("葛粮币");
			
		/**充值银行信息*/
		insertCharge.setChageBackInfo(form.getChageBackInfo());
		
		/**充值备注*/
		insertCharge.setChageMessage(form.getChageMessage());
		chargeDao.save(insertCharge);
		//充值申请成功的时候.
		result.setMsg("提交充值申请成功，请耐性等待处理.");
		result.setSuccess(true);
		return result;
	}

	@Override
	public Charge getChargeDetailById(Integer id) {
		String roleQuery = "from Charge s where s.id=?";
		List result = chargeDao.queryByHql(roleQuery, id);
		return (Charge) result.get(0);
	}
	
	@Override
	public BaseResult<Void> doAccWithdrawals(MemberWithdrawalsApplyForm form) {
		BaseResult<Void> result = new BaseResult<Void>();
		//验证用户的二级密码是否正确
		boolean isRightPwd = checkProtectPassword(form.getNumber(), form.getProtectPassword());
		if(!isRightPwd){
			result.setMsg("您输入的二级密码不正确");
			result.setSuccess(false);
			return result;
		}
		
		//提现的业务判断
		// 提现金额
		BigDecimal tradeAmt = form.getWithdrawalsAmt();
		SystemParameter syspar = getSystemParameter();
		
		String goldFlg = syspar.getGoldFlg();
		if("close".equals(goldFlg) || "".equals(goldFlg)){
			result.setSuccess(false);
			result.setMsg("现在不允许提现申请,请联系平台确认.");
			return result;
		}
		//邓明亮修改
		if(CommonUtil.getDay()!=2 && CommonUtil.getDay()!=22 && CommonUtil.getDay()!=12){
			result.setSuccess(false);
			result.setMsg("现在不是提现申请日,不能进行提现申请.");
			return result;
		}
		
		//判断会员是否有提现，如果有提现，则不允许再提交提现申请
		Withdrawals singleWith = getWithdrawalsByNumber(form.getNumber());
		if(singleWith!=null){
			result.setSuccess(false);
			result.setMsg("您还有未处理的提现申请，暂时不能提现，请联系管理员处理您以前的申请.");
			return result;
		}
		
		BigDecimal goldMax = syspar.getGoldMax();
		BigDecimal goldMin = syspar.getGoldMin();

		if (tradeAmt.compareTo(goldMax) == 1) {
			result.setSuccess(false);
			result.setMsg("超过单笔提现最大金额.");
			return result;
		}

		if (tradeAmt.compareTo(goldMin) == -1) {
			result.setSuccess(false);
			result.setMsg("低于单笔提现最低金额.");
			return result;
		}
		
		/**提现手续费 */
		BigDecimal goldTakeRate = syspar.getGoldTake();
		BigDecimal goldTake = goldTakeRate.multiply(tradeAmt);
		
/*		//提现实际需要积分.
		BigDecimal realWithDrawalsAmt = tradeAmt.add(goldTake);*/
		
		//取得客户账户信息
		Information ifm = getActInfo(form.getId());

		BigDecimal shoppingMoney = ifm.getShoppingMoney();//普通积分
		
		//可以提现的积分=积分
		BigDecimal catdoMoeyBd = shoppingMoney;
		
		//判断积分是否够提现
		if(catdoMoeyBd.compareTo(tradeAmt)==-1){//积分小于提现金额
			result.setSuccess(false);
			result.setMsg("提现积分不足.");
			return result;
		}
		
		//将提现申请保存到申请表中.
		Withdrawals insertWithdrawals = new Withdrawals();
		/**会员数据记录ID */
		insertWithdrawals.setMemberId(form.getId());
		
		/**会员登录ID */
		insertWithdrawals.setNumber(form.getNumber());
		
		/**流水号 */
		insertWithdrawals.setTradeNo(CommonUtil.getCountNumber()+"");
		
		/**日期 */
		insertWithdrawals.setTradeDate(new Date());
		
		/**提现金额 */
		insertWithdrawals.setTradeAmt(tradeAmt);
		
		/**手续费 */
		insertWithdrawals.setTradeFee(goldTake);
		
		/**实际金额*/
		insertWithdrawals.setRealGetAmt(tradeAmt.subtract(goldTake));
		
		/**余额 */
		insertWithdrawals.setBalanceAmt(shoppingMoney.subtract(tradeAmt));
		
		/**状态  0:未处理，1：已处理 */
		insertWithdrawals.setStatus("0");
		
		/**审核人*/
//		insertWithdrawals.setuserName;
		
		/**提现银行信息*/
		insertWithdrawals.setWithdrawalsBackInfo(form.getWithdrawalsBackInfo());
		withdrawalsDao.save(insertWithdrawals);
		
		//1.扣除会员账户金额
		BigDecimal shoppingMoneyBd = shoppingMoney;
		BigDecimal afterShopingMoney =  shoppingMoneyBd.subtract(tradeAmt);
		ifm.setShoppingMoney(afterShopingMoney);//普通积分
		informationDao.update(ifm);
		
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
		} else if (day <= 30) {
			dateNumber = "03";
		}
		if (day == 31) {
			dateNumber = "01";
		}
		/** 流水号 */
		insertAccountDetails.setCountNumber(CommonUtil.getCountNumber());
		/** 日期类别统计 */
		insertAccountDetails.setDateNumber(nowDateStr.substring(0, 6)
				+ dateNumber);
		/** 项目 */
		insertAccountDetails.setProject(ProjectEnum.pointcash);
		/** 积分余额 */
		insertAccountDetails.setPointbalance(afterShopingMoney);
		/** 葛粮币余额 */
		insertAccountDetails.setGoldmoneybalance(ifm.getCrmMoney());
		/** 收入 */
		insertAccountDetails.setIncome(new BigDecimal(0));
		/** 支出 */
		insertAccountDetails.setPay(tradeAmt);
		/** 备注 */
		insertAccountDetails.setRedmin("提现金额:" + tradeAmt + "手续费:" + goldTake
				+ "实际到账金额:" + tradeAmt.subtract(goldTake) + "申请时间: || "
				+ nowDateStr);
		/** 用户ID */
		insertAccountDetails.setUserId(ifm.getId());
		/** 用户登录ID */
		insertAccountDetails.setUserNumber(ifm.getNumber());
		/** createTime 创建时间 */
		insertAccountDetails.setCreateTime(new Date());
		
		accountDetailsDao.save(insertAccountDetails);
				
		//充值申请成功的时候.
		result.setMsg("提交提现申请成功，请耐性等待处理.");
		result.setSuccess(true);
		return result;
	}
	
	private boolean checkProtectPassword(String userName,String password){
		Map<String, Object> arguments = new HashMap<String, Object>();
		arguments.put("number", userName);
		arguments.put("protectPassword", password);
		String hql = "from Information ifm where ifm.number=:number and ifm.protectPassword=:protectPassword";
		
		List<Information> checkResult = (List<Information>) informationDao.queryByHql(hql, arguments);
		if(checkResult!=null && checkResult.size()>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Withdrawals getWithdrawalsDetailById(Integer id) {
		String withdrawalsQuery = "from Withdrawals s where s.id=?";
		List withdrawalsResult = withdrawalsDao.queryByHql(withdrawalsQuery, id);
		if(withdrawalsResult!=null && withdrawalsResult.size()>0){
			return (Withdrawals) withdrawalsResult.get(0);
		}else{
			return null;
		}
	}
	
	public Withdrawals getWithdrawalsByNumber(String number) {
		String withdrawalsQuery = "from Withdrawals s where s.number=? and s.status='0'";
		List withdrawalsResult = withdrawalsDao.queryByHql(withdrawalsQuery, number);
		if(withdrawalsResult!=null && withdrawalsResult.size()>0){
			return (Withdrawals) withdrawalsResult.get(0);
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
	
	private Information getActInfo(Integer id){
		String hqlQuery = " from Information s where s.isActivate=1 and s.isLock=0 and s.id=?";
		List result = informationDao.queryByHql(hqlQuery, id);
		if(result!=null){
			return (Information) result.get(0);
		}else{
			return null;
		}
	}
}
