package bank;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentServiceTest {

    @Test
    public void shouldSendMoneyFromAccountOne() {
        Account accountOne = new Account(1,200);
        Account accountTwo = new Account(2,0);
        PaymentService ps = new PaymentService();
        ps.transferMoney(accountOne,accountTwo,100);
        assertThat(accountTwo.getBalance()).isEqualTo(100);
    }

}
