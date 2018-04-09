package cpsc2150.Lab2;

/*
 * CPSC 2150 Lab1
 *
 */

public class BankApp {
    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount("John Smith", "454-909765-331", 200.00);
        BankAccount acc2 = new BankAccount("Henrietta Lacks", "474-579468-513", 350.00);
        BankAccount acc3 = new BankAccount("Ada Lovelace", "867-530986-753", 175.00);
        BankAccount acc4 = acc2;
        BankAccount acc5 = new BankAccount("John Smith", "454-909765-331", 200.00);
        BankAccount acc6 = new BankAccount("John Smith", "454-909765-331", 200.00);
        acc1.deposit(250.0);
        if(acc1.equals(acc5))
        {
            acc5.deposit(300.0);
        }
        if(acc5 == acc6)
        {
            acc4.withdraw(50.0);
        }
        acc2.withdraw(100.0);
        if(acc2 == acc4)
        {
            acc4 = acc1;
        }
        if(acc5.equals(acc6))
        {
            System.out.println("Duplicate Accounts!");
        }
        System.out.println("Account 1\n" + acc1.toString());
        System.out.println("Account 2\n" + acc2.toString());
        System.out.println("Account 3\n" + acc3.toString());
        System.out.println("Account 4\n" + acc4.toString());
        System.out.println("Account 5\n" + acc5.toString());
        System.out.println("Account 6\n" + acc6.toString());
    }
}
