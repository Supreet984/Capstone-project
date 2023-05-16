package com.capstone.project.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeID;

    //EmployeeName
    @Column(name = "employee_name")
    private String employeeName;

    //DateOfBirth
    @Column(name = "date_of_birth")
    private String dateOfBirth;



}
