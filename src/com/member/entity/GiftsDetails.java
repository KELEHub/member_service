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

import com.member.beans.back.enumData.BatchNoEnum;
import com.member.beans.back.enumData.GiftEnum;

@Entity@DynamicUpdate(true)@DynamicInsert(true)
//@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,optimisticLock=org.hibernate.annotations.OptimisticLockType.VERSION)
@Table(name="GiftsDetails")
@org.hibernate.annotations.Table(comment="礼包信息表",appliesTo="GiftsDetails")
public class GiftsDetails {
	
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**用户ID */
	@Column(name="userId")
	private Integer userId;
	
	/**下级报单中心ID */
	@Column(name="childId")
	private Integer childId;
	
	/**用户会员账号 */
	@Column(name="number")
	private String number;
	
	/**种类 */
	@Column(name="giftEnum")
	private GiftEnum giftEnum;
	
	/**流水号 */
	@Column(name="countNumber")
	private Integer countNumber;

	/**日期类别统计 */
	@Column(name="dateNumber")
	private String dateNumber;
	
	/**批次号 */
	@Column(name="batchNo")
	private BatchNoEnum batchNo;
	
	/**期次 */
	@Column(name="pointNumber")
	private Integer pointNumber;
	
	/**礼包名称*/
	@Column(name="name")
	private String name;
	
	/** createTime 创建时间 */
	@Column(name="createTime",nullable=true,unique=false,length=32)
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getChildId() {
		return childId;
	}

	public void setChildId(Integer childId) {
		this.childId = childId;
	}

	public GiftEnum getGiftEnum() {
		return giftEnum;
	}

	public void setGiftEnum(GiftEnum giftEnum) {
		this.giftEnum = giftEnum;
	}

	public String getDateNumber() {
		return dateNumber;
	}

	public void setDateNumber(String dateNumber) {
		this.dateNumber = dateNumber;
	}

	public BatchNoEnum getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(BatchNoEnum batchNo) {
		this.batchNo = batchNo;
	}

	public Integer getPointNumber() {
		return pointNumber;
	}

	public void setPointNumber(Integer pointNumber) {
		this.pointNumber = pointNumber;
	}

	public Integer getCountNumber() {
		return countNumber;
	}

	public void setCountNumber(Integer countNumber) {
		this.countNumber = countNumber;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
