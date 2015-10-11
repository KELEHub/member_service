package com.member.form.front;

import javax.persistence.Column;


public class ShopCartForm {
	/**购物车ID */
	private String id;
	
	/**会员登录ID */
	private String number;
	
	/**产品编号 */
	private String productNumber;
	
	/**产品名称 */
	private String productName;
	
	/**产品图片路径 */
	private String productTarget;
	
	/**服务站Number */
	private String serverNumber;
	
	/**购买数量 */
	private String shopCount;
	
	/**购买价格 */
	private String shopPrice;
	
	/**服务站电话*/
	private String phone;
	
	/**服务站地址*/
	private String address;
	
	/**购买总价 */
	private String priceall;
	
	/**商品状态 */
	private String stauts;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public String getServerNumber() {
		return serverNumber;
	}

	public void setServerNumber(String serverNumber) {
		this.serverNumber = serverNumber;
	}

	public String getShopCount() {
		return shopCount;
	}

	public void setShopCount(String shopCount) {
		this.shopCount = shopCount;
	}

	public String getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(String shopPrice) {
		this.shopPrice = shopPrice;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProductTarget() {
		return productTarget;
	}

	public void setProductTarget(String productTarget) {
		this.productTarget = productTarget;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPriceall() {
		return priceall;
	}

	public void setPriceall(String priceall) {
		this.priceall = priceall;
	}

	public String getStauts() {
		return stauts;
	}

	public void setStauts(String stauts) {
		this.stauts = stauts;
	}
	

}
