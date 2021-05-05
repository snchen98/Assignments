package com.capgemini.repository;
import com.capgemini.beans.Account;

public interface AccountRepoInterface {
    public Account addAccount(int accountNumber, double startBalance);
    public Account getAccount(int accountNumber);
    public Account updateAccountBalance(int accountNumber, double newBalance);
    public Account updateAccountNumber(int accountNumber, int newAccountNumber);
    public boolean accountExists(int accountNumber);
}
