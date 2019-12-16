package com.crm_demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crm_demo.models.Customer;
import com.crm_demo.services.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomerController 
{
	//Injecting the Customer Service into the Controller
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model model) 
	{
		//getting customers from DAO
		List<Customer> customers = customerService.getCustomers();
		
		//adding customers to Model
		model.addAttribute("customers",customers);
		
		return "list-customers";
	}
	
	@GetMapping("showFormForAdd")
	public String showFormForAdd(Model model)
	{
		//Creating attribute to bind Form Data
		Customer customer  = new Customer();
		model.addAttribute("customer",customer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) 
	{
		//Saving the Customer using our service layer.
		customerService.saveCustomer(customer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model) 
	{
		//Getting Customer from Database
		Customer customer = customerService.getCustomer(id);
		
		//Setting Customer as a model attribute to pre populate the form
		model.addAttribute("customer",customer);
		
		
		//Send over to our form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id) 
	{
		//Deleting the Customer
		customerService.deleteCustomer(id);
		
		return "redirect:/customer/list";
	}
	
	
	
	
	
}
