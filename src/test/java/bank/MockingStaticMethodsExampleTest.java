package bank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Tdd training on 17.08.17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(PaymentService.class) // PaymentService.class contains static methods
public class MockingStaticMethodsExampleTest {

    @Test
    public void mockingStaticMethodExample() throws Exception {
        PowerMockito.mockStatic(PaymentService.class);

        Mockito.when(PaymentService.getCurrentDate())
                .thenReturn(LocalDate.parse("2012-12-03"));

        assertThat(PaymentService.getCurrentDate().getYear())
                .isEqualTo(2012);
    }

    @Test
    public void withoutMocking() throws Exception {
        assertThat(PaymentService.getCurrentDate().getYear())
                .isEqualTo(LocalDate.now().getYear());
    }
}
