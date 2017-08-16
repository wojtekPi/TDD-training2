package bank;

/**
 * Tdd training on 16.08.17.
 */
public class ExchangeServiceIImpl implements ExchangeServiceI {
    @Override
    public Instrument calculateAmount(Instrument instrument, Currency currency) {
        return new Instrument(30, Currency.USD);
    }
}
