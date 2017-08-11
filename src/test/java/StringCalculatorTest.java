import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
    public void shouldReturnZeroWhenEmptyStringPassed() {
        int result = testedObject.add("");

        assertThat(result).isEqualTo(0);
    }

    @Test
    public void shouldReturnOneWhenOnePassed() throws Exception {
        int result = testedObject.add("1");

        assertThat(result).isEqualTo(1);
    }

    @Test
    public void shouldReturnTwoWhenTwoPassed() throws Exception {
        int result = testedObject.add("2");

        assertThat(result).isEqualTo(2);
    }

    @Test
    public void shouldReturnFiveWhenTwoCommaThreeGiven() throws Exception {
        int result = testedObject.add("2,3");

        assertThat(result).isEqualTo(5);
    }

    @Test
    public void shouldReturnTwentyTwoWhenTenCommaSpaceTwelveGiven() throws Exception {
        int result = testedObject.add("10, 12");

        assertThat(result).isEqualTo(22);
    }

    @Test
    public void shouldReturnTenWhenSeparatorBetweenTwoFivesGiven() throws Exception {
        int result = testedObject.add("//;\n65;55");

        assertThat(result).isEqualTo(120);
    }

    @Test
    public void shouldReturnTwelveWhenNewLineBetweenTwoSixGiven() throws Exception {
        int result = testedObject.add("6\n6");

        assertThat(result).isEqualTo(12);
    }

    @Test
    public void shouldReturnSumOfThreeDelimitedNumbers() throws Exception {
        int result = testedObject.add("6,5 3");

        assertThat(result).isEqualTo(14);
    }

}