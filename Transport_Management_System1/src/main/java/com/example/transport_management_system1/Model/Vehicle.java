package com.example.transport_management_system1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Vehicle {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = " plateNumber must be not empty")
    @Size(min = 6)
    private String plateNumber;

    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = " plateNumber must be not empty")
    @Size(min = 3)
    private String model;

    @Column(columnDefinition = "varchar(20) not null ")
    @Pattern(regexp = "truck|trailer|VAN")
    private String type;

    @Column(columnDefinition = "int not null")
    @NotNull
    private Double maxLoadKg;

    @Column(columnDefinition = "int not null")
    @NotNull
    private Double distance;

    @Column(columnDefinition = "int not null")
    @NotNull
    private Integer driverId;

}
