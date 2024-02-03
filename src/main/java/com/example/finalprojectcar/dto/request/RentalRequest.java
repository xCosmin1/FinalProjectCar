package com.example.finalprojectcar.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalRequest {
    private String name;
    private String internetDomain;
    private String contactAddress;



}
