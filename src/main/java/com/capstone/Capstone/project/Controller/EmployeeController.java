package com.capstone.Capstone.project.Controller;

import com.capstone.Capstone.project.entities.Employee;
import com.capstone.Capstone.project.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    // default
    @GetMapping("/")
    public String home() {
        return "<h1>Welcome to Employee Management System</h1><br>" +
                "<h2>Available Endpoints</h2><br>" +
                "<h3>GET</h3><br>" +
                "<p>/employees</p><br>" +
                "<p>/employees/{id}</p><br>" +
                "<h3>POST</h3><br>" +
                "<p>/employees/add</p><br>" +
                "<h3>PUT</h3><br>" +
                "<p>/employees/update</p><br>" +
                "<h3>DELETE</h3><br>" +
                "<p>/employees/delete/{id}</p><br>";


    }

    //get all
    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    //get by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    //add
    @PostMapping("/employees/add")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    //update
    @PutMapping("/employees/update")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/employees/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }


}
