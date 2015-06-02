package com.member.dao;

import java.util.List;

import com.member.helper.dao.BaseDao;

 public interface NmsUserDao extends BaseDao
{
  public List<String> getUserDataPermissonCode(Integer userId);
 
}
