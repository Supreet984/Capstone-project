package com.capstone.Capstone.project.services;

import com.capstone.Capstone.project.entities.Employee;
import com.capstone.Capstone.project.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    //get all
    public ResponseEntity<?> getAllEmployees() {
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    //get by id
    public ResponseEntity<?> getEmployeeById(Long id) {
        if(id == null)
            return ResponseEntity.badRequest().body(new EmployeeNotFoundException("Employee not found", 404));
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            return ResponseEntity.badRequest().body(new EmployeeNotFoundException("Employee not found", 404));
        }
    }

    //add
    public ResponseEntity<?> addEmployee(Employee employee) {
        return ResponseEntity.ok(employeeRepository.save(employee));
    }

    //update
    public ResponseEntity<?> updateEmployee(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getEmployeeID());
        if (employeeOptional.isPresent()) {
            return ResponseEntity.ok(employeeRepository.save(employee));
        } else {
            return ResponseEntity.badRequest().body(new EmployeeNotFoundException("Employee not found", 404));
        }
    }

    public ResponseEntity<?> deleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
            return ResponseEntity.ok("Employee deleted");
        } else {
            return ResponseEntity.badRequest().body(new EmployeeNotFoundException("Employee not found", 404));
        }
    }


}

@Data
@AllArgsConstructor
class EmployeeNotFoundException {
    private String message;
    private int status;
}
