package com.example.finalprojectcar.dto.response;

import com.example.finalprojectcar.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private List<Reservation> reservationList;
}
