package com.member.services.front.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.ChargeDao;
import com.member.dao.InformationDao;
import com.member.dao.WithdrawalsDao;
import com.member.entity.Charge;
import com.member.entity.Information;
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
	
	@Override
	public List<Charge> getMemberChargeInfoByUserName(String userName) {
		String roleQuery = "from Charge s where s.number=?";
		List result = chargeDao.queryByHql(roleQuery, userName);
		return result;
	}

	@Override
	public List<Withdrawals> getMemberWithdrawalsInfoByUserName(String userName) {
		String withdrawalsQuery = "from Withdrawals s where s.number=?";
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
		insertCharge.setChageAmt(form.getChageAmt());
		
		/**实际得到金额*/
		insertCharge.setRealGetAmt(new BigDecimal(0));

		/**充值手续费*/
		insertCharge.setChargesurplus(new BigDecimal(0));
		
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
		insertWithdrawals.setTradeAmt(form.getWithdrawalsAmt());
		
		/**手续费 */
//		insertWithdrawals.setTradeFee(new BigDecimal(0));
		
		/**实际金额*/
//		insertWithdrawals.setRealGetAmt(new BigDecimal(0));
		
		/**余额 */
//		insertWithdrawals.setBalanceAmt(new BigDecimal(0));
		
		/**状态  0:未处理，1：已处理 */
		insertWithdrawals.setStatus("0");
		
		/**审核人*/
//		insertWithdrawals.setuserName;
		
		/**提现银行信息*/
		insertWithdrawals.setWithdrawalsBackInfo(form.getWithdrawalsBackInfo());
		withdrawalsDao.save(insertWithdrawals);
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

}
