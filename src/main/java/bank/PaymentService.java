package bank;

/**
 * Created by Filip on 2017-08-11.
 */
public class PaymentService {

    public void transferMoney(Account accountOne, Account accountTwo, Instrument money ) throws IllegalArgumentException {

            if(!((money.getCurrency() == accountOne.getCurrency()) && (money.getCurrency() == accountTwo.getCurrency()))){

                throw new IllegalArgumentException();
            }

            accountOne.setBalance(accountOne.getBalance() - money.getAmount());
            accountTwo.setBalance(accountTwo.getBalance() + money.getAmount());

            if(accountOne.getBalance()<-500){

                throw new IllegalArgumentException();

            }

        }
    }




