package com.capgemini.exceptions;

public class InvalidAccountNumber extends Exception {
    public InvalidAccountNumber(int accountNumber) {
        super(accountNumber + " does not exist");
    }
}
