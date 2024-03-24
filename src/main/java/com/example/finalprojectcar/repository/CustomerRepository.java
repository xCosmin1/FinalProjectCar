package com.example.finalprojectcar.repository;

import com.example.finalprojectcar.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

    Customer findCustomerByFirstName(String firstName);
    Customer findByEmail(String email);


    void deleteById(Integer id);
}
