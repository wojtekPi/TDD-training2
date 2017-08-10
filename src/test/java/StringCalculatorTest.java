import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static sun.nio.cs.Surrogate.is;

/**
 * Tdd training on 09.08.17.
 */
public class StringCalculatorTest {

    private StringCalculator testedObject;
    int result;

    @Before
    public void setUp() throws Exception {
        testedObject = new StringCalculator();
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
    }

    @Test
    public void ShouldReturnZeroWhenNoStringPassed() throws Exception {
        result = testedObject.Add("");
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void ShouldReturnOneWhenOnePassed() throws Exception {
        result = testedObject.Add("1");
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void ShouldReturnTwoWhenTwoPassed() throws Exception {
        result = testedObject.Add("2");
        assertThat(result).isEqualTo(2);
    }

    public void ShouldReturnSumOfNumericalStringElements() throws Exception {
        result = testedObject.Add("1,2,3,4,5");
        assertThat(result).isEqualTo(15);
    }


}