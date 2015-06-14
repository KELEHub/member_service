package com.member.entity;

import java.math.BigDecimal;
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
@Table(name="RepeatedMoneyStatistics")
@org.hibernate.annotations.Table(comment="极差积分统计表",appliesTo="RepeatedMoneyStatistics")
public class RepeatedMoneyStatistics {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**上级报单中心表ID*/
	@Column(name="superiorServiceId")
	private Integer superiorServiceId;
	
	/**上级报单中心登录ID */
	@Column(name="superiorServiceNumber")
	private String superiorServiceNumber;
	
	/**下级报单中心表ID*/
	@Column(name="juniorServiceId")
	private Integer juniorServiceId;
	
	/**下级报单中心登录ID */
	@Column(name="juniorServiceNumber")
	private String juniorServiceNumber;
	
	/**服务积分数量 */
	@Column(name="scoreService",columnDefinition="NUMERIC(20,2) default 0")
	private BigDecimal scoreService;
	
	/**日期类别统计 */
	@Column(name="dateNumber")
	private String dateNumber;
	
	/** createTime 创建时间 */
	@Column(name="createTime",nullable=true,unique=false,length=32)
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSuperiorServiceId() {
		return superiorServiceId;
	}

	public void setSuperiorServiceId(Integer superiorServiceId) {
		this.superiorServiceId = superiorServiceId;
	}

	public String getSuperiorServiceNumber() {
		return superiorServiceNumber;
	}

	public void setSuperiorServiceNumber(String superiorServiceNumber) {
		this.superiorServiceNumber = superiorServiceNumber;
	}

	public Integer getJuniorServiceId() {
		return juniorServiceId;
	}

	public void setJuniorServiceId(Integer juniorServiceId) {
		this.juniorServiceId = juniorServiceId;
	}

	public String getJuniorServiceNumber() {
		return juniorServiceNumber;
	}

	public void setJuniorServiceNumber(String juniorServiceNumber) {
		this.juniorServiceNumber = juniorServiceNumber;
	}

	public BigDecimal getScoreService() {
		return scoreService;
	}

	public void setScoreService(BigDecimal scoreService) {
		this.scoreService = scoreService;
	}

	public String getDateNumber() {
		return dateNumber;
	}

	public void setDateNumber(String dateNumber) {
		this.dateNumber = dateNumber;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
