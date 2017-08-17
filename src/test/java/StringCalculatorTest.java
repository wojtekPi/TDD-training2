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
    public void shouldReturnZeroWhenEmptyStringPassed(){
        int result = testedObject.Add("");
        assertThat(result).isEqualTo(0);

//        assertEquals(result, 0);
    }

    @Test
    public void shouldReturnOneWhenOnePassed() throws Exception {
        int result = testedObject.Add("1");

        assertThat(result).isEqualTo(1);
    }

    @Test
    public void shouldReturnTwoWhenOnePassed() throws Exception {
        int result = testedObject.Add("2");

        assertThat(result).isEqualTo(2);
    }

    @Test
    public void shouldReturnOneiTwoWhenOnePassed() throws Exception {
        int result = testedObject.Add("1,2");

        assertThat(result).isEqualTo(3);
    }


}