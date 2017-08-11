package bank;

/**
 * Created by RENT on 2017-08-11.
 */
public class ExchangeRatio {
    private int pln;
    private int usd;



    public int getPln() {
        return pln;
    }

    public void setPln(int pln) {
        this.pln = pln;
    }

    public int getUsd() {
        return usd;
    }

    public void setUsd(int usd) {
        this.usd = usd;
    }

    public int changeUSDtoPLN(int value){
        return value*4;

    }
}
