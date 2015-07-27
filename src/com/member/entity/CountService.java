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
@Table(name="CountService")
@org.hibernate.annotations.Table(comment="报单统计表",appliesTo="CountService")
public class CountService {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	
	/**日期类别统计 */
	@Column(name="dateNumber")
	private String dateNumber;
	
	
	/**用户ID */
	@Column(name="userId")
	private Integer userId;
	
	/**用户登录ID */
	@Column(name="userNumber")
	private String userNumber;
	
	
	/**流水号 */
	@Column(name="countNumber")
	private Integer countNumber;
	
	/** createTime 创建时间 */
	@Column(name="createTime",nullable=true,unique=false,length=32)
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDateNumber() {
		return dateNumber;
	}

	public void setDateNumber(String dateNumber) {
		this.dateNumber = dateNumber;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public Integer getCountNumber() {
		return countNumber;
	}

	public void setCountNumber(Integer countNumber) {
		this.countNumber = countNumber;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	

}
