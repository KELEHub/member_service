package com.member.form;

import java.util.Date;

public class ProductOperForm {
	
	private Integer id;
	
	/**产品名称 */
	private String productName;
	
	/**产品介绍ID */
	private String productIntroduction;
	
	/**产品编号 */
	private String productNumber;
	
	/**商品规格 */
	private String productModel;
	
	/**商品价格 */
	private String productPrice;
	
	/**总库存 */
	private String allfirm;
	
	/**总库存修改 */
	private String productAllKC;
	
	/**产品图片路径 */
	private String productTarget;
	
	/**操作时间 */
	private Date operDate;
	
	/**产品状态 */
	private Integer productStatus;
	
	/**产品类型 */
	private String productCategory;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductIntroduction() {
		return productIntroduction;
	}

	public void setProductIntroduction(String productIntroduction) {
		this.productIntroduction = productIntroduction;
	}

	public String getProductTarget() {
		return productTarget;
	}

	public void setProductTarget(String productTarget) {
		this.productTarget = productTarget;
	}

	public Date getOperDate() {
		return operDate;
	}

	public void setOperDate(Date operDate) {
		this.operDate = operDate;
	}

	public Integer getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getAllfirm() {
		return allfirm;
	}

	public void setAllfirm(String allfirm) {
		this.allfirm = allfirm;
	}

	public String getProductAllKC() {
		return productAllKC;
	}

	public void setProductAllKC(String productAllKC) {
		this.productAllKC = productAllKC;
	}
	
	
}
