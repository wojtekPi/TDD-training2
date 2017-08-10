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

//    @Test
//    public void shouldCreateObject() throws Exception {
//        assertThat(testedObject).isNotNull();
//    }
//
//    @Test
//    public void shouldReturnZeroWhenEmptyStringPassed() {
//        int result = testedObject.Add("");
//
//        assertThat(result).isEqualTo(0);
//    }
//
//    @Test
//    public void shouldReturnOneWhenOnePassed() throws Exception {
//        int result = testedObject.Add("1");
//
//        assertThat(result).isEqualTo(1);
//    }
//
//    @Test
//    public void shouldReturnThreeWhenOneAndTwoPassed() throws Exception {
//        int result = testedObject.Add("1,2");
//
//        assertThat(result).isEqualTo(3);
//    }
//
//    @Test
//    public void shouldReturnTenWhenRandomNumsPassed() throws Exception {
//        int result = testedObject.Add("1,3,5,1");
//
//        assertThat(result).isEqualTo(10);
//    }
//
//    @Test
//    public void shouldReturnSixWhen() throws Exception {
//        int result = testedObject.Add("1,2," +
//                "3");
//
//        assertThat(result).isEqualTo(6);
//    }
//
//    @Test
//    public void shouldReturnThreeWhenOneTwoAndDelimiterPassed() throws Exception {
//        int result = testedObject.Add("//;1" +
//                ";2");
//
//        assertThat(result).isEqualTo(3);
//    }

    @Test
    @Parameters({"1,1", "0,0", ",0"})
    public void shouldReturnCorrectValueForSpecificInput(String input, int expectedResult) throws Exception {
        int result = testedObject.Add(input);
        assertThat(result).isEqualTo(expectedResult);
    }

    private Object[][] parametersForCheckingResults(){
        return new Object[][]{
                {"0", 0},
                {"1", 1},
                {null, 0},
        };
    }

    @Test
    @Parameters(method = "parametersForCheckingResults")
    public void shouldReturnCorrectValueForSpecificInput2(String input, int expectedResult) throws Exception {
        int result = testedObject.Add(input);
        assertThat(result).isEqualTo(expectedResult);
    }


}