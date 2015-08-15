package com.member.beans.back;

import java.math.BigDecimal;
import java.util.Date;

public class WithdrawalsDto {
	
	
	private Integer id;
	
	private Integer memberId;
	
	/**会员登录ID */
	private String number;
	
	/**流水号 */
	private String tradeNo;
	
	/**日期 */
	private Date tradeDate;
	
	/**提现金额 */
	private BigDecimal tradeAmt;
	
	/**手续费 */
	private BigDecimal tradeFee;
	
	/**实际金额*/
	private BigDecimal realGetAmt;
	
	/**余额 */
	private BigDecimal balanceAmt;
	
	/**状态  0:未处理，1：已处理 */
	private String status;
	
	/**审核人*/
	private String userName;
	
	/**提现银行信息*/
	private String withdrawalsBackInfo;
	
	/**拒绝理由*/
	private String refuseReason;
	
	/**会员姓名*/
	private String numberName;
	
	/**会员联系电话*/
	private String numberPhone;
	
	/**充值银行名称*/
	private String bankName;
	
	/**充值银行地址*/
	private String bankAddress;
	
	/**充值银行卡号*/
	private String bankCardNo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public BigDecimal getTradeFee() {
		return tradeFee;
	}

	public void setTradeFee(BigDecimal tradeFee) {
		this.tradeFee = tradeFee;
	}

	public BigDecimal getRealGetAmt() {
		return realGetAmt;
	}

	public void setRealGetAmt(BigDecimal realGetAmt) {
		this.realGetAmt = realGetAmt;
	}

	public BigDecimal getBalanceAmt() {
		return balanceAmt;
	}

	public void setBalanceAmt(BigDecimal balanceAmt) {
		this.balanceAmt = balanceAmt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWithdrawalsBackInfo() {
		return withdrawalsBackInfo;
	}

	public void setWithdrawalsBackInfo(String withdrawalsBackInfo) {
		this.withdrawalsBackInfo = withdrawalsBackInfo;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public String getNumberName() {
		return numberName;
	}

	public void setNumberName(String numberName) {
		this.numberName = numberName;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	
	
	

}
