package test.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capgemini.beans.Account;
import com.capgemini.repository.AccountRepo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class AccountRepoTest {
    static AccountRepo accRepo;

    @BeforeEach
    public void init() {
        accRepo = new AccountRepo();
    }

    @Test
    public void addAccount_Success() {
        int accountNumber = 123;
        double startBalance = 500;
        Account account = accRepo.addAccount(accountNumber, startBalance);
        assertTrue(account != null, "Account does not exist");
        assertEquals(accountNumber, account.getAccountNumber(), "Account number does not match");
        assertEquals(startBalance, account.getBalance(), "Balance does not match");
    }

    @Test
    public void addAccount_Fail_AccountExists() {
        int accountNumber = 123;
        double startBalance = 100;
        Account account = accRepo.addAccount(accountNumber, startBalance);
        account = accRepo.addAccount(accountNumber, startBalance);
        assertTrue(account == null, "Account was created when it already exists");
    }

    @Test
    public void getAccount_Success() {
        int accountNumber = 123;
        double startBalance = 500;
        accRepo.addAccount(accountNumber, startBalance);
        Account account = accRepo.getAccount(accountNumber);
        assertTrue(account != null, "Account was not retrieved");
        assertEquals(accountNumber, account.getAccountNumber(), "Account number does not match");
        assertEquals(startBalance, account.getBalance(), "Balance does not match");
    }

    @Test
    public void getAccount_Fail() {
        int accountNumber = 123;
        Account account = accRepo.getAccount(accountNumber);
        assertTrue(account == null, "Account is not supposed to exist");
    }

    @Test
    public void updateAccountBalance_Success() {
        int accountNumber = 123;
        double startBalance = 500;
        double newBalance = 600;
        accRepo.addAccount(accountNumber, startBalance);
        Account account = accRepo.updateAccountBalance(accountNumber, newBalance);
        assertTrue(account != null, "Account does not exist");
        assertEquals(account.getBalance(), newBalance, "Updating account balance failed");
    }

    @Test
    public void updateAccountBalance_Fail_NonexistentAccount() {
        int accountNumber = 123;
        double newBalance = 600;
        Account account = accRepo.updateAccountBalance(accountNumber, newBalance);
        assertTrue(account == null, "Account is not supposed to exist");
    }

    @Test
    public void updateAccountNumber_Success() {
        int accountNumber = 123;
        double startBalance = 500;
        int newAccountNumber = 321;
        accRepo.addAccount(accountNumber, startBalance);
        Account account = accRepo.updateAccountNumber(accountNumber, newAccountNumber);
        assertTrue(account != null, "Account does not exist");
        assertEquals(account.getAccountNumber(), newAccountNumber, "Updating account number failed");
    }

    @Test
    public void updateAccountNumber_Fail_NonexistentAccount() {
        int accountNumber = 123;
        int newAccountNumber = 321;
        Account account = accRepo.updateAccountNumber(accountNumber, newAccountNumber);
        assertTrue(account == null, "Account is not supposed to exist");
    }

    public void updateAccountNumber_Fail_NewAccountNumberAlreadyExists() {
        int accountNumber = 123;
        double startBalance = 500;
        int newAccountNumber = 321;
        accRepo.addAccount(accountNumber, startBalance);
        accRepo.addAccount(newAccountNumber, startBalance);
        Account account = accRepo.updateAccountNumber(accountNumber, newAccountNumber);
        assertTrue(account == null, "Account is not supposed to exist");
    }

    @Test
    public void accountExists_Success() {   
        int accountNumber = 123;
        int startBalance = 500;
        Account account = accRepo.getAccount(accountNumber);
        assertTrue(account == null, "Account is not supposed to exist");
        accRepo.addAccount(accountNumber, startBalance);
        account = accRepo.getAccount(accountNumber);
        assertTrue(account != null, "Account is supposed to exist");
    }
}
