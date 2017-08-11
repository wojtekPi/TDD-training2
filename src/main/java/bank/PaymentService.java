package bank;

public class PaymentService {

    public static void transferMoney(int payment, Account payer, Account beneficiary){

        int tempBalance = beneficiary.getBalance();
        beneficiary.setBalance(tempBalance+payment);

        tempBalance = payer.getBalance();
        payer.setBalance(tempBalance-payment);


    }
}
