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
@Table(name="ApplyserviceOld")
@org.hibernate.annotations.Table(comment="申请报单中心",appliesTo="ApplyserviceOld")
public class ApplyserviceOld {
	
	
	@Column(name="a_id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer a_id;
	
	/**提交会员（即报单中心）在information表的ID */
	@Column(name="a_submiti_d")
	private Integer a_submiti_d;
	
	/**提交会员（即报单中心）在information表的ID */
	@Column(name="a_submiti_number")
	private String a_submiti_number;
	
	/**提交会员（即报单中心）在information表的ID */
	@Column(name="a_submit_reason")
	private String a_submit_reason;
	
	
	/**提交会员（即报单中心）在information表的ID */
	@Column(name="a_applyi_d")
	private int a_applyi_d;
	
	/**提交会员（即报单中心）在information表的ID */
	@Column(name="a_applyi_number")
	private String a_applyi_number;

	/**提交会员（即报单中心）在information表的ID */
	@Column(name="a_applydesc")
	private String a_applydesc;
	
	
	/**申请时间 */
	@Column(name="a_applydate")
	private Date a_applydate;
	
	
	/**申请时间 */
	@Column(name="a_activatedate")
	private Date a_activatedate;
	
	/**提交会员（即报单中心）在information表的ID */
	@Column(name="a_state")
	private int a_state;
	

	/**提交会员（即报单中心）在information表的ID */
	@Column(name="a_target")
	private int a_target;
	
	/**提交会员（即报单中心）在information表的ID */
	@Column(name="a_desc")
	private String a_desc;

	public Integer getA_id() {
		return a_id;
	}

	public void setA_id(Integer aId) {
		a_id = aId;
	}

	public Integer getA_submiti_d() {
		return a_submiti_d;
	}

	public void setA_submiti_d(Integer aSubmitiD) {
		a_submiti_d = aSubmitiD;
	}

	public String getA_submiti_number() {
		return a_submiti_number;
	}

	public void setA_submiti_number(String aSubmitiNumber) {
		a_submiti_number = aSubmitiNumber;
	}

	public String getA_submit_reason() {
		return a_submit_reason;
	}

	public void setA_submit_reason(String aSubmitReason) {
		a_submit_reason = aSubmitReason;
	}

	public int getA_applyi_d() {
		return a_applyi_d;
	}

	public void setA_applyi_d(int aApplyiD) {
		a_applyi_d = aApplyiD;
	}

	public String getA_applyi_number() {
		return a_applyi_number;
	}

	public void setA_applyi_number(String aApplyiNumber) {
		a_applyi_number = aApplyiNumber;
	}

	public String getA_applydesc() {
		return a_applydesc;
	}

	public void setA_applydesc(String aApplydesc) {
		a_applydesc = aApplydesc;
	}

	public Date getA_applydate() {
		return a_applydate;
	}

	public void setA_applydate(Date aApplydate) {
		a_applydate = aApplydate;
	}

	public Date getA_activatedate() {
		return a_activatedate;
	}

	public void setA_activatedate(Date aActivatedate) {
		a_activatedate = aActivatedate;
	}

	public int getA_state() {
		return a_state;
	}

	public void setA_state(int aState) {
		a_state = aState;
	}

	public int getA_target() {
		return a_target;
	}

	public void setA_target(int aTarget) {
		a_target = aTarget;
	}

	public String getA_desc() {
		return a_desc;
	}

	public void setA_desc(String aDesc) {
		a_desc = aDesc;
	}
	
	
	
	
}
