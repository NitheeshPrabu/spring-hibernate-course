package com.example.springdemo.rest;

import com.example.springdemo.entity.Customer;
import com.example.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){

		return customerService.getCustomers();
	}

	@GetMapping("/customers/{id}")
	public Customer getCustomerById(@PathVariable int id) {

		Customer customer = customerService.getCustomer(id);

		if (customer == null)
			throw new CustomerNotFoundException("Invalid customer id " + id);

		return customer;
	}

	// @RequestBody - binds the POJO to a method parameters
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer) {

		/*
		 * Since we are using saveOrUpdate() in the service to add a customer, we may have to
		 * force the id to 0/null in order to indicate to Hibernate that this must only be an insert
		 * and not an update.
		*/
		customer.setId(0);
		customerService.addCustomer(customer);

		return customer;
	}

	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {

		customerService.addCustomer(customer);

		return customer;
	}

	@DeleteMapping("/customers/{id}")
	public String updateCustomer(@PathVariable int id) {

		Customer customer = customerService.getCustomer(id);

		if (customer == null)
			throw new CustomerNotFoundException("Invalid customer id " + id);

		customerService.deleteCustomer(id);
		return "Customer id " + id + " deleted successfully";
	}
}
