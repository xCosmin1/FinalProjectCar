package com.example.finalprojectcar.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private String firstName;
    private String lastName;
    private String position;

    private Integer rentalId;
}
