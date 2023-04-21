package com.capstone.Capstone.project.repositories;

import com.capstone.Capstone.project.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
