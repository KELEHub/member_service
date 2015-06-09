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
@Table(name="t_product")
@org.hibernate.annotations.Table(comment="产品信息",appliesTo="t_product")
public class Product {
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**产品名称 */
	@Column(name="productName")
	private String productName;
	
	/**产品介绍ID */
	@Column(name="productIntroduction")
	private String productIntroduction;
	
	/**产品图片路径 */
	@Column(name="productTarget")
	private String productTarget;
	
	/**操作时间 */
	@Column(name="operDate")
	private Date operDate;
	
	/**产品状态 0:有效，1：无效*/
	@Column(name="productStatus")
	private Integer productStatus;
	
	/**产品类型 */
	@Column(name="productCategory")
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
