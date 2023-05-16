package com.capstone.project.controller;

import com.capstone.project.services.EmployeeNotFound;
import com.capstone.project.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    //get by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable String id) {
        logger.info("Get Employee By ID: " + id + " " + new Date());

        long employeeID;
        try {
            employeeID = Long.parseLong(id);
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Please enter the id");
        }

        try {
            return ResponseEntity.ok(employeeService.getEmployee(employeeID));
        } catch (EmployeeNotFound ex) {
            return ResponseEntity.status(400).body(ex.toString());
        }
    }

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

    //employees/byID?id=1
    @GetMapping("/employees/byID")
    public ResponseEntity<?> getEmployeeByIdParam(@RequestParam String id) throws EmployeeNotFound {
        try {
            Long.parseLong(id);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid ID");
        }
        logger.info("Get Employee By ID: " + id + " " + new Date());
        return ResponseEntity.ok(employeeService.getEmployee(Long.parseLong(id)));
    }

}
