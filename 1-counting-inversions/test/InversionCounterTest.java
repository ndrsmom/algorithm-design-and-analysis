import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Tests for {@link InversionCounter}.
 */
public class InversionCounterTest {
    private static final String PATH = "../resources/test/";

    /* Acceptance Tests */

    @Test
    public void emptyListTest() throws Exception {
        long count = InversionCounter.countInversions(new String[]{PATH + "SortTest1.txt"});
        assertEquals(0L, count);
    }

    @Test
    public void singleEltListTest() throws Exception {
        long count = InversionCounter.countInversions(new String[]{PATH + "SortTest2.txt"});
        assertEquals(0L, count);
    }

    @Test
    public void inorderListTest() throws Exception {
        long count = InversionCounter.countInversions(new String[]{PATH + "SortTest3.txt"});
        assertEquals(0L, count);
    }

    @Test
    public void backwardsOrderListTest() throws Exception {
        long count = InversionCounter.countInversions(new String[]{PATH + "SortTest4.txt"});
        assertEquals(15L, count);
    }

    /* Unit Tests */

    @Test
    public void mixedOrderListRecursionTest() throws Exception {
        List<Integer> array = Arrays.asList(9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0);
        List<Integer> sorted = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);

        InversionCounter.Pair<List<Integer>, Long> result = InversionCounter.recursiveSortAndCount(array);
        assertEquals(56L, result.second().longValue());
        assertArrayEquals(sorted.toArray(), result.first().toArray());
    }

    @Test
    public void mixedOrderListMergeTest() throws Exception {
        List<Integer> left = Arrays.asList(0, 2, 4);
        List<Integer> right = Arrays.asList(1, 3, 5);
        List<Integer> sorted = Arrays.asList(0, 1, 2, 3, 4, 5);

        InversionCounter.Pair<List<Integer>, Long> result = InversionCounter.mergeAndCountSplit(left, right);
        assertEquals(3L, result.second().longValue());
        assertArrayEquals(sorted.toArray(), result.first().toArray());
    }
}

