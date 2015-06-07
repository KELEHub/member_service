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
@Table(name="LimitDeclaration")
@org.hibernate.annotations.Table(comment="每周限制报单数量表",appliesTo="LimitDeclaration")
public class LimitDeclaration {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	/**最大报单额 */
	@Column(name="maxDeclaration")
	private Integer maxDeclaration;
	/**	系统数据 */
	@Column(name="systemData")
	private String systemData;
	/** createTime 创建时间 */
	@Column(name="createTime",nullable=true,unique=false,length=32)
	private Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMaxDeclaration() {
		return maxDeclaration;
	}
	public void setMaxDeclaration(Integer maxDeclaration) {
		this.maxDeclaration = maxDeclaration;
	}
	public String getSystemData() {
		return systemData;
	}
	public void setSystemData(String systemData) {
		this.systemData = systemData;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
