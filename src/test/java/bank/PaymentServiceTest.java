package bank;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentServiceTest {

    private Account testedAccount1;
    private Account testedAccount2;
    private PaymentService transfer = new PaymentService();

    @Before
    public void setUp() throws Exception {
        testedAccount1 = new Account(123456, 1000);
        testedAccount2 = new Account(654321,3000);
    }
    @Test
    public void shouldTransferMoneyFromOneAccountToAnother() throws Exception {
        transfer.transferMOney(testedAccount1, testedAccount2, 100);
        assertThat(testedAccount1.getBalance()).isEqualTo(900);
    }
    @Test
    public void shouldTransferMultiMoneyFromOneAccountToAnother() throws Exception {
        transfer.transferMOney(testedAccount1,testedAccount2,100);
        assertThat(testedAccount1.getBalance()).isEqualTo(900);
        transfer.transferMOney(testedAccount1,testedAccount2,100);
        assertThat(testedAccount1.getBalance()).isEqualTo(800);
    }
}
