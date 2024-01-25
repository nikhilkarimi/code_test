package com.project.employee.service;

import com.project.employee.entity.Employee;
import com.project.employee.repo.EmployeeRepo;
import com.project.employee.response.EmployeeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Employee createEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public  List<EmployeeDto> getAllEmployeeTaxDetails(){
        List<Employee> employees = employeeRepo.findAll();
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        return employees.stream()
                .map(employee -> calculateTaxDeduction(employee, currentYear))
                .collect(Collectors.toList());

    }

    private EmployeeDto calculateTaxDeduction(Employee employee, int currentYear){
        LocalDate doj = employee.getDoj();
        if (doj == null) {
            // Handle the case where date of joining is not provided
            // You may want to log an error, throw an exception, or handle it based on your requirements
            return null;
        }

        int monthsWorked;
        if (doj.getYear() == currentYear) {
            // If employee joined in the current year
            monthsWorked = LocalDate.now().getMonthValue() - doj.getMonthValue() + 1;
        } else {
            // If employee joined in a previous year
            monthsWorked = 12 - doj.getMonthValue() + 1;
        }

        // Calculate total yearly salary
        double totalSalary = employee.getSalary() * monthsWorked;

        // Calculate loss of pay per day
        double lossOfPayPerDay = employee.getSalary() / 30;

        // Calculate loss of pay based on the days worked in the joining month
        int daysWorkedInJoiningMonth = doj.lengthOfMonth() - doj.getDayOfMonth() + 1;
        double lossOfPay = daysWorkedInJoiningMonth * lossOfPayPerDay;

        // Include loss of pay in total salary
        totalSalary -= lossOfPay;
        double taxableAmount = totalSalary - 250000;
        double tax = 0;


        if (taxableAmount > 0) {
            if (taxableAmount <= 250000) {
                tax = 0;
            } else if (taxableAmount <= 500000) {
                tax = taxableAmount * 0.05;
            } else if (taxableAmount <= 1000000) {
                tax = 250000 * 0.05 + (taxableAmount - 500000) * 0.10;
            } else {
                tax = 250000 * 0.05 + 500000 * 0.10 + (taxableAmount - 1000000) * 0.20;
            }
        }

        // Calculate cess
        double cess = 0;
        if (totalSalary > 2500000) {
            cess = (totalSalary - 2500000) * 0.02;
        }

        // Create DTO
        EmployeeDto taxDeductionDTO = new EmployeeDto();
        taxDeductionDTO.setEmployeeId(employee.getEmployeeId());
        taxDeductionDTO.setFirstName(employee.getFirstName());
        taxDeductionDTO.setLastName(employee.getLastName());
        taxDeductionDTO.setTotalSalary(totalSalary);
        taxDeductionDTO.setTaxAmount(tax);
        taxDeductionDTO.setCessAmount(cess);

        return taxDeductionDTO;
    }

    }

