package com.member.services.back.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.member.beans.back.enumData.KindDataEnum;
import com.member.beans.back.enumData.ProjectEnum;
import com.member.dao.AccountDetailsDao;
import com.member.dao.InformationDao;
import com.member.dao.ManageUserRoleDao;
import com.member.dao.ParameterDao;
import com.member.dao.WithdrawalsDao;
import com.member.entity.AccountDetails;
import com.member.entity.Information;
import com.member.entity.ManageUserRoleHub;
import com.member.entity.SystemParameter;
import com.member.entity.Withdrawals;
import com.member.helper.BaseResult;
import com.member.services.back.ExcelExportService;
import com.member.services.back.WithdrawalsService;
import com.member.util.CommonUtil;

@Service("WithdrawalsServiceImpl")
public class WithdrawalsServiceImpl implements WithdrawalsService {

	@Resource(name = "ManageUserRoleDaoImpl")
	private ManageUserRoleDao manageUserRoleDao;
	
	@Resource(name = "WithdrawalsDaoImpl")
	private WithdrawalsDao withdrawalsDao;
	
	@Resource(name = "InformationDaoImpl")
	private InformationDao informationDao; 
	
	@Resource(name = "AccountDetailsDaoImpl")
	private AccountDetailsDao accountDetailsDao;
	
	@Resource(name = "ParameterDaoImpl")
	private ParameterDao parameterDao;
	
	@Resource(name = "ExcelExportServiceImpl")
	private ExcelExportService excelExportService;
	
	@Override
	public List<Withdrawals> getWithdrawalsRecordByMemberNumber(
			String memeberNumber,Integer currentUserId,String currentUserNm,
			int pageSize,int pageNumber) {
		
		//根据当前登录人的编号，取得角色信息
		String roleQuery = "from ManageUserRoleHub s where s.userId=?";
		List userRoleResult = manageUserRoleDao.queryByHql(roleQuery, currentUserId);
		if(userRoleResult==null){
			return null;
		}
		
		//超级管理员判断
		boolean isSuperAdmin=false;
		for(int i=0;i<userRoleResult.size();i++){
			ManageUserRoleHub userRole = (ManageUserRoleHub) userRoleResult.get(i);
			Integer roleId = userRole.getRoleId();
			if(roleId==1){//超级管理员角色编号
				isSuperAdmin = true;
			}
		}
		Map<String, Object> arguments = new HashMap<String, Object>();
//		(status='1' or status='2')
		String withdrawalsQuery = "from Withdrawals s where s.status<>'0' ";
		if(memeberNumber != null && !"".equals(memeberNumber)){
			withdrawalsQuery+="and s.number=:number";
			arguments.put("number", memeberNumber);
		}
		
		if(isSuperAdmin){//超级管理员查看所有的
			
		}else{//不是超级管理员，查看自己审核的
			withdrawalsQuery+="and s.userName=:userName";
			arguments.put("userName", currentUserNm);
		}
		withdrawalsQuery = withdrawalsQuery+"  order by tradeDate desc";
		
		List withdrawalsResult = withdrawalsDao.queryByHql(withdrawalsQuery,pageNumber,pageSize, arguments);

		return withdrawalsResult;
	}

	@Override
	public int countWithdrawalsRecordByMemberNumberData(String memeberNumber,Integer currentUserId,String currentUserNm) {
		//根据当前登录人的编号，取得角色信息
		String roleQuery = "from ManageUserRoleHub s where s.userId=?";
		List userRoleResult = manageUserRoleDao.queryByHql(roleQuery, currentUserId);
		if(userRoleResult==null){
			return 0;
		}
		
		//超级管理员判断
		boolean isSuperAdmin=false;
		for(int i=0;i<userRoleResult.size();i++){
			ManageUserRoleHub userRole = (ManageUserRoleHub) userRoleResult.get(i);
			Integer roleId = userRole.getRoleId();
			if(roleId==1){//超级管理员角色编号
				isSuperAdmin = true;
			}
		}
		Map<String, Object> arguments = new HashMap<String, Object>();
		
		String withdrawalsQuery = "from Withdrawals s where (status='1' or status='2') ";
		if(memeberNumber != null && !"".equals(memeberNumber)){
			withdrawalsQuery+="and s.number=:number";
			arguments.put("number", memeberNumber);
		}
		
		if(isSuperAdmin){//超级管理员查看所有的
			
		}else{//不是超级管理员，查看自己审核的
			withdrawalsQuery+="and s.userName=:userName";
			arguments.put("userName", currentUserNm);
		}
		withdrawalsQuery = withdrawalsQuery+"  order by tradeDate desc";
		List result = withdrawalsDao.queryByHql(withdrawalsQuery,arguments);
		if(result!=null){
			return result.size();
		}
		return 0;
	}
	
