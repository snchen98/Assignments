package com.capgemini.service;

import com.capgemini.beans.Account;

public interface AccountServiceInterface {
    public Account getAccount(int accountNumber) throws Exception;
    public String createAccount(int accountNumber, double startBalance) throws Exception;
    public String depositAmount(int accountNumber, double amount) throws Exception;
    public String withdrawAmount(int accountNumber, double amount) throws Exception;
    public String fundTransfer(int originAccountNumber, int destinationAccountNumber, double amount) throws Exception;
}
