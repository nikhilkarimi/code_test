package com.project.employee.service;

import com.project.employee.entity.Employee;
import com.project.employee.repo.EmployeeRepo;
import com.project.employee.response.EmployeeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Employee createEmployeeDetails(Employee employee){
        Employee employee1 = employeeRepo.save(employee);
        return employee1;
    }

    public List<EmployeeDto> getEmployeeDetails(){
        List<Employee> employees = employeeRepo.findAll();
        LocalDate currentYearStatus = LocalDate.now().withMonth(4).withDayOfMonth(1);
        return employees.stream()
                .map(employee -> calculateEmployeeTaxDetails(employee,currentYearStatus))
                .collect(Collectors.toList());
    }

    private EmployeeDto calculateEmployeeTaxDetails(Employee employee, LocalDate currentYearStatus){
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        LocalDate localDate = employee.getDateOfBirth();
        if(localDate.isAfter(currentYearStatus)){
            employeeDto.setYearlySalary((double) (employee.getSalary()*(12-localDate.getMonthValue()+1)));
        }else {
            employeeDto.setYearlySalary((double) (employee.getSalary()*12));
        }

        double lossOfPayPerDay = employee.getSalary() / 30;
        double yearlySalary = employeeDto.getYearlySalary();
        double taxAmount = 0.0;
        if (yearlySalary > 250000 && yearlySalary <= 500000) {
            taxAmount = 0.05 * (yearlySalary - 250000);
        } else if (yearlySalary > 500000 && yearlySalary <= 1000000) {
            taxAmount = 0.1 * (yearlySalary - 500000) + 0.05 * 250000;
        } else if (yearlySalary > 1000000) {
            taxAmount = 0.2 * (yearlySalary - 1000000) + 0.1 * 500000 + 0.05 * 250000;
        }
        if (yearlySalary > 2500000) {
            double cessAmount = 0.02 * (yearlySalary - 2500000);
            taxAmount += cessAmount;
            employeeDto.setCessAmount(cessAmount);
        }

        employeeDto.setTaxAmount(taxAmount);
        return  employeeDto;
    }
}
