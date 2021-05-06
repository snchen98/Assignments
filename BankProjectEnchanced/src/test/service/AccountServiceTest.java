package test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.AccountAlreadyExist;
import com.capgemini.exceptions.InsufficientBalance;
import com.capgemini.exceptions.InsufficientOpeningBalance;
import com.capgemini.exceptions.InvalidAccountNumber;
import com.capgemini.service.AccountService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class AccountServiceTest {
    static AccountService accService;

    @BeforeEach
    public void init() {
        accService = new AccountService();
    }

    @Test
    public void getAccount_Success() throws Exception {
        int accountNumber = 123;
        double startBalance = 500;
        accService.createAccount(accountNumber, startBalance);
        Account account = accService.getAccount(accountNumber);
        assertTrue(account != null, "Account does not exist");
        assertEquals(accountNumber, account.getAccountNumber(), "Account number does not match");
        assertEquals(startBalance, account.getBalance(), "Balance does not match");
    };

    public void getAccount_Fail_InvalidAccountNumber() {
        int accountNumber = 123;
        assertThrows(InvalidAccountNumber.class, () -> {
            accService.getAccount(accountNumber);
        });
    };

    @Test
    public void createAccount_Success() throws Exception {
        int accountNumber = 123;
        double startBalance = 500;
        String message = accService.createAccount(accountNumber, startBalance);
        assertEquals(message, "Account successfully created", "Account creation failed");
    }

    @Test
    public void createAccount_Fail_AccountAlreadyExist() {
        int accountNumber = 123;
        double startBalance = 500;
        assertThrows(AccountAlreadyExist.class, () -> {
            accService.createAccount(accountNumber, startBalance);
            accService.createAccount(accountNumber, startBalance);
        });
    }

    @Test
    public void createAccount_Fail_InsufficientOpeningBalance() {
        int accountNumber = 123;
        double startBalance = 100;
        assertThrows(InsufficientOpeningBalance.class, () -> {
            accService.createAccount(accountNumber, startBalance);
        });
    }

    @Test
    public void depositAmount_Success() throws Exception {
        int accountNumber = 123;
        double startBalance = 500;
        accService.createAccount(accountNumber, startBalance);
        accService.depositAmount(accountNumber, startBalance);
        Account account = accService.getAccount(accountNumber);
        assertEquals(account.getBalance(), 2*startBalance, "Balance does not match expected");
    }

    @Test
    public void depositAmount_Fail_InvalidAccountNumber() {
        int accountNumber = 123;
        double startBalance = 500;
        assertThrows(InvalidAccountNumber.class, () -> {
            accService.depositAmount(accountNumber, startBalance);
        });
    }

    @Test
    public void withdrawAmount_Success() throws Exception{
        int accountNumber = 123;
        double startBalance = 500;
        double amount = 100;
        accService.createAccount(accountNumber, startBalance);
        String message = accService.withdrawAmount(accountNumber, amount);
        assertEquals(message, "Withdrawal successful\nNewBalance: " + (startBalance-amount));
    }

    @Test
    public void withdrawAmount_Fail_InvalidAccountNumber() {
        int accountNumber = 123;
        double amount = 100;
        assertThrows(InvalidAccountNumber.class, () -> {
            accService.withdrawAmount(accountNumber, amount);
        });
    }

    @Test
    public void withdrawAmount_Fail_InsufficientBalance() {
        int accountNumber = 123;
        double startBalance = 500;
        double amount = 600;
        assertThrows(InsufficientBalance.class, () -> {
            accService.createAccount(accountNumber, startBalance);
            accService.withdrawAmount(accountNumber, amount);
        });
    }

    @Test
    public void fundTransfer_Success() throws Exception {
        int accountNumber = 123;
        int accountNumber2 = 321;
        double startBalance = 500;
        double amount = 100;
        accService.createAccount(accountNumber, startBalance);
        accService.createAccount(accountNumber2, startBalance);
        accService.fundTransfer(accountNumber, accountNumber2, amount);
        Account acc1 = accService.getAccount(accountNumber);
        Account acc2 = accService.getAccount(accountNumber2);
        assertEquals(acc1.getBalance(), startBalance - amount, "Origin account balance is not correct");
        assertEquals(acc2.getBalance(), startBalance + amount, "Destination account balance is not correct");
    }

    @Test
    public void fundTransfer_Fail_InvalidAccountNumber_Origin() {
        int accountNumber = 123;
        int accountNumber2 = 321;
        double startBalance = 500;
        double amount = 100;
        assertThrows(InvalidAccountNumber.class, () -> {
            accService.createAccount(accountNumber2, startBalance);
            accService.fundTransfer(accountNumber, accountNumber2, amount);
        });
    }

    @Test
    public void fundTransfer_Fail_InvalidAccountNumber_Destination() {
        int accountNumber = 123;
        int accountNumber2 = 321;
        double startBalance = 500;
        double amount = 100;
        assertThrows(InvalidAccountNumber.class, () -> {
            accService.createAccount(accountNumber, startBalance);
            accService.fundTransfer(accountNumber, accountNumber2, amount);
        });
    }

    @Test
    public void fundTransfer_Fail_InsufficientBalance() {
        int accountNumber = 123;
        int accountNumber2 = 321;
        double startBalance = 500;
        double amount = 600;
        assertThrows(InsufficientBalance.class, () -> {
            accService.createAccount(accountNumber, startBalance);
            accService.createAccount(accountNumber2, startBalance);
            accService.fundTransfer(accountNumber, accountNumber2, amount);
        });
    }
}
