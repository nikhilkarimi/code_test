package com.project.employee.controller;

import com.project.employee.entity.Employee;
import com.project.employee.response.EmployeeDto;
import com.project.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/tax-details")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeeTaxDetails(){
        List<EmployeeDto> employeeDto =  employeeService.getAllEmployeeTaxDetails();
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }
}
