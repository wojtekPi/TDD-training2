package bank;

/**
 * Tdd training on 11.08.17.
 */
public class Account {

    private int id;
    private int balance;

    public Account() {
        balance = 0;
    }

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
