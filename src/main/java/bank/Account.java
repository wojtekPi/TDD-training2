package bank;

/**
 * Tdd training on 11.08.17.
 */
public class Account {

    private int id;
    private Instrument balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Instrument getBalance() {
        return balance;
    }

    public void setBalance(Instrument balance) {
        this.balance = balance;
    }
}
