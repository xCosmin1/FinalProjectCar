package com.example.finalprojectcar.controller;

import com.example.finalprojectcar.dto.request.*;
import com.example.finalprojectcar.dto.response.*;
import com.example.finalprojectcar.service.UniqueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/controller")
public class UniqueController {
    @Autowired
    private final UniqueService uniqueService;

    public UniqueController(UniqueService uniqueService) {
        this.uniqueService = uniqueService;
    }

    @PostMapping("/cars")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addCar(@Valid @RequestBody CarRequest carRequest) {
        uniqueService.addCar(carRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarResponse> getCar(@PathVariable Integer id) {
        CarResponse response = uniqueService.getCar(id);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/customers")
    public ResponseEntity<Void> addCustomer(@Valid @RequestBody RegisterRequest registerRequest) {
        uniqueService.addCustomer(registerRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/customers/name")
    public ResponseEntity<CustomerResponse> getCustomer(@RequestBody RegisterRequest registerRequest) {
        CustomerResponse customerResponse = uniqueService.getCustomer(registerRequest.getFirstName());
        return ResponseEntity.ok(customerResponse);

    }

    @PostMapping("/employees")
    public ResponseEntity<Void> addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        uniqueService.addEmployee(employeeRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/employees/name")
    public ResponseEntity<EmployeeResponse> getEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = uniqueService.getEmployee(employeeRequest.getFirstName());
        return ResponseEntity.ok(employeeResponse);
    }

    @PostMapping("/rental")
    public ResponseEntity<Void> addRental(@Valid @RequestBody RentalRequest rentalRequest) {
        uniqueService.addRental(rentalRequest);
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

    @PostMapping("/addCarToRental")
    public ResponseEntity<Void> addCarToRental(@RequestBody AddCarToRentalRequest addCarToRentalRequest) {
        uniqueService.addCarToRental(addCarToRentalRequest);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/addEmployeeToRental")
    public ResponseEntity<Void> addEmployeeToRental(@RequestBody AddEmployeeToRentalRequest addEmployeeToRentalRequest) {
        uniqueService.addEmployeeToRental(addEmployeeToRentalRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addReservationToCar")
    public ResponseEntity<Void> addReservationToCar(@RequestBody AddReservationToCarRequest addReservationToCarRequest) {
        uniqueService.addReservationToCar(addReservationToCarRequest);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/addReservationToCustomer")
    public ResponseEntity<Void> addReservationToCustomer(@RequestBody AddReservationToCustomerRequest addReservationToCustomerRequest){
        uniqueService.addReservationToCustomer(addReservationToCustomerRequest);
        return  ResponseEntity.ok().build();
    }
    @PutMapping("/{customerId}/{carId}")
    public ResponseEntity<Void> returnCar(@PathVariable Integer customerId, @PathVariable Integer carId ){
        uniqueService.returnCar(carId,customerId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer customerId){
        uniqueService.deleteCustomer(customerId);
        return ResponseEntity.ok().build();
    }

}
