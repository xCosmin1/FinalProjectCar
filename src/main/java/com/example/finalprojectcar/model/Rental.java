package com.example.finalprojectcar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @Column(name = "internet_domain")
    private String internetDomain;
    @Column(name = "contact_address")
    private String contactAddress;

    @OneToMany
    private List<Car> cars = new ArrayList<>();
    @OneToMany
    private List<Employee> employees;


    public void insertCar(Car car)
    {
        this.cars.add(car);
        car.setRental(this);
    }

}
