package com.member.form.front;

import java.math.BigDecimal;

public class MemberChargeApplyForm {
	
	private Integer id;
	
	/**会员登录ID */
	private String number;
	
	/**实际得到金额*/
	private BigDecimal chageAmt;
	
	/**充值银行信息*/
	private String chageBackInfo;
	
	/**充值备注*/
	private String chageMessage;
	
	/**会员二级密码*/
	private String protectPassword;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public BigDecimal getChageAmt() {
		return chageAmt;
	}

	public void setChageAmt(BigDecimal chageAmt) {
		this.chageAmt = chageAmt;
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
