package com.capgemini.service;
import com.capgemini.exceptions.AccountAlreadyExist;
import com.capgemini.exceptions.InsufficientBalance;
import com.capgemini.exceptions.InsufficientOpeningBalance;
import com.capgemini.exceptions.InvalidAccountNumber;
import com.capgemini.repository.AccountRepo;
import com.capgemini.beans.Account;

public class AccountService implements AccountServiceInterface {
    public AccountRepo accRepo;

    public AccountService() {
        accRepo = new AccountRepo();
    }

    public Account getAccount(int accountNumber) throws InvalidAccountNumber {
        Account account = this.accRepo.getAccount(accountNumber);
        if (account == null) {
            throw new InvalidAccountNumber(accountNumber);
        }
        return account;
    }

    public String createAccount(int accountNumber, double startBalance) throws AccountAlreadyExist, InsufficientOpeningBalance {
        double minBalance = 500;
        if (startBalance < minBalance) {
            throw new InsufficientOpeningBalance(minBalance);
        }
        if (this.accRepo.accountExists(accountNumber)) {
            throw new AccountAlreadyExist(accountNumber);
        }
        Account account = this.accRepo.addAccount(accountNumber, startBalance);
        if (account == null) {
            return "Failed to create account";
        }
        return "Account successfully created";
    }

    public String depositAmount(int accountNumber, double amount) throws InvalidAccountNumber {
        if (!this.accRepo.accountExists(accountNumber)) {
            throw new InvalidAccountNumber(accountNumber);
        }
        Account account = this.accRepo.getAccount(accountNumber);
        double newBalance = account.getBalance() + amount;
        this.accRepo.updateAccountBalance(accountNumber, newBalance);
        if (account.getBalance() == newBalance) {
            return "Deposit successful\nNew Balance: " + account.getBalance();
        }
        return "Deposit failed";
    }

    public String withdrawAmount(int accountNumber, double amount) throws InvalidAccountNumber, InsufficientBalance {
        if (!this.accRepo.accountExists(accountNumber)) {
            throw new InvalidAccountNumber(accountNumber);
        }
        Account account = this.accRepo.getAccount(accountNumber);
        double balance = account.getBalance();
        if (balance < amount) {
            throw new InsufficientBalance(balance);
        }
        double newBalance = balance - amount;
        this.accRepo.updateAccountBalance(accountNumber, newBalance);
        if (account.getBalance() == newBalance) {
            return "Withdrawal successful\nNewBalance: " + account.getBalance();
        }
        return "Withdrawal failed";
    }

    public String fundTransfer(int originAccountNumber, int destinationAccountNumber, double amount) throws InvalidAccountNumber, InsufficientBalance {
        if (!this.accRepo.accountExists(originAccountNumber)) {
            throw new InvalidAccountNumber(originAccountNumber);
        }
        if (!this.accRepo.accountExists(destinationAccountNumber)) {
            throw new InvalidAccountNumber(destinationAccountNumber);
        }
        Account oAccount = this.accRepo.getAccount(originAccountNumber);
        Account dAccount = this.accRepo.getAccount(destinationAccountNumber);
        double oBalance = oAccount.getBalance();
        if (oBalance < amount) {
            throw new InsufficientBalance(oBalance);
        }
        double dBalance = dAccount.getBalance();
        double newOBalance = oBalance - amount;
        double newDBalance = dBalance + amount;
        this.accRepo.updateAccountBalance(originAccountNumber, newOBalance);
        this.accRepo.updateAccountBalance(destinationAccountNumber, newDBalance);
        if (oAccount.getBalance() == newOBalance && dAccount.getBalance() == newDBalance) {
                return "Transfer sucessful\nYour Balance: " + oAccount.getBalance();
        }
        return "Transfer failed";
    }
}
