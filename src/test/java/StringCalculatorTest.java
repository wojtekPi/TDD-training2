import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    public void shouldReturnZeroWhenEmptyString(){
        int result = 0;
        try {
            result = testedObject.Add("");
        } catch (NegativeException e) {
            e.printStackTrace();
        }
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void shouldReturnOneWhenOnePassed() throws Exception {
        int result = testedObject.Add("1");
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void shouldReturnTwoWhenTwoPassed() throws Exception {
        int result = testedObject.Add("1,2");
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void shouldReturnTwoWhenMorePassed() throws Exception {
        int result = testedObject.Add("1;2,5 3");
        assertThat(result).isEqualTo(11);
    }

    @Test
    public void checkIfThrowException() throws Exception {
        boolean checker = false;

        try {

            int result = testedObject.Add("1;2,-5 3");
        } catch (NegativeException e) {
            checker = true;
            e.printStackTrace();
            return;
        }
        assertTrue(checker);
    }
}