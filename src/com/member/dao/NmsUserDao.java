package com.member.dao;

import java.util.List;

import com.member.beans.back.MenuBean;
import com.member.helper.dao.BaseDao;

 public interface NmsUserDao extends BaseDao
{
  public List<String> getUserDataPermissonCode(Integer userId);
 
  public List<MenuBean> getMenuByUserId(Integer userId);
  
  public List<MenuBean> getFrontMenuAll();
  
  public List<MenuBean> getFrontMenuBySome();

}
