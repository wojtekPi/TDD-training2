package bank;

/**
 * Created by Filip on 2017-08-11.
 */
public class PaymentService {

    public void transferMoney(Account accountOne, Account accountTwo, int moneyToTransfer) {
        if ((accountOne.getBalance().getCurrency() == accountTwo.getBalance().getCurrency()) &&
        (accountOne.getBalance().getAmount() > moneyToTransfer)) {

            accountOne.getBalance().setAmount(accountOne.getBalance().getAmount()-moneyToTransfer);
            accountTwo.getBalance().setAmount(accountTwo.getBalance().getAmount()+moneyToTransfer);
        } else { throw new IllegalArgumentException("Bida"); }
    }
}
