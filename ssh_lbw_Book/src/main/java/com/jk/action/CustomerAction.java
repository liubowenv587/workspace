/** 
 * <pre>项目名称:ssh_lbw_lx3 
 * 文件名称:CustomerAction.java 
 * 包名:com.jk.action 
 * 创建日期:2019年8月5日下午8:08:55 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.jk.model.Customer;
import com.jk.service.impl.CustomerService;
import com.opensymphony.xwork2.ModelDriven;

/** 
 * <pre>项目名称：ssh_lbw_lx3    
 * 类名称：CustomerAction    
 * 类描述：    
 * 创建人：刘博文 
 * 创建时间：2019年8月5日 下午8:08:55    
 * 修改人：刘博文 
 * 修改时间：2019年8月5日 下午8:08:55    
 * 修改备注：       
 * @version </pre>    
 */
@Namespace("/")
@Action("customerAction")
@Results({
	@Result(name="show",location="/show.jsp"),
	@Result(name="addCustomer",location="/WEB-INF/addCustomer.jsp"),
	@Result(name="del",type="redirect",location="customerAction!querycustomer.action")
})
public class CustomerAction extends BaseAction implements ModelDriven<Customer>{

	private static final long serialVersionUID = 5007935643511462092L;
	@Autowired
	private CustomerService customerService;
	
	
	private Customer customer=new Customer();
    private Customer customer2;

	public Customer getCustomer2() {
	return customer2;
}
	public String querycustomer(){
		
		try {
			List<Customer> queryBookList = customerService.querycustomerList();
			String jsonStringWithDateFormat = JSON.toJSONStringWithDateFormat( queryBookList,"yyyy-MM-dd");
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("list", queryBookList);
//			super.writeJson(queryBookList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "show";
	}
	
	public String delCustomer(){
		
		customerService.delCustomer(customer);
		return "del";
	}
	
	public void addCustomer(){
		customerService.addCustomer(customer);
	}
	
	
	public String toAdd(){
		return "addCustomer";
	}
	
	
public void setCustomer2(Customer customer2) {
	this.customer2 = customer2;
}

	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}

}
