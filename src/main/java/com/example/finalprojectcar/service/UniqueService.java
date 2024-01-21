package com.example.finalprojectcar.service;

import com.example.finalprojectcar.dto.request.CarRequest;
import com.example.finalprojectcar.dto.request.ReservationRequest;
import com.example.finalprojectcar.dto.response.CarResponse;
import com.example.finalprojectcar.dto.response.ReservationResponse;
import com.example.finalprojectcar.mapper.CarMapper;
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

    private final  ReservationMapper reservationMapper;

    public UniqueService(CarRepository carRepository, CustomerRepository customerRepository,
                         EmployeeRepository employeeRepository,
                         RentalRepository rentalRepository,
                         ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.rentalRepository = rentalRepository;
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    public void addCar(CarRequest carRequest) {
        Car car = CarMapper.fromCarRequest(carRequest);
        carRepository.save(car);
    }

    public CarResponse getCar(Integer id)
    {
        Optional<Car> carOptional = carRepository.findById(id);


        if(carOptional.isPresent())
        {
            Car car = carOptional.get();
            return CarMapper.fromCar(car);
        }
        else
            throw new RuntimeException("No car with id "+id+ " was found!");
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
    public ReservationResponse getReservation(Integer id){
       Reservation reservationFound = reservationRepository.findById(id).get();

        return reservationMapper.fromReservation(reservationFound);
    }

}