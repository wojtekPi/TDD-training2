package bank;

/**
 * Tdd training on 11.08.17.
 */
public class Account {
    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    private int id;
    private int balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
