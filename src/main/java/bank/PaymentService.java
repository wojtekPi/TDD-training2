package bank;

/**
 * Created by Filip on 2017-08-11.
 */
public class PaymentService {

    public void transferMoney(Account accountOne, Account accountTwo, Instrument moneyToTransfer) {

//        accountOne.setBalance(accountOne.getBalance()-moneyToTransfer);
//        if(accountOne.getBalance() < -500){
//            throw new IllegalArgumentException("I'm very sorry, but you don't have enough money...");
//        }
//        accountTwo.setBalance(accountTwo.getBalance()+moneyToTransfer);




        accountOne.getBalance().setAmount(accountOne.getBalance().getAmount()-moneyToTransfer.getAmount());
        if(accountOne.getBalance().getAmount() < -500){
            throw new IllegalArgumentException("I'm very sorry, but you don't have enough money...");
        } else if(accountOne.getBalance().getCurrency() != accountTwo.getBalance().getCurrency()){
            throw new IllegalArgumentException("Incorrect currency");
        }
        accountTwo.getBalance().setAmount(accountTwo.getBalance().getAmount()+moneyToTransfer.getAmount());
    }
}
