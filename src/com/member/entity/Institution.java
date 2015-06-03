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

@Entity@DynamicUpdate(true)@DynamicInsert(true)
//@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,optimisticLock=org.hibernate.annotations.OptimisticLockType.VERSION)
@Table(name="Institution")
@org.hibernate.annotations.Table(comment="制度参数表",appliesTo="Institution")
public class Institution {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	/**注册资金 */
	@Column(name="registerGold")
	private Integer registerGold;
	/**直推礼包数*/
	@Column(name="preCount")
	private Integer preCount;
	/**5份礼包第一期*/
	@Column(name="preaFirst")
	private Integer preaFirst;
	/**5份礼包第二期*/
	@Column(name="preaSecond")
	private Integer preaSecond;
	/**5份礼包第三期*/
	@Column(name="preaThree")
	private Integer preaThree;
	/**5份礼包第四期*/
	@Column(name="preaFour")
	private Integer preaFour;
	/**5份礼包第5期*/
	@Column(name="preaFive")
	private Integer preaFive;
	/**10份礼包第1期*/
	@Column(name="prebFirst")
	private Integer prebFirst;
	/**10份礼包第2期*/
	@Column(name="prebSecond")
	private Integer prebSecond;
	/**10份礼包第3期*/
	@Column(name="prebThree")
	private Integer prebThree;
	/**10份礼包第4期*/
	@Column(name="prebFour")
	private Integer prebFour;
	/**10份礼包第5期*/
	@Column(name="prebFive")
	private Integer prebFive;
	/**10份礼包第6期*/
	@Column(name="prebSix")
	private Integer prebSix;
	/**10份礼包第7期*/
	@Column(name="prebSeven")
	private Integer prebSeven;
	/**10份礼包第8期*/
	@Column(name="prebEight")
	private Integer prebEight;
	/**10份礼包第9期*/
	@Column(name="prebNine")
	private Integer prebNine;
	/**10份礼包第10期*/
	@Column(name="prebTen")
	private Integer prebTen;
	/**服务费*/
	@Column(name="serviceCash")
	private Integer serviceCash;
	/**	系统数据 */
	@Column(name="systemData")
	private String systemData;
	/** createTime 创建时间 */
	@Column(name="createTime",nullable=true,unique=false,length=32)
	private Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRegisterGold() {
		return registerGold;
	}
	public void setRegisterGold(Integer registerGold) {
		this.registerGold = registerGold;
	}
	public Integer getPreCount() {
		return preCount;
	}
	public void setPreCount(Integer preCount) {
		this.preCount = preCount;
	}
	public Integer getPreaFirst() {
		return preaFirst;
	}
	public void setPreaFirst(Integer preaFirst) {
		this.preaFirst = preaFirst;
	}
	public Integer getPreaSecond() {
		return preaSecond;
	}
	public void setPreaSecond(Integer preaSecond) {
		this.preaSecond = preaSecond;
	}
	public Integer getPreaThree() {
		return preaThree;
	}
	public void setPreaThree(Integer preaThree) {
		this.preaThree = preaThree;
	}
	public Integer getPreaFour() {
		return preaFour;
	}
	public void setPreaFour(Integer preaFour) {
		this.preaFour = preaFour;
	}
	public Integer getPreaFive() {
		return preaFive;
	}
	public void setPreaFive(Integer preaFive) {
		this.preaFive = preaFive;
	}
	public Integer getPrebFirst() {
		return prebFirst;
	}
	public void setPrebFirst(Integer prebFirst) {
		this.prebFirst = prebFirst;
	}
	public Integer getPrebSecond() {
		return prebSecond;
	}
	public void setPrebSecond(Integer prebSecond) {
		this.prebSecond = prebSecond;
	}
	public Integer getPrebThree() {
		return prebThree;
	}
	public void setPrebThree(Integer prebThree) {
		this.prebThree = prebThree;
	}
	public Integer getPrebFour() {
		return prebFour;
	}
	public void setPrebFour(Integer prebFour) {
		this.prebFour = prebFour;
	}
	public Integer getPrebFive() {
		return prebFive;
	}
	public void setPrebFive(Integer prebFive) {
		this.prebFive = prebFive;
	}
	public Integer getPrebSix() {
		return prebSix;
	}
	public void setPrebSix(Integer prebSix) {
		this.prebSix = prebSix;
	}
	public Integer getPrebSeven() {
		return prebSeven;
	}
	public void setPrebSeven(Integer prebSeven) {
		this.prebSeven = prebSeven;
	}
	public Integer getPrebEight() {
		return prebEight;
	}
	public void setPrebEight(Integer prebEight) {
		this.prebEight = prebEight;
	}
	public Integer getPrebNine() {
		return prebNine;
	}
	public void setPrebNine(Integer prebNine) {
		this.prebNine = prebNine;
	}
	public Integer getPrebTen() {
		return prebTen;
	}
	public void setPrebTen(Integer prebTen) {
		this.prebTen = prebTen;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getSystemData() {
		return systemData;
	}
	public void setSystemData(String systemData) {
		this.systemData = systemData;
	}
	public Integer getServiceCash() {
		return serviceCash;
	}
	public void setServiceCash(Integer serviceCash) {
		this.serviceCash = serviceCash;
	}
	
	

}
