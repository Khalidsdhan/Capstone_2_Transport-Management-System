package com.example.transport_management_system1.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "int not null")
    @NotNull
    @Min(1)
    @Max(5)
    private Integer score;

    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = " comment must be not empty")
    @Size(max = 50)
    private String comment;


    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = " ratedBy must be not empty")
    private String ratedBy;

    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = " ratedDriver must be not empty")
    private String ratedDriver;


    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate ratingDate;

    @Column(columnDefinition = "int not null")
    @NotNull
    private Integer transport_requestId;




}
