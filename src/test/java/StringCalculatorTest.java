import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
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
    public void ShouldReturnZeroWhenEmptyStringPassed() {
        int result = testedObject.Add("");
        assertEquals(result, 0);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void shouldReturnOneWhenOnePassed() throws Exception {
        int result = testedObject.Add("1");
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void shouldReturnSumWhenTwoPassed() throws Exception {
        int result = testedObject.Add("1,2");
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void shouldReturnSumWhenUnknownAmountOfNumbers() throws Exception {
        int result = testedObject.Add("1,2,3,4");
        assertThat(result).isEqualTo(10);
    }
    @Test
    public void shouldReturnSumWhennewLinesBetweenNumbers() throws Exception {
        int result = testedObject.Add("1\n2,3");
        assertThat(result).isEqualTo(6);
    }

    @Test
    public void shouldtReturnSumWhennewLinesBetweenNumbers() throws Exception {
        int result = testedObject.Add("1,\n");
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void shouldReturnSumWhenNewDifferentDelimiters() throws Exception {
        int result = testedObject.Add("//;\\n1;2");
        assertThat(result).isEqualTo(1);
    }
}
