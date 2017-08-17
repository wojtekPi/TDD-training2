package bank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Answers;

import static bank.Currency.EUR;
import static bank.Currency.PLN;
import static bank.Currency.USD;
import static org.assertj.core.api.Assertions.*;
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
        Account accountOne = createAccount(1, 0, PLN);

        Account accountTwo = createAccount(2, 200, PLN);

        testedObject.transferMoney(accountOne, accountTwo, new Instrument(1000, PLN));
    }

    @Test
    public void shouldNotTransferMoneyWhenBalanceToLow() throws Exception {
        Account accountOne = createAccount(1, 0, PLN);

        Account accountTwo = createAccount(2, 200, PLN);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> testedObject.transferMoney
                        (accountOne, accountTwo, new Instrument(1000, PLN)));
        assertThat(accountOne.getBalance()).isEqualTo(new Instrument(0, PLN));
        assertThat(accountTwo.getBalance()).isEqualTo(new Instrument(200, PLN));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCurrenciesAreDifferent() throws Exception {
        Account accountOne = createAccount(1, 0, USD);

        Account accountTwo = createAccount(2, 200, PLN);

        testedObject.transferMoney(accountOne, accountTwo, new Instrument(100, PLN));
        //#INFO: We are not able to check  here anything. When exception is thrown,
        // then no followed instructions will be reached.
    }

    @Ignore
    @Test
    public void shouldNotTransferMoneyWhenCurrenciesDoesntMatch() throws Exception {
        Account accountOne = createAccount(1, 0, USD);

        Account accountTwo = createAccount(2, 200, PLN);
        try {
            testedObject.transferMoney(accountOne, accountTwo, new Instrument(100, PLN));
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail("WrongExceptionThrown");
        }
        assertThat(accountOne.getBalance().getAmount()).isEqualTo(0);
        assertThat(accountTwo.getBalance().getAmount()).isEqualTo(200);

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

        //#INFO: Checking exception with AssertJ - nice  feature from assertJ 3
        //This approach is much better than wrapping execution in try-catch block
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> testedObject.transferMoney(accountOneMock, accountTwoMock, moneyToTransfer))
                .withMessage("I'm very sorry, but you don't have enough money...");

        verify(instrumentMock, never()).setAmount(anyInt());
        verify(accountOneMock, never()).setBalance(any(Instrument.class));
    }

    @Test
    public void shoulTransferPLNfromFirstAcountToAccountWithEuro() throws Exception {
        Account accountOne = createAccount(1, 0, Currency.PLN);

        Account accountTwo = createAccount(2, 200, USD);

        ExchangeServiceI exchangeServiceMock = mock(ExchangeServiceI.class);
        testedObject.setExchangeService(exchangeServiceMock);

        when(exchangeServiceMock.calculateAmount(any(Instrument.class), eq(USD)))
                .thenReturn(new Instrument(30, USD));

        testedObject.transferMoney(accountOne, accountTwo, new Instrument(100, PLN));

        assertThat(accountOne.getBalance().getAmount()).isEqualTo(-100);
        assertThat(accountTwo.getBalance().getAmount()).isEqualTo(230);
        verify(exchangeServiceMock).calculateAmount(any(Instrument.class), eq(USD));
    }

    private Account createAccount(int id, int amount, Currency currency) {
        Account accountOne = new Account();
        accountOne.setId(id);
        accountOne.setBalance(new Instrument(amount, currency));
        return accountOne;
    }

    @Test
    public void shouldThrowNPEwhenconversionrequiredAndExchangeServiceNotSet() throws Exception {
        Account accountOne = createAccount(1, 2, PLN);
        Account accountTwo = createAccount(2, 2, USD);
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> testedObject.transferMoney(accountOne, accountTwo, HUNDRED_PLN));

        assertThat(accountOne.getBalance()).isEqualTo(TWO_PLN);
        assertThat(accountTwo.getBalance()).isEqualTo(new Instrument(2, USD));
    }

    private Object[][] parametersForShouldTransferMoneyWhenDifferentCurrencies() {
        return new Object[][]{
                {createAccount(1, 1, PLN), createAccount(2, 1, USD),
                        new Instrument(100, PLN), new Instrument(-99, PLN), new Instrument(56, USD)},
                {createAccount(1, 1, PLN), createAccount(2, 1, USD),
                        new Instrument(10, USD), new Instrument(-32, PLN), new Instrument(11, USD)},
                {createAccount(1, 1, PLN), createAccount(2, 1, USD),
                        new Instrument(30, EUR), new Instrument(-32, PLN), new Instrument(56, USD)},
        };
    }

    @Parameters
    @Test
    public void shouldTransferMoneyWhenDifferentCurrencies(Account accountOne, Account accountTwo,
                                                           Instrument moneyToTransfer, Instrument expectedBalanceInAccountOne,
                                                           Instrument expectedBalanceInAccountTwo) {
        ExchangeServiceI exchangeServiceMock = mock(ExchangeServiceI.class);
        testedObject.setExchangeService(exchangeServiceMock);

        when(exchangeServiceMock.calculateAmount(moneyToTransfer, accountOne.getBalance().getCurrency()))
                .thenReturn(new Instrument(33, accountOne.getBalance().getCurrency()));

        when(exchangeServiceMock.calculateAmount(moneyToTransfer, accountTwo.getBalance().getCurrency()))
                .thenReturn(new Instrument(55, accountTwo.getBalance().getCurrency()));


        testedObject.transferMoney(accountOne, accountTwo, moneyToTransfer);

        assertThat(accountOne.getBalance()).isEqualTo(expectedBalanceInAccountOne);
        assertThat(accountTwo.getBalance()).isEqualTo(expectedBalanceInAccountTwo);
    }

}