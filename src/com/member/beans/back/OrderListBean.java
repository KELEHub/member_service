package com.member.beans.back;

import java.util.Date;

public class OrderListBean {
	private Integer id;
	
	private String memeberNumber;
	
	private String tradeNo;
	
	private Date orderDate;
	
	private String serviceNumber;
	
	private Integer productId;
	
	private String productName;
	
	private Integer status;
	
	private String linkName;
	
	/**联系人省份 */
	private String linkProvince;
	
	/**联系人市级 */
	private String linkCity;

	/**联系人区县 */
	private String linkCounty;
	
	/**联系人地址 */
	private String linkAddress;
	
	/**邮编*/
	private String postNumber;
	
	/**电话号码 */
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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
