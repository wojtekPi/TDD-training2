package bank;

/**
 * Created by Filip on 2017-08-11.
 */
public class PaymentService {

    public void transferMoney(Account accountOne, Account accountTwo, int moneyToTransfer) {

        accountOne.setBalance(accountOne.getBalance()-moneyToTransfer);
        accountTwo.setBalance(accountTwo.getBalance()+moneyToTransfer);
    }


}
