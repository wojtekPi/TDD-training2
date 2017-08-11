package bank;

/**
 * Created by RENT on 2017-08-11.
 */
public class PaymentService {

    public void transferMoney(Account from, Account to, int howMany) throws AccountException {
        if(from.getBalanceAccount()< howMany){
            throw new AccountException("Not enough money on account");
        }

        from.setBalanceAccount(from.getBalanceAccount()-howMany);
        to.setBalanceAccount(to.getBalanceAccount()+howMany);
        System.out.println("Przelales "+howMany+"zl na konto: "+to.getId());

    }
}
