package com.project.employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String email;
    @Column
    private String phoneNumber;
    @Column
    private LocalDate dateOfBirth;
    @Column
    private  Long salary;

    @Transient
    private Double yearlySalary;

    @Transient
    private Double cessAmount;

    @Transient
    private Double taxAmount;

}
