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

    @Test
    public void shouldReturnZeroWhenEmptyStringPassed() {
        int result = testedObject.Add("");
        //assertEquals(result,0);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void shouldReturnOneWhenOnePassed() throws Exception {
        int result = testedObject.Add("1");

        assertThat(result).isEqualTo(1);
    }

    @Test
    public void shouldReturnTwoWhenTwoPassed() throws Exception {
        int result = testedObject.Add("2");

        assertThat(result).isEqualTo(2);
    }

  //  @Test
 //   public void shouldReturnUnknownNumbers() throws Exception {
 //       int result = testedObject.Add("1, 2");
//        assertThat(result).isEqualTo(3);
//    }
    private Object[][] parametersForCheckingResults() {
        return new Object[][]{
                {"0",0},
                {"1",1},
                {null,0},
                {"1,2",3},
                {"4,5",9},
                {"1\n2,3",6}
        };
    }

    @Test
    @Parameters(method = "parametersForCheckingResults")
    public void shouldReturnCorrectValueForSpecificInput(String input, int expectedResult) {
        int result = testedObject.Add(input);
        assertThat(result).isEqualTo(expectedResult);
    }
}