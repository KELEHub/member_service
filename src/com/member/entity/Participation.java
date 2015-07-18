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
@Table(name="Participation")
@org.hibernate.annotations.Table(comment="礼包",appliesTo="Participation")
public class Participation {
	
	@Column(name="p_id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer p_id;
	
	@Column(name="p_iid")
	private Integer p_iid;
	
	@Column(name="p_inumber")
	private String p_inumber;
	
	@Column(name="p_number")
	private String p_number;
	
	/** createTime 创建时间 */
	@Column(name="p_indate",nullable=true,unique=false,length=32)
	private Date p_indate;
	
	/** createTime 创建时间 */
	@Column(name="p_okdate",nullable=true,unique=false,length=32)
	private Date p_okdate;
	
	@Column(name="p_toppid")
	private Integer p_toppid;
	
	@Column(name="p_toppname")
	private String p_toppname;
	
	@Column(name="p_target")
	private Integer p_target;
	
	@Column(name="p_count")
	private Integer p_count;
	
	@Column(name="p_state")
	private Integer p_state;
	
	@Column(name="p_category")
	private Integer p_category;
	
	@Column(name="p_group")
	private Integer p_group;
	
	@Column(name="p_groupindex")
	private Integer p_groupindex;
	
	@Column(name="p_index")
	private Integer p_index;
	
	@Column(name="p_layer")
	private Integer p_layer;
	
	@Column(name="p_one")
	private String p_one;
	
	@Column(name="p_two")
	private String p_two;
	
	@Column(name="p_three")
	private String p_three;
	
	@Column(name="p_four")
	private String p_four;
	
	@Column(name="p_five")
	private String p_five;
	@Column(name="p_put")
	private Integer p_put;
	public Integer getP_id() {
		return p_id;
	}
	public void setP_id(Integer pId) {
		p_id = pId;
	}
	public Integer getP_iid() {
		return p_iid;
	}
	public void setP_iid(Integer pIid) {
		p_iid = pIid;
	}
	public String getP_inumber() {
		return p_inumber;
	}
	public void setP_inumber(String pInumber) {
		p_inumber = pInumber;
	}
	public String getP_number() {
		return p_number;
	}
	public void setP_number(String pNumber) {
		p_number = pNumber;
	}
	public Date getP_indate() {
		return p_indate;
	}
	public void setP_indate(Date pIndate) {
		p_indate = pIndate;
	}
	public Date getP_okdate() {
		return p_okdate;
	}
	public void setP_okdate(Date pOkdate) {
		p_okdate = pOkdate;
	}
	public Integer getP_toppid() {
		return p_toppid;
	}
	public void setP_toppid(Integer pToppid) {
		p_toppid = pToppid;
	}
	public String getP_toppname() {
		return p_toppname;
	}
	public void setP_toppname(String pToppname) {
		p_toppname = pToppname;
	}
	public Integer getP_target() {
		return p_target;
	}
	public void setP_target(Integer pTarget) {
		p_target = pTarget;
	}
	public Integer getP_count() {
		return p_count;
	}
	public void setP_count(Integer pCount) {
		p_count = pCount;
	}
	public Integer getP_state() {
		return p_state;
	}
	public void setP_state(Integer pState) {
		p_state = pState;
	}
	public Integer getP_category() {
		return p_category;
	}
	public void setP_category(Integer pCategory) {
		p_category = pCategory;
	}
	public Integer getP_group() {
		return p_group;
	}
	public void setP_group(Integer pGroup) {
		p_group = pGroup;
	}
	public Integer getP_groupindex() {
		return p_groupindex;
	}
	public void setP_groupindex(Integer pGroupindex) {
		p_groupindex = pGroupindex;
	}
	public Integer getP_index() {
		return p_index;
	}
	public void setP_index(Integer pIndex) {
		p_index = pIndex;
	}
	public Integer getP_layer() {
		return p_layer;
	}
	public void setP_layer(Integer pLayer) {
		p_layer = pLayer;
	}
	public String getP_one() {
		return p_one;
	}
	public void setP_one(String pOne) {
		p_one = pOne;
	}
	public String getP_two() {
		return p_two;
	}
	public void setP_two(String pTwo) {
		p_two = pTwo;
	}
	public String getP_three() {
		return p_three;
	}
	public void setP_three(String pThree) {
		p_three = pThree;
	}
	public String getP_four() {
		return p_four;
	}
	public void setP_four(String pFour) {
		p_four = pFour;
	}
	public String getP_five() {
		return p_five;
	}
	public void setP_five(String pFive) {
		p_five = pFive;
	}
	public Integer getP_put() {
		return p_put;
	}
	public void setP_put(Integer pPut) {
		p_put = pPut;
	}
	
	
	
	
	
	

}
