package com.jk.model;

import java.io.Serializable;

public class UserRoleBean implements Serializable {
	private static final long serialVersionUID = 7042569432622286261L;
	private Integer id;
	private Integer userId;
	private Integer roleId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
