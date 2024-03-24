package com.example.finalprojectcar.security;

import com.example.finalprojectcar.model.Customer;
import com.example.finalprojectcar.repository.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomerDetailsImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null){
            throw new UsernameNotFoundException("User not found with this email" +email);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(email)
                .password(customer.getPassword())
                .roles("Customer")
                .build();
    }
}