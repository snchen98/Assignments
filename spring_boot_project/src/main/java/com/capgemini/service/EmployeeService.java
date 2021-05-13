package com.capgemini.service;

import java.util.List;

import com.capgemini.entity.Employee;
import com.capgemini.repo.EmployeeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    @Autowired
    EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployeeDetails() {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(int empId) {
        return employeeRepo.getById(empId);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeRepo.delete(employee);
    }
}