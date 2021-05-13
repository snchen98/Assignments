package com.capgemini.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    private int empId;
    private String name;
    private double salary;

    public Employee() {
        super();
    }

    public Employee(int empId, String name, double salary) {
        super();
        this.empId = empId;
        this.name = name;
        this.salary = salary;
    }

	public int getEmpId() {
		return this.empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
}