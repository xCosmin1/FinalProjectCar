package com.example.finalprojectcar.dto.request;

import com.example.finalprojectcar.model.Reservation;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String address;

}
