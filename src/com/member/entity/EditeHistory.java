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
@Table(name="EditeHistory")
@org.hibernate.annotations.Table(comment="账户修改历史记录",appliesTo="EditeHistory")
public class EditeHistory {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**会员编号 */
	@Column(name="numeber")
	private String numeber;
	
	/** createTime 创建时间 */
	@Column(name="createTime",nullable=true,unique=false,length=32)
	private Date createTime;
	
	/**备注 */
	@Column(name="remaind")
	private String remaind;
	
	/**操作人*/
	@Column(name="userId")
	private Integer userId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeber() {
		return numeber;
	}

	public void setNumeber(String numeber) {
		this.numeber = numeber;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemaind() {
		return remaind;
	}

	public void setRemaind(String remaind) {
		this.remaind = remaind;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	

}
