package bank;

public class PaymentService {

    public void transferMOney(Account fromAccount, Account toAccount, int howMany){
        fromAccount.setBalance(fromAccount.getBalance() - howMany);
        toAccount.setBalance(toAccount.getBalance() + howMany);
    }
}
