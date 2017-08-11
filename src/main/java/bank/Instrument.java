package bank;

/**
 * Created by RENT on 2017-08-11.
 */
public class Instrument {
    private int ammount;
    private Currency currency;

    public Instrument() {
    }

    public Instrument(int ammount, Currency currency) {
        this.ammount = ammount;
        this.currency = currency;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
