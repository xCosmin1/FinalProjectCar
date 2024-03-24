package com.example.finalprojectcar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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


    @NotNull
    @NotEmpty
    private String name;
    @Column(name = "internet_domain")

    @NotNull
    @NotEmpty
    private String internetDomain;

    @NotNull
    @NotEmpty
    @Column(name = "contact_address")
    private String contactAddress;

    @NotNull
    @NotEmpty
    @OneToMany
    private List<Car> cars = new ArrayList<>();

    @NotNull
    @NotEmpty
    @OneToMany
    private List<Employee> employees = new ArrayList<>();

    public void insertCar(Car car){
        this.cars.add(car);
        car.setRental(this);
    }

    public void insertEmployee(Employee employee){
        this.employees.add(employee);
        employee.setRental(this);
    }
}
