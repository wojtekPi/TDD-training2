package bank;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InstrumentTest {
    private Instrument testedObject;

    @Before
    public void setUp() throws Exception {
        testedObject = new Instrument(0, Currency.PLN);
    }

    @Test
    public void shouldCreateObject() {
        assertThat(testedObject).isNotNull();
    }

    @Test
    public void shouldBeAbleToChengeAmount() {
        testedObject.setAmount(7);
        assertThat(testedObject.getAmount()).isEqualTo(7);
    }

    //#INFO: Very nice test which test our equals and hashcode method
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Instrument.class)
                .usingGetClass()
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }

}
