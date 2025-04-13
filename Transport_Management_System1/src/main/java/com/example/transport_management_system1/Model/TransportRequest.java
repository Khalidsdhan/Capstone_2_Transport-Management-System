package com.example.transport_management_system1.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class TransportRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = " StartsFrom must be not empty")
    @Size(min = 4)
    private String startsFrom;


    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = " destination must be not empty")
    @Size(min = 4)
    private String destination;

    @Column(columnDefinition = "varchar(20) not null")
    @Pattern(regexp = "camel|car|furniuer|horse")
    private String type;

    @Column(columnDefinition = "int not null")
    @NotNull
    private Double estimatedWeight;

    @Column(columnDefinition = "varchar(20) not null")
    @Pattern(regexp = "accepted|completed|cancelled")
    private String status;

    @Column(columnDefinition = "int not null")
    @NotNull
    private Double distance;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate expectedDeliveryDate;


    @Column(columnDefinition = "int not null")
    @NotNull
    private Integer userId;

    @Column(columnDefinition = "int not null")
    @NotNull
    private Integer driverId;

    @Column(columnDefinition = "int not null")
    @NotNull
    private Integer vehicleId;



}
