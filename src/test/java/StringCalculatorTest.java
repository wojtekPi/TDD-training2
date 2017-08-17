import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tdd training on 09.08.17.
 */
@RunWith(JUnitParamsRunner.class)
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
    public void shouldeThrowExceptionWhenNegativePassed() throws Exception {
        int result = 0;
        try {
            result = testedObject.Add("-1");
            fail();
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("negatives not allowed: -1");
        }
        assertThat(result).isNotEqualTo(-1);
        assertThat(result).isEqualTo(0);
    }

    private Object[][] parametersForCheckingResults() {
        return new Object[][]{
                {"", 0}, {null, 0},
                {"0", 0}, {"1", 1}, {"3", 3},
                {"1,2", 3},
                {"2\n3",5},
                {"1,2,7", 10},{"1\n2,3", 6},
                {"//;\n1;2", 3}, {"//#\n4#7", 11},
                {"2000,7", 7},
                {"//[###][%^]\\n3###3%^3",9 },
                {"//[ ][%]\\n1 2%3", 6},
                {"//[***]\\n1***2***3", 6}
        };
    }

    @Test
    @Parameters(method = "parametersForCheckingResults")
    public void shoudReturnCorrectValueForSpecificInput(String input, int expectedResult) throws Exception {
        int result = testedObject.Add(input);
        assertThat(result).isEqualTo(expectedResult);
    }

}