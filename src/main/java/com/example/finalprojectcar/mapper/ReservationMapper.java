package com.example.finalprojectcar.mapper;

import com.example.finalprojectcar.dto.request.ReservationRequest;
import com.example.finalprojectcar.dto.response.ReservationResponse;
import com.example.finalprojectcar.model.Car;
import com.example.finalprojectcar.model.Customer;
import com.example.finalprojectcar.model.Reservation;
import com.example.finalprojectcar.repository.CarRepository;
import com.example.finalprojectcar.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CustomerRepository customerRepository;
    public  Reservation fromReservationRequest(ReservationRequest reservationRequest)
    {
        Reservation reservation = new Reservation();
        reservation.setAmount(reservationRequest.getAmount());
        reservation.setDateFrom(reservationRequest.getDateFrom());
        reservation.setDateOfBooking(reservationRequest.getDateOfBooking());
        reservation.setDateTo(reservationRequest.getDateTo());

        String carModel =  reservationRequest.getCarModel();
        Car car = carRepository.findCarByModel(carModel);

        reservation.setCar(car);


        String customerName = reservationRequest.getCustomerName();
        Customer customer = customerRepository.findCustomerByFirstName(customerName);

        reservation.setCustomer(customer);
        return reservation;
    }
    public ReservationResponse fromReservation(Reservation reservation){


        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setCustomerName(reservation.getCustomer().getFirstName());
        reservationResponse.setDateFrom(reservation.getDateFrom());
        reservationResponse.setAmount(reservation.getAmount());
        reservationResponse.setDateTo(reservation.getDateTo());

        reservationResponse.setCarModel(reservation.getCar().getModel());

        return reservationResponse;


    }
}
