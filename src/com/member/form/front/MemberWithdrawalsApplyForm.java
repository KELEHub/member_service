package com.member.form.front;

import java.math.BigDecimal;

public class MemberWithdrawalsApplyForm {

	private Integer id;
	
	/**会员登录ID */
	private String number;
	
	/**提现金额*/
	private BigDecimal withdrawalsAmt;
	
	/**提现银行信息*/
	private String withdrawalsBackInfo;
	
	/**会员二级密码*/
	private String protectPassword;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public BigDecimal getWithdrawalsAmt() {
		return withdrawalsAmt;
	}

	public void setWithdrawalsAmt(BigDecimal withdrawalsAmt) {
		this.withdrawalsAmt = withdrawalsAmt;
	}

	public String getWithdrawalsBackInfo() {
		return withdrawalsBackInfo;
	}

	public void setWithdrawalsBackInfo(String withdrawalsBackInfo) {
		this.withdrawalsBackInfo = withdrawalsBackInfo;
	}

	public String getProtectPassword() {
		return protectPassword;
	}

	public void setProtectPassword(String protectPassword) {
		this.protectPassword = protectPassword;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
