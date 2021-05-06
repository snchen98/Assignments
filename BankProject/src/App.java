import com.capgemini.beans.Bank;

public class App {
    public static void main(String[] args) throws Exception {
        Bank bank = new Bank();
        int accountNumber = 123456789;
        int accountNumber2 = 987654321;

        System.out.println("Create new account with insufficient balance");
        System.out.println(bank.createAccount(accountNumber, 300) + "\n");

        System.out.println("Create new account with enough balance");
        System.out.println(bank.createAccount(accountNumber, 500) + "\n");

        System.out.println("Creating new account with same number");
        System.out.println(bank.createAccount(accountNumber, 500) + "\n");
        
        System.out.println("Check our balance (should be 500)");
        System.out.println(bank.checkAccountBalance(accountNumber)  + "\n");

        System.out.println("Depost 100 into our bank account (new balance should be 600)");
        System.out.println(bank.deposit(accountNumber, 100) + "\n");

        System.out.println("Deposit 100 into a non-existent account");
        System.out.println(bank.deposit(73294, 100) + "\n");

        System.out.println("Withdraw 100");
        System.out.println(bank.withdraw(accountNumber, 100) + "\n");

        System.out.println("Withdraw from non-existent account");
        System.out.println(bank.withdraw(3872934, 100) + "\n");

        System.out.println("Withdraw 10000");
        System.out.println(bank.withdraw(accountNumber, 10000) + "\n");

        System.out.println("Transfer 100 to another account that doesn't exist");
        System.out.println(bank.transfer(accountNumber, accountNumber2, 100) + "\n");

        System.out.println("Transfer 100 from account that doesn't exist");
        System.out.println(bank.transfer(1234, accountNumber, 100) + "\n");

        System.out.println("Transfer 100 to another account");
        System.out.println("Creating second account with 1000");
        System.out.println(bank.createAccount(accountNumber2, 1000));
        System.out.println(bank.transfer(accountNumber, accountNumber2, 100) + "\n");
    }
}
