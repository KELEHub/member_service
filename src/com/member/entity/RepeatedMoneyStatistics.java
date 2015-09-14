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
@Table(name="RepeatedMoneyStatistics")
@org.hibernate.annotations.Table(comment="极差积分统计表",appliesTo="RepeatedMoneyStatistics")
public class RepeatedMoneyStatistics {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**会员表ID，被激活账号*/
	@Column(name="declarationId")
	private Integer declarationId;
	
	/**会员登录ID */
	@Column(name="declarationNumber")
	private String declarationNumber;
	
	/**激活账号表ID，受益人*/
	@Column(name="declarationBenefitId")
	private Integer declarationBenefitId;
	
	/**激活账号登录ID */
	@Column(name="declarationBenefitNumber")
	private String declarationBenefitNumber;
	
	/**流水号 */
	@Column(name="serialNumber")
	private Integer serialNumber;
	
	/**日期类别统计 */
	@Column(name="dateNumber")
	private String dateNumber;
	
	/**状态，0为未发放，1为已发放*/
	@Column(name="state",columnDefinition="INT default 0")
	private Integer state;
	
	/** createTime 创建时间 */
	@Column(name="createTime",nullable=true,unique=false,length=32)
	private Date createTime;
	
	/**状态，0为正常，1为禁用*/
	@Column(name="dbUse",columnDefinition="INT default 0")
	private Integer dbUse;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDeclarationId() {
		return declarationId;
	}

	public void setDeclarationId(Integer declarationId) {
		this.declarationId = declarationId;
	}

	public String getDeclarationNumber() {
		return declarationNumber;
	}

	public void setDeclarationNumber(String declarationNumber) {
		this.declarationNumber = declarationNumber;
	}

	public Integer getDeclarationBenefitId() {
		return declarationBenefitId;
	}

	public void setDeclarationBenefitId(Integer declarationBenefitId) {
		this.declarationBenefitId = declarationBenefitId;
	}

	public String getDeclarationBenefitNumber() {
		return declarationBenefitNumber;
	}

	public void setDeclarationBenefitNumber(String declarationBenefitNumber) {
		this.declarationBenefitNumber = declarationBenefitNumber;
	}

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getDbUse() {
		return dbUse;
	}

	public void setDbUse(Integer dbUse) {
		this.dbUse = dbUse;
	}
	
}
