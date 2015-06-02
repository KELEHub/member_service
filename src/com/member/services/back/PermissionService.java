package com.member.services.back;

import java.util.List;

import com.member.entity.NmsRolePermission;

 public interface PermissionService {
	 
  public List<NmsRolePermission> getPermissionsByUserName(String userName);
  public List<NmsRolePermission> getPermissionsByRoleId(Integer roleId);

}
