package com.capstone.project.services;

public class EmployeeNotFound extends Exception {
    public EmployeeNotFound(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Employee Not Found:" + getMessage();
    }
}
