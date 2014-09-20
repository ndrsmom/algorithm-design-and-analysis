import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * Tests for {@link ThreadedInversionCounter}.
 */
public class ThreadedInversionCounterTest {
    private static final String PATH = "./test/resources/";

    /* Acceptance Tests */

    @Test
    public void emptyListTest() throws Exception {
        long count = ThreadedInversionCounter.countInversions(new String[]{PATH + "Test1.txt"});
        assertEquals(0L, count);
    }

    @Test
    public void singleEltListTest() throws Exception {
        long count = ThreadedInversionCounter.countInversions(new String[]{PATH + "Test2.txt"});
        assertEquals(0L, count);
    }

    @Test
    public void inorderListTest() throws Exception {
        long count = ThreadedInversionCounter.countInversions(new String[]{PATH + "Test3.txt"});
        assertEquals(0L, count);
    }

    @Test
    public void backwardsOrderListTest() throws Exception {
        long count = ThreadedInversionCounter.countInversions(new String[]{PATH + "Test4.txt"});
        assertEquals(15L, count);
    }

    /* Unit Tests */

    @Test
    public void mixedOrderListRecursionTest() throws Exception {
        List<Integer> array = Arrays.asList(9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0);
        List<Integer> sorted = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);

        ThreadedInversionCounter.CounterTask task = new ThreadedInversionCounter.CounterTask(array);
        ForkJoinPool pool = new ForkJoinPool(4);
        pool.invoke(task);
        assertArrayEquals(sorted.toArray(), task.array().toArray());
    }


    @Test
    public void mixedOrderListMergeTest() throws Exception {
       ThreadedInversionCounter.CounterTask left = new ThreadedInversionCounter.CounterTask(Arrays.asList(0, 2, 4));
       ThreadedInversionCounter.CounterTask right = new ThreadedInversionCounter.CounterTask(Arrays.asList(1, 3, 5));
       List<Integer> sorted = Arrays.asList(0, 1, 2, 3, 4, 5);

       List<Integer> result = new ThreadedInversionCounter.CounterTask(Collections.EMPTY_LIST).merge(left, right);
       assertArrayEquals(sorted.toArray(), result.toArray());
    }
}
