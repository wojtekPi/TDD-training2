package bank;

/**
 * Created by Filip on 2017-08-11.
 */
public class PaymentService {

    private static final int BALANCE_LIMIT = -500;
    private static final String NOT_ENOUGH_MONEY_MESSAGE = "I'm very sorry, but you don't have enough money...";
    private ExchangeServiceI exchangeService;

    public void transferMoney(Account accountOne, Account accountTwo, Instrument moneyToTransfer) throws IllegalArgumentException {

        validatePayment(accountOne, accountTwo, moneyToTransfer);

        doPayment(accountOne, accountTwo, moneyToTransfer);
    }

    private void doPayment(Account accountOne, Account accountTwo, Instrument moneyToTransfer) {
        accountOne.setBalance(new Instrument(calculateAmountInFirstAccount(accountOne, moneyToTransfer), accountOne.getBalance().getCurrency()));
        accountTwo.setBalance(new Instrument(calculateMoneyOnSecondAccount(accountTwo, moneyToTransfer), accountTwo.getBalance().getCurrency()));
    }

    private void validatePayment(Account accountOne, Account accountTwo, Instrument moneyToTransfer) {

        if (isConversionRequired(accountTwo, moneyToTransfer) || isConversionRequired(accountOne, moneyToTransfer)) {
            checkExchangeService();
        }

        if (isEnoughMoneyOnFirstAccount(accountOne, moneyToTransfer)) {
            throw new IllegalArgumentException(NOT_ENOUGH_MONEY_MESSAGE);
        }

    }

    private void checkExchangeService() {
        if (exchangeService == null) {
            throw new NullPointerException();
        }
    }

    private int calculateMoneyOnSecondAccount(Account accountTwo, Instrument moneyToTransfer) {
        Instrument moneyWhichShouldBeTransferedToSecondAccount =
                calculateInstrumentInCurrencyUsedInAccount(accountTwo, moneyToTransfer);
        return accountTwo.getBalance().getAmount()
                + moneyWhichShouldBeTransferedToSecondAccount.getAmount();
    }

    private Instrument calculateInstrumentInCurrencyUsedInAccount(Account accountUsedInTransaction, Instrument moneyToTransfer) {
        Instrument moneyWhichShouldBeTransferedToSecondAccount = moneyToTransfer;
        if (isConversionRequired(accountUsedInTransaction, moneyToTransfer)) {
            moneyWhichShouldBeTransferedToSecondAccount =
                    exchangeService.calculateAmount(moneyToTransfer, accountUsedInTransaction.getBalance().getCurrency());
        }
        return moneyWhichShouldBeTransferedToSecondAccount;
    }

    private boolean isConversionRequired(Account accountUsedInTransaction, Instrument moneyToTransfer) {
        return accountUsedInTransaction.getBalance().getCurrency() != moneyToTransfer.getCurrency();
    }

    private int calculateAmountInFirstAccount(Account accountOne, Instrument moneyToTransfer) {
        Instrument moneyWhichShouldBeTransferedToSecondAccount =
                calculateInstrumentInCurrencyUsedInAccount(accountOne, moneyToTransfer);
        return accountOne.getBalance().getAmount()
                - moneyWhichShouldBeTransferedToSecondAccount.getAmount();
    }

    private boolean isEnoughMoneyOnFirstAccount(Account accountOne, Instrument moneyToTransfer) {
        return calculateAmountInFirstAccount(accountOne, moneyToTransfer) < BALANCE_LIMIT;
    }

    private boolean doesCurrencyMatch(Account accountOne, Account accountTwo, Instrument moneyToTransfer) {
        return accountOne.getBalance().getCurrency() == moneyToTransfer.getCurrency();
    }

    public void setExchangeService(ExchangeServiceI exchangeService) {
        this.exchangeService = exchangeService;
    }
}
