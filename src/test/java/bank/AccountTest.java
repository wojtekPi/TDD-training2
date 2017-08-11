package bank;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Tdd training on 11.08.17.
 */
public class AccountTest {
    private Account account1;
    private PaymentService paymentService;
    private Account account2;
    private Currency currency1;
    private ExchangeRatio exchange;

    @Before
    public void setUp() throws Exception {
        account1 = new Account(1, 1000);
        account2 = new Account(2, 5000);
        paymentService = new PaymentService();
    }

    @Test
    public void shouldCreateObjectAccount() throws Exception {
        assertThat(account1).isNotNull();
        assertThat(paymentService).isNotNull();
        assertThat(currency1).isNotNull();
        assertThat(exchange).isNotNull();
    }



    @Test
    public void shouldTransferAmountFromA1ToA2(){
        try {
            paymentService.transferMoney(account1, account2, 500);
        } catch (AccountException e) {
            e.printStackTrace();
        }
        assertThat(account1.getBalanceAccount()).isEqualTo(500);
        assertThat(account2.getBalanceAccount()).isEqualTo(5500);
    }

    @Test
    public void shouldTransferAmountFromA1ToA2TooMuch(){
        boolean checker = false;
        try {
            paymentService.transferMoney(account1, account2,2000);
        } catch (AccountException e) {
            checker = true;
            e.printStackTrace();
            return;
        }
        assertThat(checker).isTrue();
    }



//    @Test
//    public void shouldChangePLNtoUSD() throws Exception {
//        exchange.changePLNtoUSD(100).isEqualTo(400);
//    }


}