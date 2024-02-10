package com.example.finalprojectcar.repository;

import com.example.finalprojectcar.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    Car findCarByModel(String model);


}
