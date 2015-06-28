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
@Table(name="t_withdrawals")
@org.hibernate.annotations.Table(comment="提现信息表",appliesTo="t_withdrawals")
public class Withdrawals {

	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**会员数据记录ID */
	@Column(name="memberId")
	private Integer memberId;
	
	/**会员登录ID */
	@Column(name="number")
	private String number;
	
	/**流水号 */
	@Column(name="tradeNo")
	private String tradeNo;
	
	/**日期 */
	@Column(name="tradeDate")
	private Date tradeDate;
	
	/**提现金额 */
	@Column(name="tradeAmt")
	private BigDecimal tradeAmt;
	
	/**手续费 */
	@Column(name="tradeFee")
	private BigDecimal tradeFee;
	
	/**实际金额*/
	@Column(name="realGetAmt")
	private BigDecimal realGetAmt;
	
	/**余额 */
	@Column(name="balanceAmt")
	private BigDecimal balanceAmt;
	
	/**状态  0:未处理，1：已处理 */
	@Column(name="status")
	private String status;
	
	/**审核人*/
	@Column(name="userName")
	private String userName;
	
	/**提现银行信息*/
	@Column(name="withdrawalsBackInfo")
	private String withdrawalsBackInfo;
	
	/**拒绝理由*/
	@Column(name="refuseReason")
	private String refuseReason;
	
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

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
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
}
