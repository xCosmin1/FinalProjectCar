package com.example.finalprojectcar.dto.response;

import com.example.finalprojectcar.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {
    private String brand;
    private String model;
    private String bodyType;
    private Integer year;
    private String colour;
    private Integer mileage;
    private Status status;
    private Integer amount;

    private String rental;
    private List<Integer> reservations;
}
