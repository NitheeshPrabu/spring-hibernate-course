package com.example.springdemo.controller;

import com.example.springdemo.dao.CustomerDAO;
import com.example.springdemo.entity.Customer;
import com.example.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// inject DAO into the controller
	// below is not best practice. it is better to use a service
	// along with a DAO

	// @Autowired
	// private CustomerDAO customerDAO;

	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model model) {

		// get customers from the service
		List<Customer> customers = customerService.getCustomers();

		// add customers to the model
		model.addAttribute("customers", customers);

		return "list-customers";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {

		// create a new model attribute to bind data
		Customer customer = new Customer();
		model.addAttribute("customer", customer);

		return "customer-add-form";
	}

	@PostMapping("/addCustomer")
	public String addCustomer(@ModelAttribute("customer") Customer customer) {

		// save the customer using the service
		customerService.addCustomer(customer);

		return "redirect:/customer/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {

		// get the customer from the service
		Customer customer = customerService.getCustomer(id);

		// prepopulate form by setting customer as model attribute
		model.addAttribute("customer", customer);

		return "customer-add-form";
	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id, Model model) {

		// delete the customer using the service
		customerService.deleteCustomer(id);

		return "redirect:/customer/list";
	}

	@PostMapping("/search")
	public String searchCustomers(@RequestParam("name") String name,
	                              Model model) {

		// search customers from the service
		List<Customer> theCustomers = customerService.searchCustomers(name);

		// add the customers to the model
		model.addAttribute("customers", theCustomers);

		return "list-customers";
	}

}
