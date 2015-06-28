package com.member.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name="t_charge")
@org.hibernate.annotations.Table(comment="充值信息",appliesTo="t_charge")
public class Charge {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**会员登录ID */
	@Column(name="number")
	private String number;
	
	/**交易流水号*/
	@Column(name="tradeNo")
	private String tradeNo;
	
	/**充值类型*/
	@Column(name="chargeType")
	private String chargeType;
	/**充值日期*/
	@Column(name="chargeDate")
	private Date chargeDate;
	
	/**充值金额*/
	@Column(name="chageAmt")
	private BigDecimal chageAmt;
	
	/**实际得到金额*/
	@Column(name="realGetAmt")
	private BigDecimal realGetAmt;

	/**充值手续费*/
	@Column(name="chargesurplus")
	private BigDecimal chargesurplus;
	
	/**充值状态 0：未处理，1：已处理*/
	@Column(name="status")
	private Integer status;
	
	/**充值种类*/
	@Column(name="chargeCategory")
	private String chargeCategory;
	
	/**充值银行信息*/
	@Column(name="chageBackInfo")
	private String chageBackInfo;
	
	/**充值备注*/
	@Column(name="chageMessage")
	private String chageMessage;

	/**审核人*/
	@Column(name="userName")
	private String userName;
	
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
}
