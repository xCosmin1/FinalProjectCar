package com.example.finalprojectcar.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEmployeeToRentalRequest {
    private Integer employeeId;
    private Integer rentalId;
}
