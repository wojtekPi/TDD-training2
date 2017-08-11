package bank;

public class PaymentService {
    public boolean transferMoney (Account from, Account to, int money){

        if (from.getBalance()<money){
            return false;
        }

        from.setBalance(from.getBalance()-money);
        to.setBalance(to.getBalance()+money);

        return true;
    }

}
