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
	private Long goldMax;
	/**提现最小限额 */
	@Column(name="goldMin")
	private Long goldMin;
	/**提现手续费 */
	@Column(name="goldTake")
	private Long goldTake;
	/**	积分转账最大金额 */
	@Column(name="scoreMax")
	private Long scoreMax;
	/**	积分转账最小金额 */
	@Column(name="scoreMin")
	private Long scoreMin;
	/**	积分转账手续费 */
	@Column(name="scoreTake")
	private Long scoreTake;
	/**	葛粮币转换最小金额 */
	@Column(name="glbMin")
	private Long glbMin;
	/**	将葛粮币转换手续费: */
	@Column(name="glbTake")
	private Long glbTake;
	/**	积分充值最小金额: */
	@Column(name="scoreInMin")
	private Long scoreInMin;
	/**	积分充值手续费: */
	@Column(name="scoreInTake")
	private Long scoreInTake;
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
	public Long getGoldMax() {
		return goldMax;
	}
	public void setGoldMax(Long goldMax) {
		this.goldMax = goldMax;
	}
	public Long getGoldMin() {
		return goldMin;
	}
	public void setGoldMin(Long goldMin) {
		this.goldMin = goldMin;
	}
	public Long getGoldTake() {
		return goldTake;
	}
	public void setGoldTake(Long goldTake) {
		this.goldTake = goldTake;
	}
	public Long getScoreMax() {
		return scoreMax;
	}
	public void setScoreMax(Long scoreMax) {
		this.scoreMax = scoreMax;
	}
	public Long getScoreMin() {
		return scoreMin;
	}
	public void setScoreMin(Long scoreMin) {
		this.scoreMin = scoreMin;
	}
	public Long getScoreTake() {
		return scoreTake;
	}
	public void setScoreTake(Long scoreTake) {
		this.scoreTake = scoreTake;
	}
	public Long getGlbMin() {
		return glbMin;
	}
	public void setGlbMin(Long glbMin) {
		this.glbMin = glbMin;
	}
	public Long getGlbTake() {
		return glbTake;
	}
	public void setGlbTake(Long glbTake) {
		this.glbTake = glbTake;
	}
	public Long getScoreInMin() {
		return scoreInMin;
	}
	public void setScoreInMin(Long scoreInMin) {
		this.scoreInMin = scoreInMin;
	}
	public Long getScoreInTake() {
		return scoreInTake;
	}
	public void setScoreInTake(Long scoreInTake) {
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
