package cpsc2150.Lab2;

/*
 * CPSC 2150 Lab1
 * Author: Rafael Dejesus
 */

public class BankAccount {
    private final String
            holderName,
            uidNumber;
    private double balance;

    /**
     * @param holderName the name of the account holder
     * @param uidNumber the number of the account
     * @param initialBalance the initial balance of the account
     * @requires holderName != null and
     * uidNumber != null
     */
    public BankAccount(String holderName, String uidNumber, double initialBalance) {
        this.holderName = holderName;
        this.uidNumber = uidNumber;
        this.balance = initialBalance;
    }

    /**
     * @param amountToDeposit the amount to deposit
     * @requires amountToDeposit >= 0
     * @ensures balance = #balance + amountToDeposit
     */
    public void deposit(double amountToDeposit) {
        balance += amountToDeposit;
    }

    /**
     * @param amountToWithdraw the amount to withdraw
     * @requires amountToWithdraw > 0 and
     * amountToWithdraw < balance
     * @ensures balance = #balance - amountToWithdraw
     */
    public void withdraw(double amountToWithdraw) {
        balance -= amountToWithdraw;
    }

    /**
     * @param obj the object to compare to
     * @return true if the Bank Accounts contain the same information
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BankAccount))
            return false;
        BankAccount otherAccount = (BankAccount) obj;
        if (!this.holderName.equals(otherAccount.holderName))
            return false;
        if (!this.uidNumber.equals(otherAccount.uidNumber))
            return false;
        if (this.balance != otherAccount.balance)
            return false;
        return true;
    }

    /**
     * @return the data stored in the account in a readable form
     */
    @Override
    public String toString() {
        return "Account holder name: " + holderName + "\n" +
                "Account number: " + uidNumber + "\n" +
                "Balance: $" + balance;
    }
}
