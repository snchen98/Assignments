package com.capgemini.beans;
import com.capgemini.service.AccountService;

public class Bank {
    private AccountService accService;

    public Bank() {
        this.accService = new AccountService();
    }

    public String createAccount(int accountNumber, double startBalance) {
        try {
            return this.accService.createAccount(accountNumber, startBalance);
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public String checkAccountBalance(int accountNumber) {
        try {
            Account account = this.accService.getAccount(accountNumber);
            return "" + account.getBalance();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public String deposit(int accountNumber, double amount) {
        try {
            return this.accService.depositAmount(accountNumber, amount);
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public String withdraw(int accountNumber, double amount) {
        try {
            return this.accService.withdrawAmount(accountNumber, amount);
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public String transfer(int originAccountNumber, int destinationAccountNumber, double amount) {
        try {
            return this.accService.fundTransfer(originAccountNumber, destinationAccountNumber, amount);
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
