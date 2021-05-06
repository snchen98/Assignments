package com.capgemini.repository;
import java.util.HashMap;
import com.capgemini.beans.Account;

public class AccountRepo implements AccountRepoInterface {
    private HashMap<Integer, Account> accounts;

    public AccountRepo() {
        this.accounts = new HashMap<>();
    }
    
    public Account addAccount(int accountNumber, double startBalance) {
        if (accountExists(accountNumber)) {
            return null;
        }
        this.accounts.put(accountNumber, new Account(accountNumber, startBalance));
        return this.accounts.get(accountNumber);
    }

    public Account getAccount(int accountNumber) {
        return this.accounts.get(accountNumber);
    }

    public Account updateAccountBalance(int accountNumber, double newBalance) {
        Account account = this.accounts.get(accountNumber);
        if (!accountExists(accountNumber)) {
            return null;
        }
        account.setBalance(newBalance);
        return account;
    }

    public Account updateAccountNumber(int accountNumber, int newAccountNumber) {
        if (!accountExists(accountNumber) || accountExists(newAccountNumber)) {
            return null;
        }
        Account account = this.accounts.get(accountNumber);
        account.setAccountNumber(newAccountNumber);
        return account;
    }

    public boolean accountExists(int accountNumber) {
        return this.accounts.containsKey(accountNumber);
    }
}
