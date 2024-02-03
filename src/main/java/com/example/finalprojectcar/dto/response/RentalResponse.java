package com.example.finalprojectcar.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponse {
    private String name;
    private String internetDomain;
    private String contactAddress;
    private List<String> cars;
    private List<String> employees;
}
