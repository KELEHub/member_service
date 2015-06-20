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

import com.member.beans.back.enumData.KindDataEnum;
import com.member.beans.back.enumData.ProjectEnum;

@Entity@DynamicUpdate(true)@DynamicInsert(true)
//@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,optimisticLock=org.hibernate.annotations.OptimisticLockType.VERSION)
@Table(name="AccountDetails")
@org.hibernate.annotations.Table(comment="账户明细表",appliesTo="AccountDetails")
public class AccountDetails {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**种类(葛粮币,积分) */
	@Column(name="kindData")
	private KindDataEnum kindData;
	
	/**日期类别统计 */
	@Column(name="dateNumber")
	private String dateNumber;
	
	/**项目(会员转账,充值,后台调整,积分转葛粮币增加,
	 * 单个服务积分,极差服务积分,积分转葛粮币减少,
	 * 积分提现,礼包发放,激活会员) */
	@Column(name="project")
	private ProjectEnum project;
	
	/**积分余额 */
	@Column(name="pointbalance")
	private BigDecimal pointbalance;
	
	/**葛粮币余额 */
	@Column(name="goldmoneybalance")
	private BigDecimal goldmoneybalance;
	
	/**收入 */
	@Column(name="income")
	private BigDecimal income;
	
	/**支出 */
	@Column(name="pay")
	private BigDecimal pay;
	
	/**备注 */
	@Column(name="redmin")
	private String redmin;
	
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

	public KindDataEnum getKindData() {
		return kindData;
	}

	public void setKindData(KindDataEnum kindData) {
		this.kindData = kindData;
	}

	public String getDateNumber() {
		return dateNumber;
	}

	public void setDateNumber(String dateNumber) {
		this.dateNumber = dateNumber;
	}

	public ProjectEnum getProject() {
		return project;
	}

	public void setProject(ProjectEnum project) {
		this.project = project;
	}

	public BigDecimal getPointbalance() {
		return pointbalance;
	}

	public void setPointbalance(BigDecimal pointbalance) {
		this.pointbalance = pointbalance;
	}

	public BigDecimal getGoldmoneybalance() {
		return goldmoneybalance;
	}

	public void setGoldmoneybalance(BigDecimal goldmoneybalance) {
		this.goldmoneybalance = goldmoneybalance;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public BigDecimal getPay() {
		return pay;
	}

	public void setPay(BigDecimal pay) {
		this.pay = pay;
	}

	public String getRedmin() {
		return redmin;
	}

	public void setRedmin(String redmin) {
		this.redmin = redmin;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	
}
