package com.capgemini.beans;

public class Student {
    private int rollNumber;
    private String name;
    private int maxCourses = 10;
    private String[] courseNames;

    public Student(int rollNumber, String name, String[] courseNames) {
        super();
        this.rollNumber = rollNumber;
        this.name = name;
        this.maxCourses = 10;
        this.courseNames = new String[this.maxCourses];
        this.courseNames = courseNames;
    }

    public int getRollNumber() {
        return rollNumber;
    }
    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String[] getCourses() {
        return this.courseNames;
    }
}
