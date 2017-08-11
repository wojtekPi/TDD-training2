package bank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Filip on 2017-08-11.
 */
@RunWith(JUnitParamsRunner.class)
public class PaymentServiceTest {

    private static final Instrument HUNDRED_PLN = new Instrument(100, Currency.PLN);
    private static final Instrument TEN_PLN = new Instrument(10, Currency.PLN);
    private static final Instrument TWO_PLN = new Instrument(2, Currency.PLN);
    private static final Instrument FIFTY_PLN = new Instrument(50, Currency.PLN);
    private static final Instrument THIRTY_PLN = new Instrument(30, Currency.PLN);
    private static final Instrument SEVENTY_PLN = new Instrument(70, Currency.PLN);
    private static final Instrument THIRTYTWO_PLN = new Instrument(32, Currency.PLN);
    private static final Instrument MINUS_FORTY_PLN = new Instrument(-40, Currency.PLN);
    private PaymentService testedObject;

//    @Rule
   // public ExceptedException thrown;

    @Before
    public void setUp() throws Exception {
        testedObject = new PaymentService();
    }

    private Object[][] parametersForSpecificMoneyTransfers(){
        return new Object[][]{
                {HUNDRED_PLN, TWO_PLN, THIRTY_PLN, SEVENTY_PLN, THIRTYTWO_PLN},
                {TEN_PLN, FIFTY_PLN, FIFTY_PLN, MINUS_FORTY_PLN,HUNDRED_PLN}



        };
    }


    @Test
    @Parameters(method = "parametersForSpecificMoneyTransfers")
    public void shouldTransferMoneyFromOneAccountToAnother(Instrument accountOneBalance, Instrument accountTwoBalance,
                                                           Instrument moneyToTransfer, Instrument expectedBalanceOfAccountOneAfterTransfer,
                                                           Instrument expectedBalanceOfAccountTwoAfterTransfer)
            throws Exception {
        Account accountOne = new Account();
        accountOne.setId(1);
        accountOne.setBalance(accountOneBalance);

        Account accountTwo = new Account();
        accountTwo.setId(2);
        accountTwo.setBalance(accountTwoBalance);

        testedObject.transferMoney(accountOne,accountTwo, moneyToTransfer);

        assertThat(accountOne.getBalance()).isEqualTo(expectedBalanceOfAccountOneAfterTransfer);
        assertThat(accountTwo.getBalance()).isEqualTo(expectedBalanceOfAccountTwoAfterTransfer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenBalanceToLow() throws Exception {
       // thrown.expect(IllegalArgumentException.class);
       // thrown.expectMessage("I'm very sorry, but you don't have enough money...);
        Account accountOne = new Account();
        accountOne.setId(1);
        accountOne.setBalance(new Instrument(0, Currency.PLN));

        Account accountTwo = new Account();
        accountTwo.setId(2);
        accountTwo.setBalance(new Instrument(200, Currency.PLN));

        testedObject.transferMoney(accountOne, accountTwo, new Instrument(1000, Currency.PLN));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCurrenciesAreDifferent() throws Exception {
        Account accountOne = new Account();
        accountOne.setId(1);
        accountOne.setBalance(new Instrument(0, Currency.USD));

        Account accountTwo = new Account();
        accountTwo.setId(2);
        accountTwo.setBalance(new Instrument(200, Currency.PLN));

        testedObject.transferMoney(accountOne, accountTwo, new Instrument(100, Currency.PLN));
    }
}