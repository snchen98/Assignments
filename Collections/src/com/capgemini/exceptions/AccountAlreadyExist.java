package com.capgemini.exceptions;

public class AccountAlreadyExist extends Exception {
    public AccountAlreadyExist(int accountNumber) {
        super(accountNumber + " already exists");
    }
}
