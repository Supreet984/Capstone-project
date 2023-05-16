package com.capstone.project.repositories;

import com.capstone.project.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value="SELECT * FROM employees WHERE employee_name=?1",nativeQuery=true)
    Employee findByEmployeeName(String name);
}
