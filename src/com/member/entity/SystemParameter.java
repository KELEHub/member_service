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
@Table(name="SystemParameter")
@org.hibernate.annotations.Table(comment="系统参数表",appliesTo="SystemParameter")
public class SystemParameter {
	
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	/**天数 */
	@Column(name="dayCount")
	private Integer dayCount;
	/**是否体现 */
	@Column(name="goldFlg")
	private String goldFlg;
	/**提现最高限额 */
	@Column(name="goldMax")
	private BigDecimal goldMax;
	/**提现最小限额 */
	@Column(name="goldMin")
	private BigDecimal goldMin;
	/**提现手续费 */
	@Column(name="goldTake")
	private BigDecimal goldTake;
	/**	积分转账最大金额 */
	@Column(name="scoreMax")
	private BigDecimal scoreMax;
	/**	积分转账最小金额 */
	@Column(name="scoreMin")
	private BigDecimal scoreMin;
	/**	积分转账手续费 */
	@Column(name="scoreTake")
	private BigDecimal scoreTake;
	/**	葛粮币转换最小金额 */
	@Column(name="glbMin")
	private BigDecimal glbMin;
	/**	将葛粮币转换手续费: */
	@Column(name="glbTake")
	private BigDecimal glbTake;
	/**	积分充值最小金额: */
	@Column(name="scoreInMin")
	private BigDecimal scoreInMin;
	/**	积分充值手续费: */
	@Column(name="scoreInTake")
	private BigDecimal scoreInTake;
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
	public Integer getDayCount() {
		return dayCount;
	}
	public void setDayCount(Integer dayCount) {
		this.dayCount = dayCount;
	}
	public String getGoldFlg() {
		return goldFlg;
	}
	public void setGoldFlg(String goldFlg) {
		this.goldFlg = goldFlg;
	}
	public BigDecimal getGoldMax() {
		return goldMax;
	}
	public void setGoldMax(BigDecimal goldMax) {
		this.goldMax = goldMax;
	}
	public BigDecimal getGoldMin() {
		return goldMin;
	}
	public void setGoldMin(BigDecimal goldMin) {
		this.goldMin = goldMin;
	}
	public BigDecimal getGoldTake() {
		return goldTake;
	}
	public void setGoldTake(BigDecimal goldTake) {
		this.goldTake = goldTake;
	}
	public BigDecimal getScoreMax() {
		return scoreMax;
	}
	public void setScoreMax(BigDecimal scoreMax) {
		this.scoreMax = scoreMax;
	}

	public BigDecimal getScoreMin() {
		return scoreMin;
	}
	public void setScoreMin(BigDecimal scoreMin) {
		this.scoreMin = scoreMin;
	}
	public BigDecimal getScoreTake() {
		return scoreTake;
	}
	public void setScoreTake(BigDecimal scoreTake) {
		this.scoreTake = scoreTake;
	}
	public BigDecimal getGlbMin() {
		return glbMin;
	}
	public void setGlbMin(BigDecimal glbMin) {
		this.glbMin = glbMin;
	}
	public BigDecimal getGlbTake() {
		return glbTake;
	}
	public void setGlbTake(BigDecimal glbTake) {
		this.glbTake = glbTake;
	}
	public BigDecimal getScoreInMin() {
		return scoreInMin;
	}
	public void setScoreInMin(BigDecimal scoreInMin) {
		this.scoreInMin = scoreInMin;
	}
	public BigDecimal getScoreInTake() {
		return scoreInTake;
	}
	public void setScoreInTake(BigDecimal scoreInTake) {
		this.scoreInTake = scoreInTake;
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
