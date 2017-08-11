package bank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static bank.Currency.EUR;
import static bank.Currency.PLN;
import static bank.Currency.USD;
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
                {100,PLN,2,PLN,30,PLN,70,32},
                {10,EUR,50,EUR,50,EUR,-40,100},



        };

    }

    private Object[][] parametersForSpecificCurrencyMoneyTransfers(){
        return new Object[][]{
                {100,EUR,0,PLN,100,EUR,0,400},
                {400,EUR,0,PLN,200,EUR,200,800},



        };

    }


    @Test
    @Parameters(method = "parametersForSpecificMoneyTransfers")
    public void shouldTransferMoneyFromOneAccountToAnother(int accountOneBalance,
                                                           Currency currency1,
                                                           int accountTwoBalance,
                                                           Currency currency2,
                                                           int moneyToTransfer,
                                                           Currency currency3,
                                                           int expectedBalanceOfAccountOneAfterTransfer,
                                                           int expectedBalanceOfAccountTwoAfterTransfer)
            throws Exception {

        Instrument money = new Instrument();
        Account accountOne = new Account();
        accountOne.setId(1);
        accountOne.setBalance(accountOneBalance);

        Account accountTwo = new Account();
        accountTwo.setId(2);
        accountTwo.setBalance(accountTwoBalance);

        accountOne.setCurrency(currency1);
        accountTwo.setCurrency(currency2);
        money.setCurrency(currency3);
        money.setAmount(moneyToTransfer);

        testedObject.transferMoney(accountOne,accountTwo,money);

        assertThat(accountOne.getBalance()).isEqualTo(expectedBalanceOfAccountOneAfterTransfer);
        assertThat(accountTwo.getBalance()).isEqualTo(expectedBalanceOfAccountTwoAfterTransfer);
    }

    @Test(expected = IllegalArgumentException.class)

    public void shoudThrowExceptionWhenBalanceTooLow(){

        Instrument money = new Instrument();
        Account accountOne = new Account();
        accountOne.setId(1);
        accountOne.setBalance(0);

        Account accountTwo = new Account();
        accountTwo.setId(2);
        accountTwo.setBalance(200);
        money.setAmount(1000);

        testedObject.transferMoney(accountOne,accountTwo, money);
    }

    /** @Test(expected = IllegalArgumentException.class)

    public void shouldThrowExceptionWhenCurrenciesDiffer(){

        Instrument money = new Instrument();
        Account accountOne = new Account();
        accountOne.setId(1);
        accountOne.setBalance(0);
        accountOne.setCurrency(USD);

        Account accountTwo = new Account();
        accountTwo.setId(2);
        accountTwo.setBalance(200);
        accountTwo.setCurrency(EUR);

        money.setAmount(50);
        money.setCurrency(USD);

        testedObject.transferMoney(accountOne,accountTwo, money);
    }
    */

    @Test
    @Parameters(method = "parametersForSpecificCurrencyMoneyTransfers")
    public void shouldTransferMoneyFromEURAccountToPLN(int accountOneBalance,
                                                           Currency currency1,
                                                           int accountTwoBalance,
                                                           Currency currency2,
                                                           int moneyToTransfer,
                                                           Currency currency3,
                                                           int expectedBalanceOfAccountOneAfterTransfer,
                                                           int expectedBalanceOfAccountTwoAfterTransfer){

        Instrument money = new Instrument();
        Account accountOne = new Account();
        accountOne.setBalance(accountOneBalance);

        Account accountTwo = new Account();
        accountTwo.setBalance(accountTwoBalance);

        accountOne.setCurrency(currency1);
        accountTwo.setCurrency(currency2);
        money.setCurrency(currency3);
        money.setAmount(moneyToTransfer);

        testedObject.transferMoney(accountOne,accountTwo,money);

        assertThat(accountOne.getBalance()).isEqualTo(expectedBalanceOfAccountOneAfterTransfer);
        assertThat(accountTwo.getBalance()).isEqualTo(expectedBalanceOfAccountTwoAfterTransfer);
    }




}