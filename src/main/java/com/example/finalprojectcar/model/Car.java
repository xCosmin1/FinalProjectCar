package com.example.finalprojectcar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
    @Enumerated(EnumType.STRING)

    @NotNull
    @NotEmpty
    private Status status;

    @NotNull
    private Integer amount;

    @NotNull
    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @NotNull
    @NotEmpty
    @OneToMany
    private List<Reservation> reservations = new ArrayList<>();



    public void insertReservation(Reservation reservation){
        this.reservations.add(reservation);
        reservation.setCar(this);
    }
}
