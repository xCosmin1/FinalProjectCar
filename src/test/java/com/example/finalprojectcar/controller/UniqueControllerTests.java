package com.example.finalprojectcar.controller;

import com.example.finalprojectcar.dto.request.CarRequest;
import com.example.finalprojectcar.dto.response.CarResponse;

import com.example.finalprojectcar.repository.CarRepository;
import com.example.finalprojectcar.service.UniqueService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.finalprojectcar.model.Status.AVAILABLE;


import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UniqueController.class)
public class UniqueControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UniqueService uniqueService;

    @MockBean
    CarRepository carRepository;


    @Test
    @WithMockUser
    public void addCarTest() throws Exception{
        CarRequest carRequest = new CarRequest("Chevrolet","Camaro","Coupe",2020,"Black",28000,AVAILABLE,5000);
        Mockito.doNothing().when(uniqueService)
                .addCar(Mockito.any(CarRequest.class));

        mockMvc.perform(post("/api/controller/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(carRequest))
                .with(csrf()))
                .andExpect(status().isCreated())
                .andDo(print());
    }
    @Test
    @WithMockUser
    public void getCarTest() throws Exception{
        CarResponse carResponse = new CarResponse("BMW","M5","Sedan",2020,"Blue",55353,AVAILABLE,4000,"Rental",null);
        int carId = 1;
        Mockito.when(uniqueService.getCar(eq(carId))).thenReturn(carResponse);
        mockMvc.perform(get("/api/controller/car/{id}", carId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(uniqueService,Mockito.times(1)).getCar(eq(carId));

    }
    @Test
    @WithMockUser
    public void returnCarTest() throws Exception{
        int carId = 1;
        int customerId =1;
        mockMvc.perform(put("/api/controller/{customerId}/{carId}", customerId,carId)
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf()))
                .andExpect(status().isOk())
                .andDo(print());

    }
    @Test
    @WithMockUser
    public void deleteCustomerTest() throws Exception{
        int customerId = 22;
        Mockito.doNothing().when(uniqueService)
                .deleteCustomer(customerId);
        mockMvc.perform(delete("/api/controller/customers/{customerId}",customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf()))
                .andExpect(status().isOk())
                .andDo(print());
    }



}
