package com.capgemini.ui;
import java.util.ArrayList;
import java.util.Scanner;
import com.capgemini.service.StudentSchedular;
import com.capgemini.beans.Student;

public class App {
    private static Scanner sc=new Scanner(System.in);
    private static StudentSchedular studSch = new StudentSchedular();
    public static void main(String[] args) throws Exception {
       showMenu();
    }
    private static void showMenu() {
            
        int choice;
        
        while(true) {
            System.out.println("1.Add student");
            System.out.println("2.Show all students");
            System.out.println("3.Show student details with ___ roll number");
            System.out.println("4.Show all students in ___ course");
            System.out.println("5.Exit");
            
            System.out.println("Enter your choice");
            choice=sc.nextInt();
            
            
            switch(choice) {
                case 1: addStudents();
                        break;
                        
                case 2: showAllStudents();
                        break;
                case 3: System.out.print("Please enter roll number: ");
                        int rollNumber = sc.nextInt();
                        showStudentDeatils(rollNumber);
                        break;
                case 4:
                        listCourseNames();
                        break;
                case 5: sc.close();
                        System.exit(0);
                default:System.out.println("Sorry entered wrong choice");
            }

            System.out.println();
            
        }
    }

    private static void addStudents() {
        System.out.println("Enter roll number");
		int rollNumber=sc.nextInt();
		
		System.out.println("Enter name");
		String name = sc.next();
		
        System.out.println("How many courses do you want to register?");
        int num = sc.nextInt();
        System.out.println("Please enter course names");
        String[] courseNames = new String[num];
        int i = 0;
        while (i < num) {
            System.out.print("Course name: ");
            String courseName = sc.next();
            courseNames[i++] = courseName;
        }

		System.out.println(studSch.addStudent(rollNumber, name, courseNames));
    }

    private static void showAllStudents() {
        Student[] students = studSch.getAllStudents();
        int numOfStudents = studSch.getStudentCounter();
        for (int i = 0; i < numOfStudents; i++) {
            System.out.println("Student Name: " + students[i].getName() + ", Roll#: " + students[i].getRollNumber());
        }
    }

    private static void showStudentDeatils(int rollNumber) {
        Student student = studSch.getStudentByRollNumber(rollNumber);
        if (student != null) {
            System.out.println("Student Name: " + student.getName() + ", Roll#: " + student.getRollNumber());
            System.out.println("Courses: ");
            String[] courseNames = student.getCourses();
            for (int i = 0; i < courseNames.length; i++) {
                if (courseNames[i] == null) {
                    break;
                }
                System.out.println(courseNames[i]);
            }
        }
        else {
            System.out.println("Could not find any student with roll number: " + rollNumber);
        }
    }

    private static void listCourseNames() {
        String choice = "";
        while(true) {
            System.out.println("Please enter a course name from below or 1 to exit:");
            String[] courseNames = studSch.getAllCourseNames();
            if (courseNames.length == 0) {
                System.out.println("No courses registered currently");
                return;
            }
            for (int i = 0; i < courseNames.length; i++) {
                System.out.println(courseNames[i]);
            }
            System.out.print("Input: ");
            choice = sc.next();
            if (choice == "1") {
                return;
            }
            if (studSch.courseNameExists(choice, courseNames)) {
                printStudentDetails(studSch.getStudentsInCourse(choice.toLowerCase()));
                break;
            }
            else {
                System.out.println("Invalid choice");
            }
        }
    }

    private static void printStudentDetails(ArrayList<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            System.out.println("Student Name: " + students.get(i).getName() + ", Roll#: " + students.get(i).getRollNumber());
        }
    }
}
