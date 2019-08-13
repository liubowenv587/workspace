package com.jk.service.impl.impl;

import java.util.List;

import com.jk.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dao.CustomerDao;
import com.jk.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	@Override
	public List<Customer> querycustomerList() {
		//StringBuffer hql =new StringBuffer("select new map(b.bookId as bookId,b.bookName as bookName ,b.bookPrice as bbb ,t.typeName as typeName) from Book b, BookType t where 1=1 and b.bookTypeId = t.typeId");
		StringBuffer hql =new StringBuffer(" from Customer c, Department d,Locations l where 1=1 and c.location_id = l.id and c.department_id=d.id");
		return customerDao.querycustomerList(hql.toString());
	}
	@Override
	public void delCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.delCustomer(customer);
	}
	@Override
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.addCustomer(customer);
	}

}
