package com.luv2code.springdemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController
{   @Autowired
	private CustomerService  customerService;
	
	@RequestMapping("/list")
	public String listCustomer(Model theModel)
	{ 
		List<Customer>customers=customerService.getCustomers();
		 theModel.addAttribute("customers",customers);
		
		return "list-customer";
	}
	@RequestMapping("/showForm")
	public String showFormForAdd(Model theModel)
	{
		Customer theCustomer=new Customer();
		theModel.addAttribute("customer",theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer")Customer theCustomer)
	{
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/list"; 
	}
	
	@GetMapping("/showFormUpdate")
   public String showFormUpdate(@RequestParam("customerId")int theId,Model theModel)	
   { 
		Customer theCustomer=customerService.getCustomer(theId);
   theModel.addAttribute("customer",theCustomer);
		return "customer-form" ;
   }
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId")int theId)
	{ 
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
}