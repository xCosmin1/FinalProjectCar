package com.example.finalprojectcar.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCarToRentalRequest {
    private Integer carId;
    private Integer rentalId;
}
