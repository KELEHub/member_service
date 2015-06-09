package com.member.form;

import java.util.Date;

public class ProductOperForm {
	
	private Integer id;
	
	/**产品名称 */
	private String productName;
	
	/**产品介绍ID */
	private String productIntroduction;
	
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
}
