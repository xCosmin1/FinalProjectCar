package com.example.finalprojectcar.mapper;

import com.example.finalprojectcar.dto.request.CarRequest;
import com.example.finalprojectcar.dto.response.CarResponse;
import com.example.finalprojectcar.model.Car;
import com.example.finalprojectcar.model.Status;

import java.util.List;

public class CarMapper {
    public static Car fromCarRequest(CarRequest carRequest)
    {
        Car car = new Car();
        car.setAmount(carRequest.getAmount());
        car.setBrand(carRequest.getBrand());
        car.setColour(carRequest.getColour());
        car.setModel(carRequest.getModel());
        car.setMileage(carRequest.getMileage());
        car.setYear(carRequest.getYear());
        car.setBodyType(carRequest.getBodyType());
        car.setStatus(Status.valueOf(carRequest.getStatus()));

        return car;
    }

    public static CarResponse fromCar(Car car)
    {
        CarResponse carResponse = new CarResponse();
        carResponse.setAmount(car.getAmount());
        carResponse.setModel(car.getModel());
        carResponse.setMileage(car.getMileage());
        carResponse.setColour(car.getColour());
        carResponse.setBrand(car.getBrand());
        carResponse.setYear(car.getYear());
        carResponse.setStatus(car.getStatus().name());
        carResponse.setBodyType(car.getBodyType());

        if(car.getRental() != null)
        {
            carResponse.setRental(car.getRental().getName());
        }

        if(!car.getReservations().isEmpty())
        {
            carResponse.setReservations(car.getReservations().stream().map(reservation -> reservation.getId()).toList());
        }
        else
        {
            carResponse.setReservations(List.of());
        }

        return carResponse;
    }
}
