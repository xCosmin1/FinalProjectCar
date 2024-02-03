package com.example.finalprojectcar.mapper;

import com.example.finalprojectcar.dto.request.CustomerRequest;
import com.example.finalprojectcar.dto.response.CustomerResponse;
import com.example.finalprojectcar.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer fromCustomerRequest(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setAddress(customerRequest.getAddress());
        customer.setEmail(customerRequest.getEmail());
        return customer;
    }

    public CustomerResponse fromCustomerResponse(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setAddress(customerResponse.getAddress());
        customerResponse.setEmail(customerResponse.getEmail());
        customerResponse.setFirstName(customer.getFirstName());
        customerResponse.setLastName(customerResponse.getLastName());
        if (customer.getReservationList() != null) {
            customerResponse.setReservationList(customer.getReservationList());
        }
        return customerResponse;
    }

}
