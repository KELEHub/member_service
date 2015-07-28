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
import com.member.services.back.ExcelExportService;
import com.member.util.CommonUtil;

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
	
	@Resource(name = "ExcelExportServiceImpl")
	private ExcelExportService excelExportService;
	
	@Override
	public List<Charge> getNoChargeList(String memeberNumber,
			int pageSize,int pageNumber) {
		Map<String, Object> arguments = new HashMap<String, Object>();
		String roleQuery = "from Charge s where status=0";
		if(memeberNumber!=null && !"".equals(memeberNumber)){
			roleQuery+=" and s.number=:number ";
			arguments.put("number", memeberNumber);
		}
		
		roleQuery = roleQuery+"  order by chargeDate desc";
		List result = chargeDao.queryByHql(roleQuery, pageNumber,pageSize,arguments);
		return result;
	}
	
	@Override
	public List<Charge> getChargeList(String memeberNumber,
			int pageSize,int pageNumber) {
		Map<String, Object> arguments = new HashMap<String, Object>();
		String roleQuery = "from Charge s where (status=1 or status=2)";
		if(memeberNumber!=null && !"".equals(memeberNumber)){
			roleQuery+=" and s.number=:number ";
			arguments.put("number", memeberNumber);
		}
		roleQuery = roleQuery+"  order by chargeDate desc";
		List result = chargeDao.queryByHql(roleQuery, pageNumber,pageSize,arguments);
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
	public BaseResult<Void> agreeCharge(ChargeOperForm form,String dealUserName) {
		BaseResult<Void> result = new BaseResult<Void>();
		String query = "from Charge s where status=0 and s.id=?";
		List queryResult = chargeDao.queryByHql(query, form.getId());
		if(queryResult==null || queryResult.size()==0){
			result.setSuccess(false);
			result.setMsg("充值申请数据异常.");
			return result;
		}
		
		//取得当前审核的充值记录项目.
		Charge singleResult = (Charge) queryResult.get(0);
		//提现金额
//		BigDecimal tradeAmt = singleResult.getChageAmt();
		
		
//		if(tradeAmt.compareTo(scoreInMin)==-1){
//			result.setSuccess(false);
//			result.setMsg("低于单笔充值最低金额.");
//			return result;
//		}
		//充值扣除收费费之后的实际金额
		BigDecimal chargeRealAmt = singleResult.getRealGetAmt();
		
		// 取得客户账户信息
		Information ifm = getActInfoByNumber(singleResult.getNumber());

		//增加客户葛粮币
		BigDecimal crmMoney = ifm.getCrmMoney();
		BigDecimal aftercrmMoney = crmMoney.add(chargeRealAmt);
		//增加葛粮币累计
		BigDecimal crmAccumulative = ifm.getCrmAccumulative();
		BigDecimal aftercrmAccumulative = crmAccumulative.add(chargeRealAmt);
		// 进行充值处理.
		ifm.setCrmMoney(aftercrmMoney);
		//葛粮币
		ifm.setCrmAccumulative(aftercrmAccumulative);
		//葛粮币累计
		informationDao.update(ifm);

		// 2.更新充值信息的手续费，状态和实际金额。
//		singleResult.setChargesurplus(scoreInTake);
//		singleResult.setRealGetAmt(chargeRealAmt);
		singleResult.setStatus(1);
		singleResult.setUserName(dealUserName);//处理这条数据的用户名
		chargeDao.update(singleResult);

		// 3.插入账户明细表
		AccountDetails insertAccountDetails = new AccountDetails();
		/** 种类 */
		insertAccountDetails.setKindData(KindDataEnum.goldmoney);
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
		} else if(day<=30){
			dateNumber = "03";
		}
		if(day==31){
			dateNumber="01";
		}
		
		/**流水号 */
		insertAccountDetails.setCountNumber(CommonUtil.getCountNumber());
		/** 日期类别统计 */
		insertAccountDetails.setDateNumber(nowDateStr.substring(0, 6)
				+ dateNumber);
		/** 项目 */
		insertAccountDetails.setProject(ProjectEnum.recharge);
		/** 积分余额 */
		insertAccountDetails.setPointbalance(ifm.getShoppingMoney());
		/** 葛粮币余额 */
		insertAccountDetails.setGoldmoneybalance(aftercrmMoney);
		/** 收入 */
		insertAccountDetails.setIncome(chargeRealAmt);
		/** 支出 */
		insertAccountDetails.setPay(singleResult.getChargesurplus());
		/** 备注 */
		insertAccountDetails.setRedmin("充值金额:"+singleResult.getChageAmt()+" || 手续费:"+singleResult.getChargesurplus()+" || 实际到账:"+chargeRealAmt+"|| 处理时间:" + nowDateStr);
		/** 用户ID */
		insertAccountDetails.setUserId(ifm.getId());
		/**用户登录ID */
		insertAccountDetails.setUserNumber(ifm.getNumber());
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

	@Override
	public BaseResult<Void> disAgreeCharge(ChargeOperForm form,
			String dealUserName) {
		BaseResult<Void> result = new BaseResult<Void>();
		
		String query = "from Charge s where status=0 and s.id=?";
		List queryResult = chargeDao.queryByHql(query, form.getId());
		if(queryResult==null || queryResult.size()==0){
			result.setSuccess(false);
			result.setMsg("充值申请数据异常.");
			return result;
		}
		
		//取得当前审核的充值记录项目.
		Charge singleResult = (Charge) queryResult.get(0);

		// 2.更新充值信息的手续费，状态和实际金额。
		singleResult.setStatus(2);
		singleResult.setRefuseReason(form.getRefuseReason());
		singleResult.setUserName(dealUserName);//处理这条数据的用户名
		chargeDao.update(singleResult);

		result.setSuccess(true);
		result.setMsg("拒绝成功.");
		return result;
	}
	
	public InputStream getExportRecords(String memeberNumber) throws Exception {
		List<Charge> list = getChargeExportRecord(memeberNumber);
		return excelExportService.exportCharge(list);
	}

	public List<Charge> getChargeExportRecord(String memeberNumber) {
		Map<String, Object> arguments = new HashMap<String, Object>();
		String query = "from Charge s where status=1 ";
		if(!"".equals(memeberNumber)){
			query+="and s.number=:number";
			arguments.put("number", memeberNumber);
		}

		List queryResult = chargeDao.queryByHql(query, arguments);
		return queryResult;
	}

	@Override
	public int countChargeList(String memeberNumber) {
		Map<String, Object> arguments = new HashMap<String, Object>();
		String roleQuery = "from Charge s where (status=1 or status=2)";
		if(memeberNumber!=null && !"".equals(memeberNumber)){
			roleQuery+=" and s.number=:number ";
			arguments.put("number", memeberNumber);
		}
		roleQuery = roleQuery+"  order by chargeDate desc";
		List result = chargeDao.queryByHql(roleQuery, arguments);
		if(result!=null && result.size()>0){
			return result.size();
		}
		return 0;
	}
	
	@Override
	public int countNoChargeList(String memeberNumber) {
		Map<String, Object> arguments = new HashMap<String, Object>();
		String roleQuery = "from Charge s where status=0";
		if(memeberNumber!=null && !"".equals(memeberNumber)){
			roleQuery+=" and s.number=:number ";
			arguments.put("number", memeberNumber);
		}
		roleQuery = roleQuery+"  order by chargeDate desc";
		List result = chargeDao.queryByHql(roleQuery, arguments);
		if(result!=null && result.size()>0){
			return result.size();
		}
		return 0;
	}
}
