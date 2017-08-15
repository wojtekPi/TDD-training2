package bank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Answers;

import static bank.Currency.PLN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;

/**
 * Created by Filip on 2017-08-11.
 */
@RunWith(JUnitParamsRunner.class)
public class PaymentServiceTest {

    private static final Instrument HUNDRED_PLN = new Instrument(100, PLN);
    private static final Instrument TEN_PLN = new Instrument(10, PLN);
    private static final Instrument TWO_PLN = new Instrument(2, PLN);
    private static final Instrument FIFTY_PLN = new Instrument(50, PLN);
    private static final Instrument THIRTY_PLN = new Instrument(30, PLN);
    private static final Instrument SEVENTY_PLN = new Instrument(70, PLN);
    private static final Instrument THIRTYTWO_PLN = new Instrument(32, PLN);
    private static final Instrument MINUS_FORTY_PLN = new Instrument(-40, PLN);
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private PaymentService testedObject;

    @Before
    public void setUp() throws Exception {
        testedObject = new PaymentService();
    }

    private Object[][] parametersForSpecificMoneyTransfers() {
        return new Object[][]{
                {HUNDRED_PLN, TWO_PLN, THIRTY_PLN, SEVENTY_PLN, THIRTYTWO_PLN},
                {TEN_PLN, FIFTY_PLN, FIFTY_PLN, MINUS_FORTY_PLN, HUNDRED_PLN}


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

        testedObject.transferMoney(accountOne, accountTwo, moneyToTransfer);

        assertThat(accountOne.getBalance()).isEqualTo(expectedBalanceOfAccountOneAfterTransfer);
        assertThat(accountTwo.getBalance()).isEqualTo(expectedBalanceOfAccountTwoAfterTransfer);
    }

    @Test
    public void shouldThrowExceptionWhenBalanceToLow() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("I'm very sorry, but you don't have enough money...");
        Account accountOne = new Account();
        accountOne.setId(1);
        accountOne.setBalance(new Instrument(0, PLN));

        Account accountTwo = new Account();
        accountTwo.setId(2);
        accountTwo.setBalance(new Instrument(200, PLN));

        testedObject.transferMoney(accountOne, accountTwo, new Instrument(1000, PLN));

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCurrenciesAreDifferent() throws Exception {
        Account accountOne = new Account();
        accountOne.setId(1);
        accountOne.setBalance(new Instrument(0, Currency.USD));

        Account accountTwo = new Account();
        accountTwo.setId(2);
        accountTwo.setBalance(new Instrument(200, PLN));

        testedObject.transferMoney(accountOne, accountTwo, new Instrument(100, PLN));
    }

    @Test
    public void shouldNotTransferMoneyWhenCurrenciesDoesntMatch() throws Exception {
        Account accountOne = new Account();
        accountOne.setId(1);
        accountOne.setBalance(new Instrument(0, Currency.USD));

        Account accountTwo = new Account();
        accountTwo.setId(2);
        accountTwo.setBalance(new Instrument(200, PLN));
        try {
            testedObject.transferMoney(accountOne, accountTwo, new Instrument(100, PLN));
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail("WrongExceptionThrown");
        }
        assertThat(accountOne.getBalance().getAmount()).isEqualTo(0);

    }

    @Test
    public void shouldNotChangeAmountWhenNotEnoughMoneyOnFirstAccountWithMockito() throws Exception {
        Account accountOneMock = mock(Account.class);
        Account accountTwoMock = mock(Account.class, Answers.RETURNS_DEEP_STUBS);
        Instrument instrumentMock = mock(Instrument.class);

        when(accountOneMock.getBalance()).thenReturn(instrumentMock);
        when(instrumentMock.getAmount()).thenReturn(1);
        when(instrumentMock.getCurrency()).thenReturn(PLN);
        when(accountTwoMock.getBalance().getCurrency()).thenReturn(PLN);

        Instrument moneyToTransfer = new Instrument(502, PLN);
        try {
            testedObject.transferMoney(accountOneMock, accountTwoMock, moneyToTransfer);
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail("WrongExceptionThrown");
        }
        verify(instrumentMock, never()).setAmount(anyInt());
        verify(accountOneMock, never()).setBalance(any(Instrument.class));
    }
}