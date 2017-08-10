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
    @Test
    public void shouldReturnThreeWhenOneAndTwoPassed() {
        int result = testedObject.Add("1,2");
        assertThat(result).isEqualTo(3);
    }
    @Test
    public void shouldReturnNineWhenFiveAndFourPassed() {
        int result = testedObject.Add("5,4");
        assertThat(result).isEqualTo(9);
    }
    @Test
    public void shouldReturn15WhenFiveTwoEightPassed() {
        int result = testedObject.Add("5,2,8");
        assertThat(result).isEqualTo(15);
    }
    @Test
    public void shouldReturn27WhenNineNineNinePassed() {
        int result = testedObject.Add("9,9,9");
        assertThat(result).isEqualTo(27);
    }
    @Test
    public void shouldReturn100When20x5Passed() {
        int result = testedObject.Add("20,20,20,20,20");
        assertThat(result).isEqualTo(100);

    }

//    @Test
//    public void shouldReturnTenWhen_7h3Passed() {
//        int result = testedObject.Add("7h3");
//        assertThat(result).isEqualTo(10);
//    }
//
//    @Test
//    public void shouldReturnTenWhen1hf_coma_dot_3ds6Passed() {
//        int result = testedObject.Add("1h,.3ds6");
//        assertThat(result).isEqualTo(10);
//    }

    private Object[][] parameterForshouldReturnCorrectValueForSpecificInput(){
        return new Object[][]{
                {"7h3", 10},
                {"1h,.3ds6", 10}
        };
    }


    @Test
    @Parameters (method = "parameterForshouldReturnCorrectValueForSpecificInput")
    public void shouldReturnCorrectValueForSpecificInput(String input, int expectedResult){
      int result = testedObject.Add(input);
        assertThat(result).isEqualTo(expectedResult);
    }

}