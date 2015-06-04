package com.member.beans.back;

import java.util.List;

public class MenuBean {

	private Integer id;
	
	private String menunm;
	
	private String menuurl;
	
	private Integer pid;
	
	private List<MenuBean> childMenu;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<MenuBean> getChildMenu() {
		return childMenu;
	}

	public void setChildMenu(List<MenuBean> childMenu) {
		this.childMenu = childMenu;
	}

	public String getMenunm() {
		return menunm;
	}

	public void setMenunm(String menunm) {
		this.menunm = menunm;
	}

	public String getMenuurl() {
		return menuurl;
	}

	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
}
