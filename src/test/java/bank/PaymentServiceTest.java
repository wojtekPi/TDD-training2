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

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
        Instrument instrumentOne = new Instrument();
        accountOne.setId(1);
        instrumentOne.setAmount(accountOneBalance);
        accountOne.setBalance(instrumentOne);


        Account accountTwo = new Account();
        Instrument instrumentTwo = new Instrument();
        accountTwo.setId(2);
        instrumentTwo.setAmount(accountTwoBalance);
        accountTwo.setBalance(instrumentTwo);

        Instrument instrumentToTransfer = new Instrument();

        instrumentToTransfer.setAmount(moneyToTransfer);

        testedObject.transferMoney(accountOne,accountTwo,instrumentToTransfer);

        assertThat(accountOne.getBalance().getAmount()).isEqualTo(expectedBalanceOfAccountOneAfterTransfer);
        assertThat(accountTwo.getBalance().getAmount()).isEqualTo(expectedBalanceOfAccountTwoAfterTransfer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenBalanceTooLow(){
        Account accountOne = new Account();
        Instrument instrumentOne = new Instrument();
        instrumentOne.setAmount(499);
        instrumentOne.setCurrency(Currency.PLN);
        accountOne.setId(1);
        accountOne.setBalance(instrumentOne);

        Account accountTwo = new Account();
        Instrument instrumentTwo = new Instrument();
        instrumentTwo.setAmount(200);
        instrumentTwo.setCurrency(Currency.PLN);
        accountTwo.setId(2);
        accountTwo.setBalance(instrumentTwo);

        Instrument instrumentToTransfer = new Instrument();
        instrumentToTransfer.setAmount(1000);
        instrumentToTransfer.setCurrency(Currency.PLN);


        testedObject.transferMoney(accountOne,accountTwo,instrumentToTransfer);
    }

//    //mozliwosc sprawdzenia komunikatu rzucanego przez wyjatek (+adnotacja Rule u gory klasy)
//    @Test
//    public void shouldThrowExceptionWhenBalanceTooLow(){
//        thrown.expect(IllegalArgumentException.class);
//        thrown.expectMessage("I'm very sorry, but you don't have enough money...");
//
//
//        Account accountOne = new Account();
//        accountOne.setId(1);
//        accountOne.setBalance(0);
//
//        Account accountTwo = new Account();
//        accountTwo.setId(2);
//        accountTwo.setBalance(200);
//
//        testedObject.transferMoney(accountOne,accountTwo,1000);
//    }
}