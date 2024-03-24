package com.example.finalprojectcar.service;

import com.example.finalprojectcar.dto.request.*;
import com.example.finalprojectcar.dto.response.CarResponse;
import com.example.finalprojectcar.dto.response.CustomerResponse;
import com.example.finalprojectcar.dto.response.EmployeeResponse;
import com.example.finalprojectcar.dto.response.ReservationResponse;
import com.example.finalprojectcar.mapper.*;
import com.example.finalprojectcar.model.*;
import com.example.finalprojectcar.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
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
    private final RentalMapper rentalMapper;

    private final RoleRepository roleRepository;

    public UniqueService(CarRepository carRepository, CustomerRepository customerRepository,
                         EmployeeRepository employeeRepository,
                         RentalRepository rentalRepository,
                         ReservationRepository reservationRepository, ReservationMapper reservationMapper, EmployeeMapper employeeMapper, CustomerMapper customerMapper, RentalMapper rentalMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.rentalRepository = rentalRepository;
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.employeeMapper = employeeMapper;
        this.customerMapper = customerMapper;
        this.rentalMapper = rentalMapper;

        this.roleRepository = roleRepository;
    }

    public void addCar(CarRequest carRequest) {
        Car car = CarMapper.fromCarRequest(carRequest);
        carRepository.save(car);
        throw new ResponseStatusException(HttpStatus.CREATED);
    }




    public CarResponse getCar(Integer id) {
        Optional<Car> carOptional = carRepository.findById(id);


        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            return CarMapper.fromCar(car);
        } else
            throw new RuntimeException("No car with id " + id + " was found!");
    }


    public void addCustomer(RegisterRequest registerRequest) {
        Customer customer = customerMapper.fromRegisterRequest(registerRequest);
        customerRepository.save(customer);
    }

    public void addEmployee(EmployeeRequest employeeRequest) {
        Employee employee = employeeMapper.employeeRequest(employeeRequest);
        employeeRepository.save(employee);
    }

    public void addRental(RentalRequest rentalRequest) {
        Rental rental = rentalMapper.fromRentalRequest(rentalRequest);
        rentalRepository.save(rental);
    }

    public void addReservation(ReservationRequest reservationRequest) {
        Reservation reservation = reservationMapper.fromReservationRequest(reservationRequest);
        reservation.setDateOfBooking(new Date());

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
        if (carOpt.isPresent() && rentalOpt.isPresent()) {
            Car car = carOpt.get();
            Rental rental = rentalOpt.get();

            rental.insertCar(car);
            rentalRepository.save(rental);// aici save-ul functioneaza ca un update.

        }
    }

    public void addEmployeeToRental(AddEmployeeToRentalRequest rentalRequest) {
        Integer employeeId = rentalRequest.getEmployeeId();
        Integer rentalId = rentalRequest.getRentalId();

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        Optional<Rental> rentalOptional = rentalRepository.findById(rentalId);

        if (employeeOptional.isPresent() && rentalOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            Rental rental = rentalOptional.get();

            rental.insertEmployee(employee);
            rentalRepository.save(rental);
        }

    }

    public void addReservationToCar(AddReservationToCarRequest reservationRequest) {
        Integer carId = reservationRequest.getCarId();
        Integer reservationId = reservationRequest.getReservationId();

        Optional<Car> carOptional = carRepository.findById(carId);
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        if (carOptional.isPresent() && reservationOptional.isPresent()) {
            Car car = carOptional.get();
            Reservation reservation = reservationOptional.get();

            car.insertReservation(reservation);
            car.setStatus(Status.valueOf("BOOKED"));
            carRepository.save(car);


        }
    }

    public void addReservationToCustomer(AddReservationToCustomerRequest reservationToCustomerRequest) {
        Integer customerId = reservationToCustomerRequest.getCustomerId();
        Integer reservationId = reservationToCustomerRequest.getReservationId();

        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        if (customerOptional.isPresent() && reservationOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Reservation reservation = reservationOptional.get();

            customer.insertReservation(reservation);
            customerRepository.save(customer);
        }
    }

    public void returnCar(Integer customerId, Integer carId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);      // customer_id =1 // car_id =3 // reservations = {1,2,3}
        Optional<Car> carOptional = carRepository.findById(carId);

        if (carOptional.isPresent() && customerOptional.isPresent()) {
            Car car = carOptional.get();
            Customer customer = customerOptional.get();

            Optional<Reservation> reservationFound = customer.getReservationList()
                    .stream()
                    .filter(reservation -> reservation.getCar().equals(car))
                    .findAny();

            if (reservationFound.isPresent()) {
                Reservation reservation = reservationFound.get();


                car.setStatus(Status.AVAILABLE);


                reservation.setCar(null);


                car.getReservations().remove(reservation);

                reservation.setCustomer(null);

                carRepository.save(car);
            }
        }
    }




    public void registerCustomer(RegisterRequest registerRequest) {
        Customer customer = customerMapper.fromRegisterRequest(registerRequest);
        Optional<Customer> existingCustomer = Optional.ofNullable(customerRepository.findByEmail(customer.getEmail()));

        if (existingCustomer.isPresent()) {
            throw new RuntimeException("A customer with the same email already exists");
        } else {
            customerRepository.save(customer);
        }
    }

    public void deleteCustomer(Integer customerId){
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()){
            customerRepository.deleteById(customerId);
        }
    }

}
