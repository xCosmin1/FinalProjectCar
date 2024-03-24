package com.example.finalprojectcar.mapper;

import com.example.finalprojectcar.dto.request.RegisterRequest;
import com.example.finalprojectcar.dto.response.CustomerResponse;
import com.example.finalprojectcar.model.Customer;
import com.example.finalprojectcar.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.List;



@Component
public class CustomerMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;
    public Customer fromRegisterRequest(RegisterRequest registerRequest) {
        Customer customer = new Customer();
        customer.setFirstName(registerRequest.getFirstName());
        customer.setLastName(registerRequest.getLastName());
        customer.setAddress(registerRequest.getAddress());
        customer.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        customer.setEmail(registerRequest.getEmail());

        List<Role> roles = registerRequest.getRoles()
                .stream()
                .map(roleName -> {
                    Role role = new Role();
                    role.setName(roleName);
                    return role;
                })
                .toList();
        customer.setRoles(roles);

        return customer;
    }



    public CustomerResponse fromCustomerResponse(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setAddress(customer.getAddress());
        customerResponse.setEmail(customer.getEmail());
        customerResponse.setFirstName(customer.getFirstName());
        customerResponse.setLastName(customer.getLastName());

        if (customer.getReservationList() != null) {
            customerResponse.setReservationList(customer.getReservationList());
        }
        return customerResponse;
    }


}
