package com.member.form.back;

import java.math.BigDecimal;

import javax.persistence.Column;

public class RangeIssueForm {

	/**会员账号登录ID*/
	@Column(name="userNumber")
	private String userNumber;
	
	/**可发放服务积分 */
	@Column(name="availableInt")
	private BigDecimal availableInt;
	
	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public BigDecimal getAvailableInt() {
		return availableInt;
	}

	public void setAvailableInt(BigDecimal availableInt) {
		this.availableInt = availableInt;
	}

}
