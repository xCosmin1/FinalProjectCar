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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String address;

    @OneToMany
    private List<Reservation> reservationList = new ArrayList<>();

    public void insertReservation(Reservation reservation) {
        this.reservationList.add(reservation);
        reservation.setCustomer(this);
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "customer_roles",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public void setRoles(List<Role> roleNames) {
        List<Role> roles = new ArrayList<>();

        if (roleNames != null) {
            for (Role roleName : roleNames) {
                Role role = new Role();
                role.setName(String.valueOf(roleName));
                roles.add(role);
            }
        }

        this.roles = roles;
    }
}
