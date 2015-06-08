package com.member.form.back;

public class TickForm {
	
	private String id;
	
	/**会员表对应ID */
	private String memberId;
	
	/**会员登录ID */
	private String memberNumber;
	
	/**留言标题 */
	private String title;
	
	/**留言内容 */
	private String content;

	/**留言时间 */
	private String ticklingDate;
	
	/**回复内容 */
	private String replyContent;
	
	/**回复时间 */
	private String replyDate;
	
	/**主题类型 */
	private String category;
	
	/**留言描述 */
	private String description;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTicklingDate() {
		return ticklingDate;
	}

	public void setTicklingDate(String ticklingDate) {
		this.ticklingDate = ticklingDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
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

	public String getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
