package com.member.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity@DynamicUpdate(true)@DynamicInsert(true)
//@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,optimisticLock=org.hibernate.annotations.OptimisticLockType.VERSION)
@Table(name="Tickling")
@org.hibernate.annotations.Table(comment="会员留言",appliesTo="Tickling")
public class Tickling {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**会员表对应ID */
	@Column(name="memberId")
	private Integer memberId;
	
	/**会员登录ID */
	@Column(name="memberNumber")
	private String memberNumber;
	
	/**留言标题 */
	@Column(name="title")
	private String title;
	
	/**留言内容 */
	@Column(name="content")
	private String content;

	/**留言时间 */
	@Column(name="ticklingDate")
	private Date ticklingDate;
	
	/**回复内容 */
	@Column(name="replyContent")
	private String replyContent;
	
	/**回复时间 */
	@Column(name="replyDate")
	private Date replyDate;
	
	/**主题类型 */
	@Column(name="category")
	private Integer category;
	
	/**留言回复状态，0未回复，1已回复 */
	@Column(name="state")
	private Integer state;
	
	/**留言描述 */
	@Column(name="description")
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
