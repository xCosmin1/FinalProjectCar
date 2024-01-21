package com.example.finalprojectcar.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Integer id;

    @Column(name = "date_of_booking")
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateOfBooking;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "date_from")
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateFrom;

    @Column(name = "date_to")
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateTo;

    private Integer amount;
}
