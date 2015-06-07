package com.member.form.back;


public class NoticeForm {

	private String id;
	
	/**管理员用户ID */
	private String userId;
	
	/**公告标题 */
	private String title;
	
	/**公告内容 */
	private String content;

	/**主题类型 ，包含公告、服务协议、常见问题*/
	private String category;
	
	/**公告日期 */
	private String date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
