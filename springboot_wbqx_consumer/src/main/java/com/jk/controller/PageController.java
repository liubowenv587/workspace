package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class PageController {

	@RequestMapping("tologin")
	public String tologin() {
		return "login";
	}

	@RequestMapping("toAddBook")
	public String toAddBook() {
		return "AddBook";
	}
	@RequestMapping("toMain")
	public String toMain(){
		return "main";
	}

	@RequestMapping("toUserListPage")
	public String toUserListPage(){
		return "userlist";
		
	}
	@RequestMapping("toRolePowerPage")
	public String toRolePowerPage(){
		return "rolepower";
		
	}
	@RequestMapping("toLogin")
	public String toLogin(){
		return "login";
		
	}
	//日志
	@RequestMapping("toLogList")
	public String toLogList(){
		return "loglist";
	}

}
