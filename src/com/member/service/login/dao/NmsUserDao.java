package com.member.service.login.dao;

import java.util.List;

import com.member.service.frame.database.dao.BaseDao;

 public interface NmsUserDao extends BaseDao
{
  public List<String> getUserDataPermissonCode(Integer userId);
 
}
