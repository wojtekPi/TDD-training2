import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Tdd training on 09.08.17.
 */
public class StringCalculatorTest {

    private StringCalculator testedObject;

    @Before
    public void setUp() throws Exception {
        testedObject = new StringCalculator();
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
    }

    @Test
    public void shouldReturnZeroWhenEmptyStringPassed(){
       int result = testedObject.Add("");
       assertThat(result).isEqualTo(0);
    }

    @Test
    public void shoudReturnOneWhenOnePassed() throws Exception {
        int result = testedObject.Add("1");
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void shoudReturnThreeWhenTwoPassed() throws Exception {
        int result = testedObject.Add("1,2");
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void shoudReturnSixWhenThreePassed() throws Exception {
        int result = testedObject.Add("1,2,3");
        assertThat(result).isEqualTo(6);
    }

    @Test
    public void shouldReturnTwentyWhenRandomNumsPassed() throws Exception {
        int result = testedObject.Add("1,3,5,1,10");

        assertThat(result).isEqualTo(20);
    }
}