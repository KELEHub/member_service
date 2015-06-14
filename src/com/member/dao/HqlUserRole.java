package com.member.dao;

public class HqlUserRole {
public final static String getUserByName=
"from NmsUser t where t.userName=?  and t.status>=0";
public final static String getUserById=
	"from NmsUser t where t.id=?";
public final static String getUserByNames=
	"from NmsUser t where t.userName in (:userNames)  and t.status>=0";
public final static String getRoleByName=
	"from NmsRole t where t.roleName=?  and t.status>=0";
public final static String getRoleByNames=
	"from NmsRole t where t.roleName in (:roleNames)  and t.status>=0";
public final static String getPermissionByParentCode=
	"from Permission t where t.parentCode=?  and t.status>=0";
public final static String getPermissionByRoleId=
	"from NmsRolePermission t where t.roleId=?  and t.status>=0";

public final static String getPermissionByType=
	"from Permission t where t.permissionType= ?  and t.status>=0"; 

public final static String deleteRolePermisisonByPermisisonCode=
	"delete from NmsRolePermission t where t.permissionCode =?";

public final static String getUserByRoleId=
	"from NmsUser t where t.roleId=?  and t.status>=0";
public final static String getUserByRoleName=
	"select nu from NmsUser nu,NmsRole nr where nu.roleId=nr.id  and nu.status>=0 and nr.roleName=?";
public final static String getUserInfoByCondition=
	"select new map(nu as nmsUser,nr.roleName as roleName,g.dataNameCode as dataNameCode) from NmsUser nu,NmsRole nr,Glossary g where  nu.roleId=nr.id and nu.userStatus=g.dataCode and nu.status>=0";
public final static String getUserInfoByConditionCount=
	"select count(*) from NmsUser nu,NmsRole nr,Glossary g where  nu.roleId=nr.id and nu.userStatus=g.dataCode and nu.status>=0";

public final static String resetUserPassword=
	"update NmsUser t set t.userPassword=:userPassword where t.id in (:ids) ";


public final static String getUserDataPermission=
	"select p.permissionCode from NmsUser u ,NmsRole r,NmsRolePermission np,Permission p where u.roleId=r.id and r.id=np.roleId and np.permissionCode=p.permissionCode and p.permissionType=1 and u.id=? and u.status>=0";

public final static String getNmsUserAll=
	"from NmsUser";



}
