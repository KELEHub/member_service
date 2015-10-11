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
@Table(name="StockDistribute")
@org.hibernate.annotations.Table(comment="库存分配",appliesTo="StockDistribute")
public class StockDistribute {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	
	/**产品ID */
	@Column(name="productId")
	private Integer productId;
	
	
	/**产品编号 */
	@Column(name="productNumber")
	private String productNumber;
	
	/**产品名称 */
	@Column(name="productName")
	private String productName;
	
	/**服务站Id */
	@Column(name="serverId")
	private Integer serverId;
	
	/**服务站Number */
	@Column(name="serverNumber")
	private String serverNumber;
	
	/**服务站库存 */
	@Column(name="serverFirm")
	private Integer serverFirm;
	
	/**date */
	@Column(name="operDate")
	private Date operDate;
	


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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


	public Integer getServerFirm() {
		return serverFirm;
	}


	public void setServerFirm(Integer serverFirm) {
		this.serverFirm = serverFirm;
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


	public Date getOperDate() {
		return operDate;
	}


	public void setOperDate(Date operDate) {
		this.operDate = operDate;
	}


	
	
	
	

}
