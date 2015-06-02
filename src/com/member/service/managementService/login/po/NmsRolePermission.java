//Description:打包
package com.member.service.managementService.login.po;
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
@Table(name="NmsRolePermission")
@org.hibernate.annotations.Table(comment="角色权限表",appliesTo="NmsRolePermission")
 public class NmsRolePermission
{


	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	/**角色ID */
	@Column(name="roleId")
	private Integer roleId;
	/**提取权限码权限码 */
	@Column(name="getGoldCode")
	private String getGoldCode;
	
	/** operateTime 操作时间 */
	@Column(name="operateTime",nullable=true,unique=false,length=32)
	private Date operateTime;
	/** createTime 创建时间 */
	@Column(name="createTime",nullable=true,unique=false,length=32)
	private Date createTime;
	/** authorId 创建�?*/
	@Column(name="creatorId",nullable=true,unique=false)
	private Integer creatorId;
	/** 数据状�?*/
	@Column(name="status",nullable=true,unique=false,columnDefinition="INT default 0")
	private Integer status=0;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public String getGetGoldCode() {
		return getGoldCode;
	}
	public void setGetGoldCode(String getGoldCode) {
		this.getGoldCode = getGoldCode;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	

	
	

}

