package bank;

import org.junit.Before;
import org.junit.Test;

import static bank.PaymentService.transferMoney;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * Tdd training on 11.08.17.
 */
public class AccountTest {
    private Account testedObject;
    private Account testedObject2;

    @Before
    public void setUp() throws Exception {
        testedObject = new Account();

    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
    }

    @Test
    public void shouldReturnWhenIDValuePassed() {
        testedObject.setId(123456789);
        int id = testedObject.getId();
        assertThat(id).isEqualTo(123456789);
    }

    @Test
    public void shouBalanceReturnWhenBalanceValuePassed() {
        testedObject.setBalance(10000);
        int id = testedObject.getBalance();
        assertThat(id).isEqualTo(10000);
    }

    @Test
    public void shouldTransferMoneyFromOneAccountToTheOther() {
        testedObject2 = new Account();

        testedObject.setBalance(2000);
        testedObject2.setBalance(1000);
        transferMoney(500, testedObject, testedObject2);
        assertThat(testedObject.getBalance()).isEqualTo(1500);
        assertThat(testedObject2.getBalance()).isEqualTo(1500);
    }
}