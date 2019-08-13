/** 
 * <pre>项目名称:ssh_lbw_lx3 
 * 文件名称:CustomerService.java 
 * 包名:com.jk.service 
 * 创建日期:2019年8月5日下午8:12:32 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.service.impl;

import java.util.List;

import com.jk.model.Customer;

/** 
 * <pre>项目名称：ssh_lbw_lx3    
 * 类名称：CustomerService    
 * 类描述：    
 * 创建人：刘博文 
 * 创建时间：2019年8月5日 下午8:12:32    
 * 修改人：刘博文 
 * 修改时间：2019年8月5日 下午8:12:32    
 * 修改备注：       
 * @version </pre>    
 */
public interface CustomerService {

	/** <pre>querycustomerList(这里用一句话描述这个方法的作用)   
	 * 创建人：刘博文
	 * 创建时间：2019年8月5日 下午8:22:25    
	 * 修改人：刘博文
	 * 修改时间：2019年8月5日 下午8:22:25    
	 * 修改备注： 
	 * @return</pre>    
	 */
	List<Customer> querycustomerList();

	void delCustomer(Customer customer);

	void addCustomer(Customer customer);

}
