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
	private Integer goldMax;
	/**提现最小限额 */
	@Column(name="goldMin")
	private Integer goldMin;
	/**提现手续费 */
	@Column(name="goldTake")
	private Integer goldTake;
	/**	积分转账最大金额 */
	@Column(name="scoreMax")
	private Integer scoreMax;
	/**	积分转账最小金额 */
	@Column(name="scoreMin")
	private Integer scoreMin;
	/**	积分转账手续费 */
	@Column(name="scoreTake")
	private Integer scoreTake;
	/**	葛粮币转换最大金额 */
	@Column(name="glbMax")
	private Integer glbMax;
	/**	葛粮币转换最小金额 */
	@Column(name="glbMin")
	private Integer glbMin;
	/**	将葛粮币转换手续费: */
	@Column(name="glbTake")
	private Integer glbTake;
	/**	积分充值最小金额: */
	@Column(name="scoreInMin")
	private Integer scoreInMin;
	/**	积分充值手续费: */
	@Column(name="scoreInTake")
	private Integer scoreInTake;
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
	public Integer getGoldMax() {
		return goldMax;
	}
	public void setGoldMax(Integer goldMax) {
		this.goldMax = goldMax;
	}
	public Integer getGoldMin() {
		return goldMin;
	}
	public void setGoldMin(Integer goldMin) {
		this.goldMin = goldMin;
	}
	public Integer getGoldTake() {
		return goldTake;
	}
	public void setGoldTake(Integer goldTake) {
		this.goldTake = goldTake;
	}
	public Integer getScoreMax() {
		return scoreMax;
	}
	public void setScoreMax(Integer scoreMax) {
		this.scoreMax = scoreMax;
	}
	public Integer getScoreMin() {
		return scoreMin;
	}
	public void setScoreMin(Integer scoreMin) {
		this.scoreMin = scoreMin;
	}
	public Integer getScoreTake() {
		return scoreTake;
	}
	public void setScoreTake(Integer scoreTake) {
		this.scoreTake = scoreTake;
	}
	public Integer getGlbMax() {
		return glbMax;
	}
	public void setGlbMax(Integer glbMax) {
		this.glbMax = glbMax;
	}
	public Integer getGlbMin() {
		return glbMin;
	}
	public void setGlbMin(Integer glbMin) {
		this.glbMin = glbMin;
	}
	public Integer getGlbTake() {
		return glbTake;
	}
	public void setGlbTake(Integer glbTake) {
		this.glbTake = glbTake;
	}
	public Integer getScoreInMin() {
		return scoreInMin;
	}
	public void setScoreInMin(Integer scoreInMin) {
		this.scoreInMin = scoreInMin;
	}
	public Integer getScoreInTake() {
		return scoreInTake;
	}
	public void setScoreInTake(Integer scoreInTake) {
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
