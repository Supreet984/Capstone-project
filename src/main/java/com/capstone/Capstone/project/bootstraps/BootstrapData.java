package com.capstone.Capstone.project.bootstraps;

import com.capstone.Capstone.project.entities.Employee;
import com.capstone.Capstone.project.repositories.EmployeeRepository;
import com.capstone.Capstone.project.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final EmployeeService employeeService;

    @Override
    public void run(String... args) throws Exception {
        Employee employee = new Employee();
        employee.setEmployeeName("John");
        employee.setDateOfBirth("12/12/1990");
        employeeService.addEmployee(employee);

        Employee employee1 = new Employee();
        employee1.setEmployeeName("Raj");
        employee1.setDateOfBirth("12/12/1990");
        employeeService.addEmployee(employee1);

        Employee employee2 = new Employee();
        employee2.setEmployeeName("Mike");
        employee2.setDateOfBirth("12/12/1990");
        employeeService.addEmployee(employee2);

    }
}