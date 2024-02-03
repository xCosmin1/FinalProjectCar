package com.example.finalprojectcar.mapper;

import com.example.finalprojectcar.dto.request.EmployeeRequest;
import com.example.finalprojectcar.dto.response.EmployeeResponse;
import com.example.finalprojectcar.model.Employee;
import com.example.finalprojectcar.model.Position;
import com.example.finalprojectcar.model.Rental;
import com.example.finalprojectcar.repository.EmployeeRepository;
import com.example.finalprojectcar.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeMapper {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RentalRepository rentalRepository;

    public Employee employeeRequest(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setPosition(Position.valueOf(employeeRequest.getPosition()));
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());

        Optional<Rental> rental = rentalRepository.findById(employeeRequest.getRentalId());
        if (rental.isPresent()) {
            employee.setRental(rental.get());
        }
        return employee;

    }

    public EmployeeResponse employeeResponse(Employee employee) {
        EmployeeResponse employeeResponse1 = new EmployeeResponse();
        employeeResponse1.setRentalId(employee.getRental().getId());
        employeeResponse1.setPosition(String.valueOf(employee.getPosition()));
        employeeResponse1.setLastName(employee.getLastName());
        employeeResponse1.setFirstName(employee.getFirstName());

        if (employee.getRental() != null) {
            employeeResponse1.setRentalId(employee.getRental().getId());
        }
        return employeeResponse1;
    }

}

