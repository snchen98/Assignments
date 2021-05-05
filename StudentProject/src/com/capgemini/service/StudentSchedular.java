package com.capgemini.service;
import java.util.Hashtable;
import java.util.Set;
import java.util.ArrayList;

import com.capgemini.beans.Student;

public class StudentSchedular {
	private int maxStudents;
	private Student[] students;
	private int counterStudent;
    private Hashtable<String, ArrayList<Student>> courses;

    public StudentSchedular () {
        this.maxStudents = 10;
        this.students = new Student[this.maxStudents];
        this.courses = new Hashtable<String, ArrayList<Student>>();
    }
	
	public String addStudent(int rollNumber,String name, String[] courseNames)
	{
        if (this.counterStudent == this.maxStudents) {
            return "Maximum students registered";
        }
        if (getStudentByRollNumber(rollNumber) != null) {
            return "Roll Number already exists";
        }
        Student student = new Student(rollNumber,name, courseNames);
		students[counterStudent++] = student;
        updateCourseNames(courseNames, student);

		return "Student added successfully";
	}

    public int getStudentCounter() {
        return this.counterStudent;
    }
	
	public Student[] getAllStudents()
	{
        return this.students;
	}


    public Student getStudentByRollNumber(int rollNumber) {
        for (int i = 0; i < this.counterStudent; i++) {
            if (rollNumber == students[i].getRollNumber()) {
                return students[i];
            }
        }
        return null;
    }

    public String[] getAllCourseNames() {
        Set<String> keys = this.courses.keySet();
        String[] courseNames = new String[keys.size()];
        keys.toArray(courseNames);
        return courseNames;
    }

    public ArrayList<Student> getStudentsInCourse(String courseName) {
        return this.courses.get(courseName);
    }

    public boolean courseNameExists(String courseName, String[] courseNames) {
        for (int i = 0; i < courseNames.length; i++) {
            if (courseNames[i].equalsIgnoreCase(courseName)) {
                return true;
            }
        }
        return false;
    }

    private void updateCourseNames(String[] courseNames, Student student) {
        Set<String> keys = this.courses.keySet();
        String[] currentCourseNames = new String[keys.size()];
        for (int i = 0; i < courseNames.length; i++) {
            // Add student under the course
            if (courseNameExists(courseNames[i], currentCourseNames)) {
                this.courses.get(courseNames[i]).add(student);
            }
            // Create new course and add student
            else {
                ArrayList<Student> students = new ArrayList<Student>();
                students.add(student);
                this.courses.put(courseNames[i].toLowerCase(), students);
            }
        }
    }
}