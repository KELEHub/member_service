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
@Table(name="Bonus")
@org.hibernate.annotations.Table(comment="分红或积分",appliesTo="Bonus")
public class Bonus {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**会员登录ID */
	@Column(name="number")
	private String number;
	
	/**会员登录密码*/
	@Column(name="password")
	private String password;
	
	/**流水号*/
	@Column(name="target")
	private String target;

	/**工程，意义不明 */
	@Column(name="project")
	private String project;
	
	/**发放时间 */
	@Column(name="issueDate")
	private Date issueDate;
	
	/**服务积分*/
	@Column(name="serviceIntegral")
	private BigDecimal serviceIntegral;
	
	/**积分总数 */
	@Column(name="integral")
	private BigDecimal integral;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public BigDecimal getServiceIntegral() {
		return serviceIntegral;
	}

	public void setServiceIntegral(BigDecimal serviceIntegral) {
		this.serviceIntegral = serviceIntegral;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**状态 */
	@Column(name="state")
	private Integer state;
	
	/**类别 */
	@Column(name="category")
	private Integer category;
	
	/**描述*/
	@Column(name="description")
	private String description;
	
}
