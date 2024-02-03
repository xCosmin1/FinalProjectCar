package com.example.finalprojectcar.mapper;

import com.example.finalprojectcar.dto.request.EmployeeRequest;
import com.example.finalprojectcar.dto.response.EmployeeResponse;
import com.example.finalprojectcar.model.Employee;
import com.example.finalprojectcar.repository.EmployeeRepository;
import com.example.finalprojectcar.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RentalRepository rentalRepository;

    public Employee employeeRequest(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setPosition(employee.getPosition());
        employee.setFirstName(employee.getFirstName());
        employee.setLastName(employee.getLastName());
        String firstName = employeeRequest.getFirstName();
        employee = employeeRepository.findEmployeeByFirstName(firstName);
        return employee;
    }

    public EmployeeResponse employeeResponse(EmployeeResponse employeeResponse){
        Employee employee = new Employee();
        employee.setRental(employee.getRental());
        employee.setPosition(employee.getPosition());
        employee.setLastName(employee.getLastName());
        employee.setFirstName(employeeResponse.getFirstName());
        return  employeeResponse;
    }
}
