package com.project.employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumbers;

    @Column(name = "doj")
    private LocalDate doj;

    @Column(name = "salary")
    private double salary;

    @Column(name = "total_salary")
    private double totalSalary;

    @Column(name = "cess_amount")
    private double cessAmount;

    @Column(name = "tax_amount")
    private double taxAmount;

}
