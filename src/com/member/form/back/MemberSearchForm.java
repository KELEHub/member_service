package com.member.form.back;

public class MemberSearchForm {

	private Integer id;
	/**
	 * 会员账号
	 */
	private String number;
	
	/**
	 * 推荐编号
	 */
	private String recommendNumber;
	
	/**
	 * 报单中心
	 */
	private String serviceNumber;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRecommendNumber() {
		return recommendNumber;
	}

	public void setRecommendNumber(String recommendNumber) {
		this.recommendNumber = recommendNumber;
	}

	public String getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
