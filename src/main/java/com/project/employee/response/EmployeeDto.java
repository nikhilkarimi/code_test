package com.project.employee.response;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class EmployeeDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Long salary;

    public Double getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(Double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public Double getCessAmount() {
        return cessAmount;
    }

    public void setCessAmount(Double cessAmount) {
        this.cessAmount = cessAmount;
    }

    private Double yearlySalary;
    private Double cessAmount;

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    private Double taxAmount;

}
