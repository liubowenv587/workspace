package com.jk.model;

import java.io.Serializable;

public class RoleBean implements Serializable {
	private static final long serialVersionUID = 1881707696666048856L;
	private Integer id;
	private String name;
	private String createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
