package com.member.services.back;

import java.util.List;

import com.member.beans.back.MenuBean;
import com.member.entity.NmsUser;

 public interface NmUserService {
	 
	 public List<MenuBean> getMenuByUserId(Integer userId);
	 
	 public List<NmsUser> getNmsUserAll();

}
