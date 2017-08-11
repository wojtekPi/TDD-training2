package bank;

public class PaymentService {
    public boolean transferMoney(Account ac1, Account ac2, int i) {
        if(i > ac1.getBalance())
            return false;

        ac1.setBalance(ac1.getBalance() - i);
        ac2.setBalance(ac2.getBalance() + i);

        return true;
    }
}