	@Override
	public List<Withdrawals> getNotDealWithdrawalsRecord(String memeberNumber,
			int pageSize,int pageNumber) {
		Map<String, Object> arguments = new HashMap<String, Object>();
		String withdrawalsQuery = "from Withdrawals s where status='0' ";
		if(memeberNumber != null && !"".equals(memeberNumber)){
			withdrawalsQuery+="and s.number=:number";
			arguments.put("number", memeberNumber);
		}
		withdrawalsQuery = withdrawalsQuery+"  order by tradeDate desc";
		List withdrawalsResult = withdrawalsDao.queryByHql(withdrawalsQuery,pageNumber,pageSize, arguments);
		return withdrawalsResult;
	}

	@Override
	public int countNotDealWithdrawalsRecordData(String memeberNumber) {
		Map<String, Object> arguments = new HashMap<String, Object>();
		String withdrawalsQuery = "from Withdrawals s where status='0' ";
		if(memeberNumber != null && !"".equals(memeberNumber)){
			withdrawalsQuery+="and s.number=:number";
			arguments.put("number", memeberNumber);
		}
		withdrawalsQuery = withdrawalsQuery+"  order by tradeDate desc";
		List result = withdrawalsDao.queryByHql(withdrawalsQuery,arguments);
		if(result!=null){
			return result.size();
		}
		return 0;
	}
	
