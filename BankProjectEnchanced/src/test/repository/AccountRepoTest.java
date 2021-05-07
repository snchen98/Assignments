package test.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capgemini.beans.Account;
import com.capgemini.repository.AccountRepo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class AccountRepoTest {
    AccountRepo accRepo;
    String accShouldNotExistMsg;
    String accNotExistMsg;
    String accNumberNotMatchMsg;
    String accBalanceNotMatchMsg;
    String accNotCreatedMsg;

    @BeforeEach
    public void init() {
        accRepo = new AccountRepo();
        accShouldNotExistMsg = "Account is not supposed to exist";
        accNotExistMsg = "Account does not exist";
        accNumberNotMatchMsg = "Account number does not match";
        accBalanceNotMatchMsg = "Balance does not match";
    }

    @Test
    public void addAccountSuccess() {
        int accountNumber = 123;
        double startBalance = 500;
        Account account = accRepo.addAccount(accountNumber, startBalance);
        assertTrue(account != null, accNotExistMsg);
        assertEquals(accountNumber, account.getAccountNumber(), accNumberNotMatchMsg);
        assertEquals(startBalance, account.getBalance(), accBalanceNotMatchMsg);
    }

    @Test
    public void addAccountFailAccountExists() {
        int accountNumber = 123;
        double startBalance = 100;
        accRepo.addAccount(accountNumber, startBalance);
        Account account = accRepo.addAccount(accountNumber, startBalance);
        assertTrue(account == null, "Account was created when it already exists");
    }

    @Test
    public void getAccountSuccess() {
        int accountNumber = 123;
        double startBalance = 500;
        accRepo.addAccount(accountNumber, startBalance);
        Account account = accRepo.getAccount(accountNumber);
        assertTrue(account != null, "Account was not retrieved");
        assertEquals(accountNumber, account.getAccountNumber(), accNumberNotMatchMsg);
        assertEquals(startBalance, account.getBalance(), accBalanceNotMatchMsg);
    }

    @Test
    public void getAccountFail() {
        int accountNumber = 123;
        Account account = accRepo.getAccount(accountNumber);
        assertTrue(account == null, accShouldNotExistMsg);
    }

    @Test
    public void updateAccountBalanceSuccess() {
        int accountNumber = 123;
        double startBalance = 500;
        double newBalance = 600;
        accRepo.addAccount(accountNumber, startBalance);
        Account account = accRepo.updateAccountBalance(accountNumber, newBalance);
        assertTrue(account != null, accNotExistMsg);
        assertEquals(account.getBalance(), newBalance, "Updating account balance failed");
    }

    @Test
    public void updateAccountBalanceFailNonexistentAccount() {
        int accountNumber = 123;
        double newBalance = 600;
        Account account = accRepo.updateAccountBalance(accountNumber, newBalance);
        assertTrue(account == null, accShouldNotExistMsg);
    }

    @Test
    public void updateAccountNumberSuccess() {
        int accountNumber = 123;
        double startBalance = 500;
        int newAccountNumber = 321;
        accRepo.addAccount(accountNumber, startBalance);
        Account account = accRepo.updateAccountNumber(accountNumber, newAccountNumber);
        assertTrue(account != null, accNotExistMsg);
        assertEquals(account.getAccountNumber(), newAccountNumber, "Updating account number failed");
    }

    @Test
    public void updateAccountNumberFailNonexistentAccount() {
        int accountNumber = 123;
        int newAccountNumber = 321;
        Account account = accRepo.updateAccountNumber(accountNumber, newAccountNumber);
        assertTrue(account == null, accShouldNotExistMsg);
    }

    public void updateAccountNumberFailNewAccountNumberAlreadyExists() {
        int accountNumber = 123;
        double startBalance = 500;
        int newAccountNumber = 321;
        accRepo.addAccount(accountNumber, startBalance);
        accRepo.addAccount(newAccountNumber, startBalance);
        Account account = accRepo.updateAccountNumber(accountNumber, newAccountNumber);
        assertTrue(account == null, accShouldNotExistMsg);
    }

    @Test
    public void accountExistsSuccess() {   
        int accountNumber = 123;
        int startBalance = 500;
        Account account = accRepo.getAccount(accountNumber);
        assertTrue(account == null, accShouldNotExistMsg);
        accRepo.addAccount(accountNumber, startBalance);
        account = accRepo.getAccount(accountNumber);
        assertTrue(account != null, "Account is supposed to exist");
    }
}
