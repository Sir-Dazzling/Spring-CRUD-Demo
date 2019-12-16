package com.crm_demo.services;

import java.util.List;

import com.crm_demo.models.Customer;

public interface CustomerService 
{
	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);
}
