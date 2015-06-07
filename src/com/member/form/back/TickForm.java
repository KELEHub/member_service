package com.member.form.back;

import java.util.Date;

public class TickForm {
	
	private Integer id;
	
	/**会员表对应ID */
	private Integer memberId;
	
	/**会员登录ID */
	private String memberNumber;
	
	/**留言标题 */
	private String title;
	
	/**留言内容 */
	private String content;

	/**留言时间 */
	private Date ticklingDate;
	
	/**回复内容 */
	private String replyContent;
	
	/**回复时间 */
	private Date replyDate;
	
	/**主题类型 */
	private Integer category;
	
	/**留言回复状态，0未回复，1已回复 */
	private Integer state;
	
	/**留言描述 */
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getTicklingDate() {
		return ticklingDate;
	}

	public void setTicklingDate(Date ticklingDate) {
		this.ticklingDate = ticklingDate;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
