package com.member.form.back;

public class ApplyServiceForm {
	
	private Integer id;
	
	/**提交会员（即报单中心）在information表的ID */
	private Integer submitId;
	
	/**提交会员（即报单中心）的登录ID */
	private String submitNumber;
	
	/**申请会员（即普通会员）在information表的ID */
	private Integer applyId;
	
	/**申请会员（即普通会员）的登录ID */
	private String applyNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubmitId() {
		return submitId;
	}

	public void setSubmitId(Integer submitId) {
		this.submitId = submitId;
	}

	public String getSubmitNumber() {
		return submitNumber;
	}

	public void setSubmitNumber(String submitNumber) {
		this.submitNumber = submitNumber;
	}

	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public String getApplyNumber() {
		return applyNumber;
	}

	public void setApplyNumber(String applyNumber) {
		this.applyNumber = applyNumber;
	}
	
}
