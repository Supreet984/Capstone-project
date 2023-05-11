package com.capstone.project.controller;

import com.capstone.project.entities.Employee;
import com.capstone.project.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    // default
    @GetMapping("/")
    public String home() {
        logger.info("Home Page");
        String html = "<h1>Serving from Spring Boot, Hosted on Kubernetes Cluster on GCP</h1>";
        html += """
                <form action="/employees/byID" method="get">
                  Employee ID:<br>
                  <input type="text" name="id" value="">
                  <br>
                  <input type="submit" value="Submit">
                </form>\s""";
        html += "<form action=\"/employees\" method=\"get\">\n" + "  <input type=\"submit\" value=\"Get All Employees\">\n" + "</form> ";
        return html;
    }

    //get all
    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees() {
        logger.info("Get All Employees" + " " + new Date());
        return employeeService.getAllEmployees();
    }

    //get by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String id) {
        try {
            Long.parseLong(id);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid ID");
        }
        logger.info("Get Employee By ID: " + id + " " + new Date());
        return employeeService.getEmployeeById(Long.parseLong(id));
    }

    //add
    @PostMapping("/employees/add")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) throws Exception {
        logger.info("Add Employee: " + employee.getEmployeeName() + " " + employee.getDateOfBirth() + " " + new Date());
        return employeeService.addEmployee(employee);
    }

    //update
    @PutMapping("/employees/update")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        logger.info("Update Employee: " + employee.getEmployeeName() + " " + employee.getDateOfBirth() + " " + new Date());
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/employees/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id) {
        try {
            Long.parseLong(id);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid ID");
        }
        logger.info("Delete Employee: " + id);
        return employeeService.deleteEmployee(Long.parseLong(id));
    }

    //employees/byID?id=1
    @GetMapping("/employees/byID")
    public ResponseEntity<?> getEmployeeByIdParam(@RequestParam String id) {
        try {
            Long.parseLong(id);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid ID");
        }
        logger.info("Get Employee By ID: " + id + " " + new Date());
        return employeeService.getEmployeeById(Long.parseLong(id));
    }

}
