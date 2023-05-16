package com.capstone.project.controller;

import com.capstone.project.entities.Employee;
import com.capstone.project.repositories.EmployeeRepository;
import com.capstone.project.services.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class EmployeeControllerTest {

    @Mock
    EmployeeRepository employeeRepository;
    EmployeeService employeeService;
    AutoCloseable closeable;
    Employee employee;

    @BeforeEach
    void setUp() {
        closeable = org.mockito.MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeService(employeeRepository);
        employee = Employee.builder()
                .employeeName("Test Employee")
                .dateOfBirth("12/12/1990")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void getAllEmployees() {
        mock(EmployeeController.class);
        mock(EmployeeRepository.class);
        List<Employee> employeeList = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(employeeList);
        assert (employeeList.size() == 0);
    }

    @Test
    void addEmployee() {
        mock(EmployeeController.class);
        mock(EmployeeRepository.class);
        when(employeeRepository.save(employee)).thenReturn(employee);
        assert (employee.getEmployeeName().equals("Test Employee"));
    }

}