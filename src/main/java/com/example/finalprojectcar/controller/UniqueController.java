package com.example.finalprojectcar.controller;

import com.example.finalprojectcar.dto.request.CarRequest;
import com.example.finalprojectcar.dto.request.CustomerRequest;
import com.example.finalprojectcar.dto.request.EmployeeRequest;
import com.example.finalprojectcar.dto.request.ReservationRequest;
import com.example.finalprojectcar.dto.response.CarResponse;
import com.example.finalprojectcar.dto.response.CustomerResponse;
import com.example.finalprojectcar.dto.response.EmployeeResponse;
import com.example.finalprojectcar.dto.response.ReservationResponse;
import com.example.finalprojectcar.model.*;
import com.example.finalprojectcar.service.UniqueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/controller")
public class UniqueController {
    private final UniqueService uniqueService;

    public UniqueController(UniqueService uniqueService) {
        this.uniqueService = uniqueService;
    }

    @PostMapping("/cars")
    public ResponseEntity<Void> addCar(@Valid @RequestBody CarRequest carRequest) {
        uniqueService.addCar(carRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarResponse> getCar(@PathVariable Integer id) {
        CarResponse response = uniqueService.getCar(id);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/customers")
    public ResponseEntity<Void> addCustomer(@Valid @RequestBody Customer customer) {
        uniqueService.addCustomer(customer);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/customers/name")
    public ResponseEntity<CustomerResponse> getCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = uniqueService.getCustomer(customerRequest.getFirstName());
        return ResponseEntity.ok(customerResponse);

    }
    @PostMapping("/employees")
    public ResponseEntity<Void> addEmployee(@Valid @RequestBody Employee employee) {
        uniqueService.addEmployee(employee);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/employees/name")
    public ResponseEntity<EmployeeResponse> getEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = uniqueService.getEmployee(employeeRequest.getFirstName());
        return ResponseEntity.ok(employeeResponse);
    }

    @PostMapping("/rental")
    public ResponseEntity<Void> addRental(@Valid @RequestBody Rental rental) {
        uniqueService.addRental(rental);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reservation")
    public ResponseEntity<Void> addReservation(@RequestBody ReservationRequest reservationRequest) {
        uniqueService.addReservation(reservationRequest);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable Integer id) {
        ReservationResponse response = uniqueService.getReservation(id);
        return ResponseEntity.ok(response);

    }

}
