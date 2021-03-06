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

@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name="t_order_list")
@org.hibernate.annotations.Table(comment="产品订单表",appliesTo="t_order_list")
public class OrderList {

	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="memeberNumber")
	private String memeberNumber;
	
	@Column(name="tradeNo")
	private String tradeNo;
	
	@Column(name="orderDate")
	private Date orderDate;
	
	@Column(name="serviceNumber")
	private String serviceNumber;
	
	@Column(name="productId")
	private Integer productId;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="linkName")
	private String linkName;
	
	/**联系人省份 */
	@Column(name="linkProvince")
	private String linkProvince;
	
	/**联系人市级 */
	@Column(name="linkCity")
	private String linkCity;

	/**联系人区县 */
	@Column(name="linkCounty")
	private String linkCounty;
	
	/**联系人地址 */
	@Column(name="linkAddress")
	private String linkAddress;
	
	/**邮编*/
	@Column(name="postNumber")
	private String postNumber;
	
	/**电话号码 */
	@Column(name="phoneNumber")
	private String phoneNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMemeberNumber() {
		return memeberNumber;
	}

	public void setMemeberNumber(String memeberNumber) {
		this.memeberNumber = memeberNumber;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkProvince() {
		return linkProvince;
	}

	public void setLinkProvince(String linkProvince) {
		this.linkProvince = linkProvince;
	}

	public String getLinkCity() {
		return linkCity;
	}

	public void setLinkCity(String linkCity) {
		this.linkCity = linkCity;
	}

	public String getLinkCounty() {
		return linkCounty;
	}

	public void setLinkCounty(String linkCounty) {
		this.linkCounty = linkCounty;
	}

	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

	public String getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(String postNumber) {
		this.postNumber = postNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
