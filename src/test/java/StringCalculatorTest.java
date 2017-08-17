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
    @Parameters ({"1,1", "0,0", ",0", "2,2"})
    public void shouldReturnNumbersWhenNPassed(String input, int exception){

        int result = testedObject.Add(input);
        assertThat(result).isEqualTo(exception);
    }

    private Object [][] parametersForCheckingResult(){
       return new Object [][]{
            {"0", 0},
            {"1,1", 2},
            { "2", 2},
            {null, 0},
            {"10,10", 20},
            {"10,10,11;10", 41},
            {"", 0},
        };
    }
    @Test
    @Parameters (method = "parametersForCheckingResult")
    public void parametersForCheckingResultFunction2(String input, int exception) throws Exception{

        int result = testedObject.Add(input);
        assertThat(result).isEqualTo(exception);
    }

}