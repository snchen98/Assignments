package com.capgemini.service;

import java.util.List;

import com.capgemini.entity.Employee;

public interface EmployeeServiceInterface {

    public List<Employee> getAllEmployeeDetails();
    public Employee getEmployeeById(int empId);
    public Employee addEmployee(Employee employee);
    public Employee updateEmployee(Employee employee);
    public void deleteEmployee(Employee employee);
}
