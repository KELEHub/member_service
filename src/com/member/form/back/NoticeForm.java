package com.member.form.back;

import java.util.Date;

public class NoticeForm {

	private Integer id;
	
	/**管理员用户ID */
	private Integer userId;
	
	/**公告标题 */
	private String title;
	
	/**公告内容 */
	private String content;

	/**主题类型 ，包含公告、服务协议、常见问题*/
	private Integer category;
	
	/**公告日期 */
	private Date date;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
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

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
