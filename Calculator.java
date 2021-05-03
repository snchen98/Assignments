package com.Capgemini;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        boolean loop = true;
        Scanner input = new Scanner(System.in);
        while(loop) {
            System.out.println("1. Addition\n" +
                           "2. Subtraction\n" +
                           "3. Multiplication\n" +
                           "4. Division\n" +
                           "5. Exit\n" +
                           "Enter your choice: ");
            int choice = input.nextInt();
            if (choice == 5) {
                break;
            }
            System.out.println("Enter two numbers:");
            int num1 = input.nextInt();
            int num2 = input.nextInt();
            System.out.print("Answer: ");
            switch(choice) {
                case 1:
                    System.out.println(num1 + num2);
                    break;
                case 2:
                    System.out.println(num1 - num2);
                    break;
                case 3:
                    System.out.println(num1 * num2);
                    break;
                case 4:
                    System.out.println(num1/(double)num2);
                    break;
            }
        }
        input.close();
    }
}
