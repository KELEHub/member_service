package com.member.form.back;

import java.util.List;

public class RoleMenuForm {

	private Integer id;
	
	private List<Integer> menuids;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Integer> getMenuids() {
		return menuids;
	}

	public void setMenuids(List<Integer> menuids) {
		this.menuids = menuids;
	}
}
