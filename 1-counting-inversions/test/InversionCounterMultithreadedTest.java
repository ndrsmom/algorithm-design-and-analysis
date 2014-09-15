import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Tests for {@link InversionCounterMultithreaded}.
 */
public class InversionCounterMultithreadedTest {
    private static final String PATH = "./test/resources/";

    /* Acceptance Tests */

    @Test
    public void emptyListTest() throws Exception {
        long count = InversionCounterMultithreaded.countInversions(new String[]{PATH + "Test1.txt"});
        assertEquals(0L, count);
    }

    @Test
    public void singleEltListTest() throws Exception {
        long count = InversionCounterMultithreaded.countInversions(new String[]{PATH + "Test2.txt"});
        assertEquals(0L, count);
    }

    @Test
    public void inorderListTest() throws Exception {
        long count = InversionCounterMultithreaded.countInversions(new String[]{PATH + "Test3.txt"});
        assertEquals(0L, count);
    }

    @Test
    public void backwardsOrderListTest() throws Exception {
        long count = InversionCounterMultithreaded.countInversions(new String[]{PATH + "Test4.txt"});
        assertEquals(15L, count);
    }

    /* Unit Tests */

   // TODO
}
