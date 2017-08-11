package bank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Filip on 2017-08-11.
 */
@RunWith(JUnitParamsRunner.class)
public class PaymentServiceTest {

    //@Rule
    //public ExpectedException thrown= ExpectedException.none();

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
        accountOne.setBalance(new Instrument(accountOneBalance,Currency.EUR));

        Account accountTwo = new Account();
        accountTwo.setId(2);
        accountTwo.setBalance(new Instrument(accountTwoBalance,Currency.EUR));

        testedObject.transferMoney(accountOne,accountTwo,moneyToTransfer);

        assertThat(accountOne.getBalance().getAmount()).isEqualTo(expectedBalanceOfAccountOneAfterTransfer);
        assertThat(accountTwo.getBalance().getAmount()).isEqualTo(expectedBalanceOfAccountTwoAfterTransfer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenBalanceTooLow(){
       // thrown.expect(IllegalArgumentException.class);
       // thrown.expectMessage("I'm very sorry");
        Account accountOne = new Account();
        accountOne.setId(1);
        accountOne.setBalance(new Instrument(0,Currency.EUR));

        Account accountTwo = new Account();
        accountTwo.setId(2);
        accountTwo.setBalance(new Instrument(200, Currency.EUR));

        testedObject.transferMoney(accountOne,accountTwo,1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCurrencyAreWrong() {

        Account accountOne = new Account();
        accountOne.setId(1);
        accountOne.setBalance(new Instrument(0,Currency.EUR));

        Account accountTwo = new Account();
        accountTwo.setId(2);
        accountTwo.setBalance(new Instrument(200, Currency.PLN));

        testedObject.transferMoney(accountOne,accountTwo,1000);
    }

}