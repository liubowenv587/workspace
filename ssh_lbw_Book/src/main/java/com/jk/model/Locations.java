/** 
 * <pre>项目名称:ssh_lbw_lx3 
 * 文件名称:Locations.java 
 * 包名:com.jk.model 
 * 创建日期:2019年8月5日下午8:24:04 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.type.IntegerType;

/** 
 * <pre>项目名称：ssh_lbw_lx3    
 * 类名称：Locations    
 * 类描述：    
 * 创建人：刘博文 
 * 创建时间：2019年8月5日 下午8:24:04    
 * 修改人：刘博文 
 * 修改时间：2019年8月5日 下午8:24:04    
 * 修改备注：       
 * @version </pre>    
 */
@Entity
@Table(name="t_locations")
public class Locations implements Serializable{

	private static final long serialVersionUID = -3414731936771656236L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer	id;//地区
	private String name;
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
	
	
}
