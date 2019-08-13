/** 
 * <pre>项目名称:ssh_lbw_lx3 
 * 文件名称:CustomerDao.java 
 * 包名:com.jk.dao 
 * 创建日期:2019年8月5日下午8:14:49 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.dao;

import java.util.List;

import com.jk.model.Customer;

/** 
 * <pre>项目名称：ssh_lbw_lx3    
 * 类名称：CustomerDao    
 * 类描述：    
 * 创建人：刘博文 
 * 创建时间：2019年8月5日 下午8:14:49    
 * 修改人：刘博文 
 * 修改时间：2019年8月5日 下午8:14:49    
 * 修改备注：       
 * @version </pre>    
 */
public interface CustomerDao {

	/** <pre>querycustomerList(这里用一句话描述这个方法的作用)   
	 * 创建人：刘博文
	 * 创建时间：2019年8月5日 下午8:32:31    
	 * 修改人：刘博文
	 * 修改时间：2019年8月5日 下午8:32:31    
	 * 修改备注： 
	 * @param string
	 * @return</pre>    
	 */
	List<Customer> querycustomerList(String hql);

	void delCustomer(Customer customer);

	void addCustomer(Customer customer);


}
