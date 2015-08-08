package com.member.beans.back;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeDto {
	
	private Integer id;
	
	/**会员登录ID */
	private String number;
	
	/**交易流水号*/
	private String tradeNo;
	
	/**充值类型*/
	private String chargeType;
	
	/**充值日期*/
	private Date chargeDate;
	/**充值金额*/
	private BigDecimal chageAmt;
	
	/**实际得到金额*/
	private BigDecimal realGetAmt;

	/**充值手续费*/
	private BigDecimal chargesurplus;
	
	/**充值状态 0：未处理，1：已处理*/
	private Integer status;
	
	/**充值种类*/
	private String chargeCategory;
	
	/**充值银行信息*/
	private String chageBackInfo;
	
	/**充值备注*/
	private String chageMessage;

	/**审核人*/
	private String userName;
	
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

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public Date getChargeDate() {
		return chargeDate;
	}

	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}

	public BigDecimal getChageAmt() {
		return chageAmt;
	}

	public void setChageAmt(BigDecimal chageAmt) {
		this.chageAmt = chageAmt;
	}

	public BigDecimal getRealGetAmt() {
		return realGetAmt;
	}

	public void setRealGetAmt(BigDecimal realGetAmt) {
		this.realGetAmt = realGetAmt;
	}

	public BigDecimal getChargesurplus() {
		return chargesurplus;
	}

	public void setChargesurplus(BigDecimal chargesurplus) {
		this.chargesurplus = chargesurplus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getChargeCategory() {
		return chargeCategory;
	}

	public void setChargeCategory(String chargeCategory) {
		this.chargeCategory = chargeCategory;
	}

	public String getChageBackInfo() {
		return chageBackInfo;
	}

	public void setChageBackInfo(String chageBackInfo) {
		this.chageBackInfo = chageBackInfo;
	}

	public String getChageMessage() {
		return chageMessage;
	}

	public void setChageMessage(String chageMessage) {
		this.chageMessage = chageMessage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
