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
@Table(name="OrderNumber")
@org.hibernate.annotations.Table(comment="订单表",appliesTo="OrderNumber")
public class OrderNumber {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**会员登录ID */
	@Column(name="number")
	private String number;
	
	/**会员登录ID */
	@Column(name="userId")
	private Integer userId;
	
	/**订单编号 */
	@Column(name="tnumber")
	private String tnumber;
	
	/** createTime 创建时间 */
	@Column(name="createTime",nullable=true,unique=false,length=32)
	private Date createTime;
	
	
	/**订单状态 */
	@Column(name="stauts")
	private Integer stauts;
	
	/**取货密码 */
	@Column(name="productPassword")
	private String productPassword;
	
	/**购买总价 */
	@Column(name="allPrice")
	private BigDecimal allPrice;

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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTnumber() {
		return tnumber;
	}

	public void setTnumber(String tnumber) {
		this.tnumber = tnumber;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStauts() {
		return stauts;
	}

	public void setStauts(Integer stauts) {
		this.stauts = stauts;
	}

	public String getProductPassword() {
		return productPassword;
	}

	public void setProductPassword(String productPassword) {
		this.productPassword = productPassword;
	}

	public BigDecimal getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(BigDecimal allPrice) {
		this.allPrice = allPrice;
	}
	
	
	

}
