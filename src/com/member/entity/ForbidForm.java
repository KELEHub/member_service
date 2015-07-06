package com.member.entity;

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
@Table(name="ForbidForm")
@org.hibernate.annotations.Table(comment="禁止报单表",appliesTo="ForbidForm")
public class ForbidForm {

	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**是否禁止报单，0表示允许报单，1表示禁止报单 */
	@Column(name="ifForbid")
	private Integer ifForbid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIfForbid() {
		return ifForbid;
	}

	public void setIfForbid(Integer ifForbid) {
		this.ifForbid = ifForbid;
	}
	
}
