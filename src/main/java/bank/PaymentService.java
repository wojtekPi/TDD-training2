package bank;

/**
 * Created by Filip on 2017-08-11.
 */
public class PaymentService {

    public void transferMoney(Account accountOne, Account accountTwo, Instrument moneyToTransfer) throws IllegalArgumentException {

        if(accountOne.getBalance().getCurrency() != accountTwo.getBalance().getCurrency() ||
                accountOne.getBalance().getCurrency() != moneyToTransfer.getCurrency())
            throw new IllegalArgumentException();

        if(accountOne.getBalance().getAmount() - moneyToTransfer.getAmount() < -500 )
            throw new IllegalArgumentException();

        accountOne.setBalance(new Instrument(accountOne.getBalance().getAmount()-moneyToTransfer.getAmount(), moneyToTransfer.getCurrency()));
        accountTwo.setBalance(new Instrument(accountTwo.getBalance().getAmount()+moneyToTransfer.getAmount(), moneyToTransfer.getCurrency()));
    }


}
