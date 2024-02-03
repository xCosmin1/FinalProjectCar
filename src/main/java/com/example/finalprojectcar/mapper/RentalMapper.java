package com.example.finalprojectcar.mapper;

import com.example.finalprojectcar.dto.request.RentalRequest;
import com.example.finalprojectcar.dto.response.RentalResponse;
import com.example.finalprojectcar.model.Rental;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RentalMapper {
    public Rental fromRentalRequest(RentalRequest rentalRequest){
        Rental rental = new Rental();
        rental.setName(rentalRequest.getName());
        rental.setContactAddress(rentalRequest.getContactAddress());
        rental.setInternetDomain(rentalRequest.getInternetDomain());
        return rental;
    }

    public RentalResponse fromRentalResponse(Rental rental){
        RentalResponse rentalResponse = new RentalResponse();
        rentalResponse.setInternetDomain(rental.getInternetDomain());
        rentalResponse.setName(rental.getName());
        rentalResponse.setContactAddress(rental.getContactAddress());
        if (rental.getCars() !=null) {
            rentalResponse.setCars(rental.getCars().stream().map(car -> car.getBrand()).toList());
        }
        if (rental.getEmployees() !=null) {
            rentalResponse.setEmployees(rental.getEmployees().stream().map(employee -> employee.getFirstName()).toList());
        }
        return rentalResponse;
    }
}
