package com.example.finalprojectcar.service;

import com.example.finalprojectcar.dto.request.AddCarToRentalRequest;
import com.example.finalprojectcar.dto.request.CarRequest;
import com.example.finalprojectcar.dto.request.ReservationRequest;
import com.example.finalprojectcar.dto.response.CarResponse;
import com.example.finalprojectcar.dto.response.CustomerResponse;
import com.example.finalprojectcar.dto.response.EmployeeResponse;
import com.example.finalprojectcar.dto.response.ReservationResponse;
import com.example.finalprojectcar.mapper.CarMapper;
import com.example.finalprojectcar.mapper.CustomerMapper;
import com.example.finalprojectcar.mapper.EmployeeMapper;
import com.example.finalprojectcar.mapper.ReservationMapper;
import com.example.finalprojectcar.model.*;
import com.example.finalprojectcar.repository.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UniqueService {

    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final RentalRepository rentalRepository;
    private final ReservationRepository reservationRepository;

    private final ReservationMapper reservationMapper;
    private final EmployeeMapper employeeMapper;
    private final CustomerMapper customerMapper;


    public UniqueService(CarRepository carRepository, CustomerRepository customerRepository,
                         EmployeeRepository employeeRepository,
                         RentalRepository rentalRepository,
                         ReservationRepository reservationRepository, ReservationMapper reservationMapper, EmployeeMapper employeeMapper, CustomerMapper customerMapper) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.rentalRepository = rentalRepository;
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.employeeMapper = employeeMapper;
        this.customerMapper = customerMapper;
    }

    public void addCar(CarRequest carRequest) {
        Car car = CarMapper.fromCarRequest(carRequest);
        carRepository.save(car);
    }

    public CarResponse getCar(Integer id) {
        Optional<Car> carOptional = carRepository.findById(id);


        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            return CarMapper.fromCar(car);
        } else
            throw new RuntimeException("No car with id " + id + " was found!");
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void addRental(Rental rental) {
        rentalRepository.save(rental);
    }

    public void addReservation(ReservationRequest reservationRequest) {
        Reservation reservation = reservationMapper.fromReservationRequest(reservationRequest);
        reservationRepository.save(reservation);
    }

    public ReservationResponse getReservation(Integer id) {
        Reservation reservationFound = reservationRepository.findById(id).get();

        return reservationMapper.fromReservation(reservationFound);
    }

    public EmployeeResponse getEmployee(String firstName) {
        Employee employee = employeeRepository.findEmployeeByFirstName(firstName);
        return employeeMapper.employeeResponse(employee);
    }

    public CustomerResponse getCustomer(String firstName) {
        Customer customer = customerRepository.findCustomerByFirstName(firstName);
        return customerMapper.fromCustomerResponse(customer);
    }

    public void addCarToRental(AddCarToRentalRequest rentalRequest) {
        Integer carId = rentalRequest.getCarId();
        Integer rentalId = rentalRequest.getRentalId();

        Optional<Car> carOpt = carRepository.findById(carId);
        Optional<Rental> rentalOpt = rentalRepository.findById(rentalId);
        if(carOpt.isPresent() && rentalOpt.isPresent()){
            Car car = carOpt.get();
            Rental rental = rentalOpt.get();

            rental.insertCar(car);
            rentalRepository.save(rental);// aici save-ul functioneaza ca un update.

        }
    }

}
