package com.example.finalprojectcar.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCarRentalRequest {
    private Integer carId;
    private Integer rentalId;
}
