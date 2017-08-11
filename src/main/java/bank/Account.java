package bank;

/**
 * Tdd training on 11.08.17.
 */
public class Account {
    private int id;
    private int balanceAccount;

    public Account(int id, int balanceAccount) {
        this.id = id;
        this.balanceAccount = balanceAccount;
    }

    public int getId() {
        return id;
    }

    public int getBalanceAccount() {
        return balanceAccount;
    }

    public void setBalanceAccount(int balanceAccount) {
        this.balanceAccount = balanceAccount;
    }
}
