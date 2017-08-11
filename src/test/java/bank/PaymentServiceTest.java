package bank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Filip on 2017-08-11.
 */
@RunWith(JUnitParamsRunner.class)
public class PaymentServiceTest {

    private PaymentService testedObject;

    @Before
    public void setUp() throws Exception {
        testedObject = new PaymentService();
    }

    private Object[][] parametersForSpecificMoneyTransfers(){
        return new Object[][]{
                {100,2,30,70,32},
                {10,50,50,-40,100},



        };
    }


    @Test
    @Parameters(method = "parametersForSpecificMoneyTransfers")
    public void shouldTransferMoneyFromOneAccountToAnother(int accountOneBalance, int accountTwoBalance,
                                                           int moneyToTransfer, int expectedBalanceOfAccountOneAfterTransfer, int expectedBalanceOfAccountTwoAfterTransfer)
            throws Exception {
        Account accountOne = new Account();
        accountOne.setId(1);
        accountOne.setBalance(accountOneBalance);

        Account accountTwo = new Account();
        accountTwo.setId(2);
        accountTwo.setBalance(accountTwoBalance);

        testedObject.transferMoney(accountOne,accountTwo,moneyToTransfer);

        assertThat(accountOne.getBalance()).isEqualTo(expectedBalanceOfAccountOneAfterTransfer);
        assertThat(accountTwo.getBalance()).isEqualTo(expectedBalanceOfAccountTwoAfterTransfer);
    }
}