	@Override
	public BaseResult<Void> agreewithdrawals(Integer id,String dealUserName) {
		BaseResult<Void> result = new BaseResult<Void>();
		String withdrawalsQuery = "from Withdrawals s where status='0' and s.id=?";
		List withdrawalsResult = withdrawalsDao.queryByHql(withdrawalsQuery, id);
		
		if(withdrawalsResult==null || withdrawalsResult.size()==0){
			result.setSuccess(false);
			result.setMsg("提现申请数据异常.");
			return result;
		}
		
		//取得当前审核的提现记录项目.
		Withdrawals singleResult = (Withdrawals) withdrawalsResult.get(0);
		
		//2.更新提现信息的手续费，状态和实际金额。
		singleResult.setUserName(dealUserName);
		singleResult.setStatus("1");
		withdrawalsDao.update(singleResult);
		
		result.setSuccess(true);
		result.setMsg("提现操作成功.");
		return result;
	}
	
	
	@Override
	public BaseResult<Void> agreewithdrawalswithnopoints(Integer id,String dealUserName) {
		BaseResult<Void> result = new BaseResult<Void>();
		String withdrawalsQuery = "from Withdrawals s where status='0' and s.id=?";
		List withdrawalsResult = withdrawalsDao.queryByHql(withdrawalsQuery, id);
		
		if(withdrawalsResult==null || withdrawalsResult.size()==0){
			result.setSuccess(false);
			result.setMsg("提现申请数据异常.");
			return result;
		}
		
		//取得当前审核的提现记录项目.
		Withdrawals singleResult = (Withdrawals) withdrawalsResult.get(0);
		//提现金额
		BigDecimal tradeAmt = singleResult.getTradeAmt();
		
		
//		SystemParameter syspar = getSystemParameter();
		
		//手续费
		BigDecimal goldTake = singleResult.getTradeFee();
		
//		//提现实际需要积分.
//		BigDecimal realWithDrawalsAmt = tradeAmt.add(goldTake);
		//取得客户账户信息
		Information ifm = getActInfo(singleResult.getMemberId());
		
		BigDecimal shoppingMoney = ifm.getShoppingMoney();//普通积分
//		BigDecimal repeatedMoey = ifm.getRepeatedMoney();//服务积分
		
		//可以提现的积分=积分-服务积分
//		BigDecimal catdoMoeyBd = shoppingMoney.subtract(repeatedMoey);
//		//判断积分是否够提现
//		if(catdoMoeyBd.compareTo(realWithDrawalsAmt)==-1){//积分小于提现金额
//			result.setSuccess(false);
//			result.setMsg("提现积分不足.");
//			return result;
//		}
		
		//进行提现处理.
		//1.扣除会员账户金额
		BigDecimal shoppingMoneyBd = shoppingMoney;
		BigDecimal afterShopingMoney =  shoppingMoneyBd.subtract(tradeAmt);
		ifm.setShoppingMoney(afterShopingMoney);//普通积分
		informationDao.update(ifm);
		
		//2.更新提现信息的手续费，状态和实际金额。
//		singleResult.setTradeFee(goldTake);
//		singleResult.setRealGetAmt(tradeAmt.subtract(goldTake));
		singleResult.setUserName(dealUserName);
		singleResult.setStatus("1");
		singleResult.setBalanceAmt(afterShopingMoney);
		withdrawalsDao.update(singleResult);
		
		//3.插入账户明细表
		AccountDetails insertAccountDetails = new AccountDetails();
		/**种类 */
		insertAccountDetails.setKindData(KindDataEnum.points);
		Calendar d1 = Calendar.getInstance();
		Date nowDate = d1.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String nowDateStr = format.format(nowDate);
		
		int day = d1.get(Calendar.DAY_OF_MONTH);
		String dateNumber="";
		if(day<=10){
			dateNumber="01";
		}else if(day<=20){
			dateNumber="02";
		}else if(day<=30){
			dateNumber="03";
		}
		if(day==31){
			dateNumber="01";
		}
		/**流水号 */
		insertAccountDetails.setCountNumber(CommonUtil.getCountNumber());
		/**日期类别统计 */
		insertAccountDetails.setDateNumber(nowDateStr.substring(0,6)+dateNumber);
		/**项目 */
		insertAccountDetails.setProject(ProjectEnum.pointcash);
		/**积分余额 */
		insertAccountDetails.setPointbalance(afterShopingMoney);
		/**葛粮币余额 */
		insertAccountDetails.setGoldmoneybalance(ifm.getCrmMoney());
		/**收入 */
		insertAccountDetails.setIncome(new BigDecimal(0));
		/**支出 */
		insertAccountDetails.setPay(tradeAmt);
		/**备注 */
		insertAccountDetails.setRedmin("提现金额:" + tradeAmt + "手续费:" + goldTake
				+ "实际到账金额:" + tradeAmt.subtract(goldTake) + "处理时间: || "
				+ nowDateStr);
		/**用户ID */
		insertAccountDetails.setUserId(ifm.getId());
		/**用户登录ID */
		insertAccountDetails.setUserNumber(ifm.getNumber());
		/** createTime 创建时间 */
		insertAccountDetails.setCreateTime(new Date());
		accountDetailsDao.save(insertAccountDetails);
		
		result.setSuccess(true);
		result.setMsg("提现操作成功.");
		return result;
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
	
	private Information getActInfo(Integer id){
		String hqlQuery = " from Information s where s.isActivate=1 and s.isLock=0 and s.id=?";
		List result = informationDao.queryByHql(hqlQuery, id);
		if(result!=null){
			return (Information) result.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public BaseResult<Void> disAgreewithdrawals(Integer id, String dealUserName,String refuseReason) {
		BaseResult<Void> result = new BaseResult<Void>();
		String withdrawalsQuery = "from Withdrawals s where status='0' and s.id=?";
		List withdrawalsResult = withdrawalsDao.queryByHql(withdrawalsQuery, id);
		
		if(withdrawalsResult==null || withdrawalsResult.size()==0){
			result.setSuccess(false);
			result.setMsg("提现申请数据异常.");
			return result;
		}
		Withdrawals singleResult = (Withdrawals) withdrawalsResult.get(0);
		BigDecimal tradeAmt = singleResult.getTradeAmt();
		// 取得客户账户信息
		Information ifm = getActInfo(singleResult.getMemberId());
		BigDecimal shoppingMoney = ifm.getShoppingMoney();// 普通积分
		BigDecimal refustBalance = shoppingMoney.add(tradeAmt);
		ifm.setShoppingMoney(refustBalance);//普通积分
		informationDao.update(ifm);
		
		//取得当前审核的提现记录项目.
	
		singleResult.setUserName(dealUserName);
		singleResult.setStatus("2");
		singleResult.setRefuseReason(refuseReason);
		singleResult.setBalanceAmt(refustBalance);
		withdrawalsDao.update(singleResult);
		
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
		insertAccountDetails.setProject(ProjectEnum.banckPoints);
		/** 积分余额 */
		insertAccountDetails.setPointbalance(refustBalance);
		/** 葛粮币余额 */
		insertAccountDetails.setGoldmoneybalance(ifm.getCrmMoney());
		/** 收入 */
		insertAccountDetails.setIncome(tradeAmt);
		/** 支出 */
		insertAccountDetails.setPay(new BigDecimal(0));
		/** 备注 */
		insertAccountDetails.setRedmin("提现金额:" + tradeAmt + "收入原因:提现拒绝，提现积分退回" + "处理时间: || "
				+ nowDateStr);
		/** 用户ID */
		insertAccountDetails.setUserId(ifm.getId());
		/** 用户登录ID */
		insertAccountDetails.setUserNumber(ifm.getNumber());
		/** createTime 创建时间 */
		insertAccountDetails.setCreateTime(new Date());
		accountDetailsDao.save(insertAccountDetails);
				
		result.setSuccess(true);
		result.setMsg("拒绝提现成功.");
		return result;
	}
	
	
	@Override
	public BaseResult<Void> disAgreewithdrawalsppp(Integer id, String dealUserName,String refuseReason) {
		BaseResult<Void> result = new BaseResult<Void>();
		String withdrawalsQuery = "from Withdrawals s where status='0' and s.id=?";
		List withdrawalsResult = withdrawalsDao.queryByHql(withdrawalsQuery, id);
		
		if(withdrawalsResult==null || withdrawalsResult.size()==0){
			result.setSuccess(false);
			result.setMsg("提现申请数据异常.");
			return result;
		}
		
		//取得当前审核的提现记录项目.
		Withdrawals singleResult = (Withdrawals) withdrawalsResult.get(0);
		singleResult.setUserName(dealUserName);
		singleResult.setStatus("2");
		singleResult.setRefuseReason(refuseReason);
		withdrawalsDao.update(singleResult);
		
		result.setSuccess(true);
		result.setMsg("拒绝提现成功.");
		return result;
	}

	@Override
	public InputStream getExportRecords(String memeberNumber) throws Exception {
		List<Withdrawals> list = getWithdrawalsExportRecord(memeberNumber);
		return excelExportService.exportWithdrawals(list);
	}
	
	public List<Withdrawals> getWithdrawalsExportRecord(String memeberNumber) {
		Map<String, Object> arguments = new HashMap<String, Object>();
		String withdrawalsQuery = "from Withdrawals s where s.status='1' ";
		if(!"".equals(memeberNumber)){
			withdrawalsQuery+="and s.number=:number";
			arguments.put("number", memeberNumber);
		}
		List withdrawalsResult = withdrawalsDao.queryByHql(withdrawalsQuery, arguments);
		return withdrawalsResult;
	}

	public List<Withdrawals> getNotDealWithdrawalsExportRecord(String memeberNumber) {
		Map<String, Object> arguments = new HashMap<String, Object>();
		String withdrawalsQuery = "from Withdrawals s where s.status='0' ";
		if(!"".equals(memeberNumber)){
			withdrawalsQuery+="and s.number=:number";
			arguments.put("number", memeberNumber);
		}
		List withdrawalsResult = withdrawalsDao.queryByHql(withdrawalsQuery, arguments);
		return withdrawalsResult;
	}
	
	@Override
	public InputStream getNotDealExportRecords(String memeberNumber)
			throws Exception {
		List<Withdrawals> list = getNotDealWithdrawalsExportRecord(memeberNumber);
		return excelExportService.exportWithdrawals(list);
	}

	@Override
	public BaseResult<Void> deletewithdrawals(Integer id) {

		BaseResult<Void> result = new BaseResult<Void>();
				
		String withdrawalsQuery = "from Withdrawals s where s.id=?";
		List withdrawalsResult = withdrawalsDao.queryByHql(withdrawalsQuery, id);
		
		if(withdrawalsResult==null || withdrawalsResult.size()==0){
			result.setSuccess(false);
			result.setMsg("提现申请数据异常.");
			return result;
		}
		
		Withdrawals singleResult = (Withdrawals) withdrawalsResult.get(0);
		withdrawalsDao.delete(singleResult);
		result.setSuccess(true);
		result.setMsg("删除成功.");
		return result;
	}

}
