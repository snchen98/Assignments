package com.capgemini.exceptions;

public class InsufficientBalance extends Exception {
    public InsufficientBalance(double balance) {
        super("Insufficient Balance\nCurrent Balance: " + balance);
    }
}
