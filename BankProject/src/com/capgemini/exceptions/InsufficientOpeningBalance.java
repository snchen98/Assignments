package com.capgemini.exceptions;

public class InsufficientOpeningBalance extends Exception {
    public InsufficientOpeningBalance(double min) {
        super("You need a minimum of $" + min + " to open an account");
    }
}
