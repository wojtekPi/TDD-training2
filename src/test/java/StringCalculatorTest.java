import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

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

    private Object[][] parametersForShouldReturnCorrectValueForSpecificInput(){
        return new Object[][]{
                {"0",0},
                {"1",1},
                {null,0},
                {"1,2,3",6},
        };
    }

    @Test
    @Parameters(method = "parametersForShouldReturnCorrectValueForSpecificInput")
    public void shouldReturnCorrectValueForSpecificInput(String input, int expectedResult) {
        int result = testedObject.Add(input);

        assertThat(result).isEqualTo(expectedResult);
    }


}