package bank;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Tdd training on 16.08.17.
 */

public class MockingExamplesTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private Map mapMock;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void listTest() throws Exception {
        //mock creation
        List mockedList = mock(List.class);


        //using mock object
        mockedList.add("one");
        mockedList.add("oneTwo");
        Object result = mockedList.get(0);

        verify(mockedList, times(1)).add("one");

    }

    @Test
    public void listSizeTest() throws Exception {
        //mock creation
        List mockedList = mock(List.class);


        when(mockedList.size()).thenReturn(100);

        int result = mockedList.size();

        assertThat(result).isEqualTo(100);
    }

    @Test(expected = NullPointerException.class)
    public void mockThrowExceptionExample() throws Exception {
        LinkedList mockedLinkList = mock(LinkedList.class);

        when(mockedLinkList.get(anyInt())).thenThrow(NullPointerException.class);

        mockedLinkList.get(7);
    }

    @Test
    public void mockCreatedByAnnotationExample() throws Exception {
        when(mapMock.get("Ala")).thenReturn("ma Kota");

        assertThat(mapMock.get("Ala")).isEqualTo("ma Kota");
    }
}
