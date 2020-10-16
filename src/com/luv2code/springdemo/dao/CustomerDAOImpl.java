package com.luv2code.springdemo.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO 
{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	
	public List<Customer> getCustomers() 
	{
		Session currentSession=sessionFactory.getCurrentSession();
		Query<Customer> theQuery=
				currentSession.createQuery("from Customer order by firstName",Customer.class);
		
		List<Customer>customer=theQuery.getResultList();
		return  customer;
	}

	@Override
	public void saveCustomer(Customer theCustomer)
	{
		Session currentSession=sessionFactory.getCurrentSession();
	  currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		Session currentSession=sessionFactory.getCurrentSession();
		Customer theCustomer=currentSession.get(Customer.class,theId);
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		Session currentSession=sessionFactory.getCurrentSession();
	Query theQuery=	currentSession.createQuery
			("delete from Customer where id=:theCustomerId");
		theQuery.setParameter("theCustomerId",theId);
		theQuery.executeUpdate();
	}

}
