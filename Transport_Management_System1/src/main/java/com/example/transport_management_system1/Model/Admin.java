package com.example.transport_management_system1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = "fullName must be not empty")
    @Size(min = 8)
    private String fullName;

    @Column(columnDefinition = "varchar(20) not null unique")
    @NotEmpty(message = "userName must be not empty")
    @Size(min = 6)
    private String adminName;

    @Column(columnDefinition = "varchar(20) not null unique")
    @NotEmpty(message = "password must be not empty")
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private String password;

    @Column(columnDefinition = "varchar(20) not null unique")
    @Email
    private String email;

    @Column(columnDefinition = "varchar(20) not null unique")
    @NotEmpty(message = "phoneNumber must be not empty")
    @Size(min = 8)
    private String phoneNumber;
}
