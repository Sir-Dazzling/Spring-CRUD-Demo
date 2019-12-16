package com.crm_demo.models;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomerDAOImpl implements CustomerDAO 
{

	//Injecting the Session Factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() 
	{
		//Getting the Current Hibernate Session
		Session session = sessionFactory.getCurrentSession();
		
		//Creating a Query...sorting by Last Name
		Query<Customer> query = session.createQuery("from Customer order by firstName",Customer.class);
		
		//Executing the Query and getting the result list
		List<Customer> customers = query.getResultList();
		
		//returning the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) 
	{
		//Getting current Hibernate Session
		Session session = sessionFactory.getCurrentSession();
		
		//Saving/Updating the Customer
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) 
	{
		//Getting Current Hibernate Session
		Session session = sessionFactory.getCurrentSession();
		
		//Retrieving from Database using the primary Key
		Customer customer = session.get(Customer.class, id);
		
		return customer;
	}

	@Override
	public void deleteCustomer(int id) 
	{
		//Getting Current Hibernate Session
		Session session = sessionFactory.getCurrentSession();
		
		//Deleting from Database using the primary key
		Query query = session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", id).executeUpdate();
	}

}
