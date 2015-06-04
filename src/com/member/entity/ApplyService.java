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
@Table(name="ApplyService")
@org.hibernate.annotations.Table(comment="申请报单中心",appliesTo="ApplyService")
public class ApplyService {

	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**提交会员（即报单中心）在information表的ID */
	@Column(name="submitId")
	private Integer submitId;
	
	/**提交会员（即报单中心）的登录ID */
	@Column(name="submitNumber")
	private String submitNumber;
	
	/**申请原因 */
	@Column(name="submitReason")
	private String submitReason;
	
	/**申请会员（即普通会员）在information表的ID */
	@Column(name="applyId")
	private Integer applyId;
	
	/**申请会员（即普通会员）的登录ID */
	@Column(name="applyNumber")
	private String applyNumber;
	
	/**申请描述 */
	@Column(name="applyDesc")
	private String applyDesc;

	/**申请时间 */
	@Column(name="applyDate")
	private Date applyDate;
	
	/**处理时间 */
	@Column(name="disposeDate")
	private Date disposeDate;
	
	/**处理状态，0未处理，1已处理 */
	@Column(name="state")
	private Integer state;
	
	/**目标，用途未知 */
	@Column(name="target")
	private Integer target;
	
	/**描述 */
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

	public Integer getSubmitId() {
		return submitId;
	}

	public void setSubmitId(Integer submitId) {
		this.submitId = submitId;
	}

	public String getSubmitNumber() {
		return submitNumber;
	}

	public void setSubmitNumber(String submitNumber) {
		this.submitNumber = submitNumber;
	}

	public String getSubmitReason() {
		return submitReason;
	}

	public void setSubmitReason(String submitReason) {
		this.submitReason = submitReason;
	}

	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public String getApplyNumber() {
		return applyNumber;
	}

	public void setApplyNumber(String applyNumber) {
		this.applyNumber = applyNumber;
	}

	public String getApplyDesc() {
		return applyDesc;
	}

	public void setApplyDesc(String applyDesc) {
		this.applyDesc = applyDesc;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Date getDisposeDate() {
		return disposeDate;
	}

	public void setDisposeDate(Date disposeDate) {
		this.disposeDate = disposeDate;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getTarget() {
		return target;
	}

	public void setTarget(Integer target) {
		this.target = target;
	}

}
