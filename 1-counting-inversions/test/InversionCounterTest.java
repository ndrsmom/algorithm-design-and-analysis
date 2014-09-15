import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for {@link InversionCounter}.
 */
public class InversionCounterTest {
    private static final String PATH = "./test/resources/";

    /* Acceptance Tests */

    @Test
    public void emptyListTest() throws Exception {
        long count = InversionCounter.countInversions(new String[]{PATH + "Test1.txt"});
        assertEquals(0L, count);
    }
}

