/** 
 * <pre>项目名称:maven-user 
 * 文件名称:DeptBean.java 
 * 包名:com.jk.model 
 * 创建日期:2019年5月30日下午7:44:19 
 * Copyright (c) 2019, 634369607@qq.com All Rights Reserved.</pre> 
 */  
package com.jk.model;

import java.io.Serializable;
import java.util.List;

/** 
 * <pre>项目名称：maven-user    
 * 类名称：DeptBean    
 * 类描述：    
 * 创建人：张雅丽    
 * 创建时间：2019年5月30日 下午7:44:19    
 * 修改人：张雅丽       
 * 修改时间：2019年5月30日 下午7:44:19    
 * 修改备注：       
 * @version </pre>    
 */
public class DeptBean implements Serializable {

	private static final long serialVersionUID = 1051380208542330141L;
	private Integer id;
	private String text;
	private Integer pid;
	
	
	private List<DeptBean> children;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public List<DeptBean> getChildren() {
		return children;
	}

	public void setChildren(List<DeptBean> children) {
		this.children = children;
	}
}
