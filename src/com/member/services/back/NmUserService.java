package com.member.services.back;

import java.util.List;

import com.member.beans.back.MenuBean;

 public interface NmUserService {
	 
	 public List<MenuBean> getMenuByUserId(Integer userId);

}
