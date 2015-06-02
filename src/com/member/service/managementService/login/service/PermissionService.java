package com.member.service.managementService.login.service;

import java.util.List;

import com.member.service.login.po.NmsRolePermission;

 public interface PermissionService {
	 
  public List<NmsRolePermission> getPermissionsByUserName(String userName);
  public List<NmsRolePermission> getPermissionsByRoleId(Integer roleId);

}
