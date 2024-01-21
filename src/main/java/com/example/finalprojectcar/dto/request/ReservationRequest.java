package com.example.finalprojectcar.dto.request;

import com.example.finalprojectcar.model.Car;
import com.example.finalprojectcar.model.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateOfBooking;

    private String customerName;

    private String carModel;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateFrom;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateTo;

    private Integer amount;
}
