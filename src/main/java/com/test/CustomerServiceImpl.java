package com.test;

import java.util.UUID;

import org.springframework.stereotype.Component;

//@Component("customerService")
public class CustomerServiceImpl implements ICustomerService {

	@Override
	public Customer getCustomerDetails(String name) {
		Customer customer = new Customer();
		customer.setAddress("Glasgow");
		customer.setName(UUID.randomUUID().toString());
		customer.setAge(29);
		return customer;
	}

	@Override
	public GenericResponse addCustomer(Customer customer) {
		GenericResponse response = new GenericResponse();

		if (customer != null && customer.getAge() > 18) {
			response.setMessage("Customer added");
			response.setSuccess(true);

		} else {
			response.setMessage("Customer to young to be added");
			response.setSuccess(false);
		}
		return response;

	}

}
