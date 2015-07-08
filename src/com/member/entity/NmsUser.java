//Description:打包
package com.member.entity;
//Description:导入�?
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
@Table(name="NmsUser")
@org.hibernate.annotations.Table(comment="用户表",appliesTo="NmsUser")
 public class NmsUser  
{
	/** ID ID */
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	/** userName 用户�?*/
	@Column(name="userName",nullable=false,unique=false,length=256)
	private String userName;
	/** userPassword 密码 */
	@Column(name="userPassword",nullable=false,unique=false,length=128)
	private String userPassword;
	/** roleId 用户角色 */
	@Column(name="roleId",nullable=true,unique=false)
	private Integer roleId;
	/** 用户别名*/
	@Column(name="userAlias",nullable=true,unique=false,length=34)
	private String  userAlias;
	/** 用户备注*/
	@Column(name="userRemark",nullable=true,unique=false,length=512)
	private String userRemark;
	/** 用户类型*/
	private Integer userType=0;
	/** 用户登录状�?*/
	private String userStatus;
	/** phoneNumber*/
	@Column(name="phoneNumber",nullable=true,unique=false,length=64)
	private String phoneNumber;
	/** email*/
	@Column(name="email",nullable=true,unique=false,length=64)
	private String email;
	/** qq*/
	@Column(name="qq",nullable=true,unique=false,length=64)
	private String qq;
	/** createTime 创建时间 */
	@Column(name="createTime",nullable=true,unique=false,length=32)
	private Date createTime;
	/** operateTime 操作时间 */
	@Column(name="operateTime",nullable=true,unique=false,length=32)
	private Date operateTime;
	/** authorId 创建�?*/
	@Column(name="creatorId",nullable=true,unique=false)
	private Integer creatorId;
	/**status 数据状�?*/
	@Column(name="status",nullable=true,unique=false,columnDefinition="INT default 0")
	private Integer status=0;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getUserAlias() {
		return userAlias;
	}
	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}

	
	
}//class end

