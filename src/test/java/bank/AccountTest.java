package bank;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Tdd training on 11.08.17.
 */
public class AccountTest {
    private Account testedObject;

    @Before
    public void setUp() throws Exception {
        testedObject = new Account();
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
    }
}