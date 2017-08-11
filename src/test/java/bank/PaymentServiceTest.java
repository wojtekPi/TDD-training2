package bank;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentServiceTest {

    private PaymentService testedObject;

    @Before
    public void setUp() throws Exception {
        testedObject = new PaymentService();
    }

    @Test
    public void transferMoneyTest() throws Exception {
        Account ac1 = new Account();
        Account ac2 = new Account();

        ac1.setId(1);
        ac2.setId(2);
        ac1.setBalance(1000);
        ac2.setBalance(500);

        testedObject.transferMoney(ac1, ac2, 200);

        assertThat(ac1.getBalance()).isEqualTo(800);
        assertThat(ac2.getBalance()).isEqualTo(700);
    }

    @Test
    public void shouldReturnFalseWhenNotEnoughMoney() throws Exception {
        Account ac1 = new Account();
        Account ac2 = new Account();

        ac1.setId(1);
        ac2.setId(2);
        ac1.setBalance(1000);
        ac2.setBalance(500);

        boolean result = testedObject.transferMoney(ac1, ac2, 2000);

        assertThat(result).isEqualTo(false);
        assertThat(ac1.getBalance()).isEqualTo(1000);
        assertThat(ac2.getBalance()).isEqualTo(500);
    }
}
