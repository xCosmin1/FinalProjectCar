package com.example.finalprojectcar.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddReservationToCustomerRequest {
    private Integer customerId;
    private Integer reservationId;
}
