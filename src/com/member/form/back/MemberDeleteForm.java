package com.member.form.back;

public class MemberDeleteForm {

	private Integer id;
	
	/**会员登录ID */
	private String number;
	
	/**推荐人在本表中的表ID */
	private Integer recommendId;
	
	/**推荐人的登录ID */
	private String recommendNumber;
	
	/**是否是报单中心，1表示是报单中心，0表示普通会员 */
	private Integer isService;
	
	/**上级报单中心的表ID*/
	private Integer leaderServiceId;
	
	/**上级报单中心的登录ID */
	private String leaderServiceNumber;
	
	/**激活人在本表中的表ID */
	private Integer activateId;
	
	/**激活人的登录ID */
	private String activateNumber;

	public Integer getActivateId() {
		return activateId;
	}

	public void setActivateId(Integer activateId) {
		this.activateId = activateId;
	}

	public String getActivateNumber() {
		return activateNumber;
	}

	public void setActivateNumber(String activateNumber) {
		this.activateNumber = activateNumber;
	}

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

	public Integer getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(Integer recommendId) {
		this.recommendId = recommendId;
	}

	public String getRecommendNumber() {
		return recommendNumber;
	}

	public void setRecommendNumber(String recommendNumber) {
		this.recommendNumber = recommendNumber;
	}

	public Integer getIsService() {
		return isService;
	}

	public void setIsService(Integer isService) {
		this.isService = isService;
	}

	public Integer getLeaderServiceId() {
		return leaderServiceId;
	}

	public void setLeaderServiceId(Integer leaderServiceId) {
		this.leaderServiceId = leaderServiceId;
	}

	public String getLeaderServiceNumber() {
		return leaderServiceNumber;
	}

	public void setLeaderServiceNumber(String leaderServiceNumber) {
		this.leaderServiceNumber = leaderServiceNumber;
	}
	
}