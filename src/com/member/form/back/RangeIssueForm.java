package com.member.form.back;

import java.math.BigDecimal;

import javax.persistence.Column;

public class RangeIssueForm {

	/**会员账号表ID*/
	@Column(name="userId")
	private Integer userId;
	
	/**会员账号登录ID*/
	@Column(name="userNumber")
	private String userNumber;
	
	/**可发放服务积分 */
	@Column(name="availableInt")
	private BigDecimal availableInt;
	
	/**年*/
	@Column(name="year")
	private String year;
	
	/**月*/
	@Column(name="month")
	private String month;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

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
