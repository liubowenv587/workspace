/** 
 * <pre>项目名称:mongodb_kx2 
 * 文件名称:PageController.java 
 * 包名:com.jk.controller 
 * 创建日期:2019年7月11日上午9:55:31 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class PageController {

	@RequestMapping("touserlist")
	public String touserlist() {

		return "Booklist";
	}

}
