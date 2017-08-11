package bank;

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
    public void shouldCreateObject(){
        assertThat(testedObject).isNotNull();
    }


}
