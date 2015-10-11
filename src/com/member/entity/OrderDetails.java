package com.member.entity;

import java.math.BigDecimal;

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
@Table(name="OrderDetails")
@org.hibernate.annotations.Table(comment="订单详细表",appliesTo="OrderDetails")
public class OrderDetails {
	
	
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
	
	/**产品图片路径 */
	@Column(name="productTarget")
	private String productTarget;
	
	/**产品编号 */
	@Column(name="productNumber")
	private String productNumber;
	
	/**产品名称 */
	@Column(name="productName")
	private String productName;
	
	/**产品ID */
	@Column(name="productId")
	private Integer productId;
	
	
	/**服务站Id */
	@Column(name="serverId")
	private Integer serverId;
	
	/**服务站Number */
	@Column(name="serverNumber")
	private String serverNumber;
	
	
	/**购买数量 */
	@Column(name="shopCount")
	private Integer shopCount;
	
	/**购买单价 */
	@Column(name="shopPrice")
	private BigDecimal shopPrice;
	
	/**订单编号 */
	@Column(name="tnumber")
	private String tnumber;
	
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

	public String getProductTarget() {
		return productTarget;
	}

	public void setProductTarget(String productTarget) {
		this.productTarget = productTarget;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

	public String getServerNumber() {
		return serverNumber;
	}

	public void setServerNumber(String serverNumber) {
		this.serverNumber = serverNumber;
	}

	public Integer getShopCount() {
		return shopCount;
	}

	public void setShopCount(Integer shopCount) {
		this.shopCount = shopCount;
	}

	public BigDecimal getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(BigDecimal shopPrice) {
		this.shopPrice = shopPrice;
	}

	public String getTnumber() {
		return tnumber;
	}

	public void setTnumber(String tnumber) {
		this.tnumber = tnumber;
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
