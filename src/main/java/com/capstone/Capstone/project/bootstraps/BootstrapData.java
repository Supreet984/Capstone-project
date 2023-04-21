package com.capstone.Capstone.project.bootstraps;

import com.capstone.Capstone.project.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;
    @Override
    public void run(String... args) {
        System.out.println("Loading Employee Data");
        employeeRepository.findAll().forEach(System.out::println);
    }
}