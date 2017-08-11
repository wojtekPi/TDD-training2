package bank;

/**
 * Created by Filip on 2017-08-11.
 */
public class PaymentService {

    public void transferMoney(Account accountOne, Account accountTwo, Instrument moneyToTransfer) {
        if(accountOne.getBalance().getCurrency() == accountTwo.getBalance().getCurrency() &&
                accountOne.getBalance().getCurrency() == moneyToTransfer.getCurrency()) {
            accountOne.getBalance().setAmmount(accountOne.getBalance().getAmmount() - moneyToTransfer.getAmmount());
            accountTwo.getBalance().setAmmount(accountTwo.getBalance().getAmmount() + moneyToTransfer.getAmmount());
        } else{
            throw new IllegalArgumentException("Wrong currency");
        }

        if(accountOne.getBalance().getAmmount() - moneyToTransfer.getAmmount() < -500) {
            throw new IllegalArgumentException("I'm very sorry, but you don't have enough money...");
        }

    }


}
