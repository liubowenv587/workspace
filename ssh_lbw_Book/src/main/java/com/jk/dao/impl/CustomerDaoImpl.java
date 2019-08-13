package com.jk.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jk.dao.CustomerDao;
import com.jk.model.Customer;
@Repository
public class CustomerDaoImpl implements CustomerDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Customer> querycustomerList(String hql) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query createQuery = currentSession.createQuery(hql);
		return createQuery.list() ;
	}
	@Override
	public void delCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(customer);
	}
	@Override
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(customer);
	}

}
