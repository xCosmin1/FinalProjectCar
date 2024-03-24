package com.example.finalprojectcar.dto.request;

import com.example.finalprojectcar.model.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest {

    @NotNull
    @NotEmpty
    private String brand;

    @NotNull
    @NotEmpty
    private String model;

    @NotNull
    @NotEmpty
    private String bodyType;

    @NotNull
    private Integer year;

    @NotNull
    @NotEmpty
    private String colour;

    @NotNull
    private Integer mileage;

    @NotNull
    @NotEmpty
    private Status status;

    @NotNull
    private Integer amount;

}
