package com.capstone.project.services;

import com.capstone.project.entities.Employee;
import com.capstone.project.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
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
        if (id == null)
            return ResponseEntity.badRequest().body(new EmployeeNotFoundException("Employee not found", 404));
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            return ResponseEntity.badRequest().body(new EmployeeNotFoundException("Employee not found", 404));
        }
    }

    //add
    public ResponseEntity<?> addEmployee(Employee employee) throws Exception {
        employee.setDateOfBirth(encrypt(employee.getDateOfBirth()));
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


    public static String encrypt(String plainText) throws Exception {
        String ALGORITHM = "AES/CBC/PKCS5Padding";
        String KEY = "0123456789abcdef0123456789abcdef";
        String IV = "0123456789abcdef";
        byte[] keyBytes = KEY.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        byte[] ivBytes = IV.getBytes(StandardCharsets.UTF_8);
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public void removeAll() {
        employeeRepository.deleteAll();
    }
}

@Data
@AllArgsConstructor
class EmployeeNotFoundException {
    private String message;
    private int status;
}
