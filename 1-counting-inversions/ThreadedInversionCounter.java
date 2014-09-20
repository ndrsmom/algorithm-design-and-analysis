import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Computes the number of inversions in an array of integers
 * using Java 7 fork/join framework.
 */
public class ThreadedInversionCounter {

    public static void main(String[] args) {
        try {
            String[] input = {"../resources/IntegerArray.txt"};
            System.out.println("Inversions: " + countInversions(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Long countInversions(String[] args) throws IOException {
        List<Integer> array = InversionCounter.parseArgs(args);
        CounterTask counter = new CounterTask(array);
        ForkJoinPool pool = new ForkJoinPool(4);
        pool.invoke(counter);
        return counter.getCount();
    }

    // This would typically be in a separate source file,
    // just want to keep each experiment grouped together.
    public static class CounterTask extends RecursiveAction {
        private List<Integer> array;
        private Long count = 0L;

        public CounterTask(List<Integer> array) {
            this.array = array;
        }

        public int size() {
            return array.size();
        }

        public Long getCount() {
            return count;
        }

        public List<Integer> array() {
            return array;
        }

        protected List<Integer> merge(CounterTask left, CounterTask right) {
            List<Integer> sorted = new ArrayList<>();
            int i = 0;
            int j = 0;
            while (i < left.size() && j < right.size()) {
                if (left.array().get(i) < right.array().get(j)) {
                    sorted.add(left.array().get(i));
                    i++;
                } else {
                    sorted.add(right.array().get(j));
                    j++;

                    // increment inversion count by number of elements remaining in left
                    // because all remaining are less than right[j]
                    count += left.size() - i;
                }
            }
            sorted.addAll(left.array().subList(i, left.size()));
            sorted.addAll(right.array().subList(j, right.size()));
            return sorted;
        }

        @Override
        protected void compute() {
            int length = size();
            // base case
            if (length <= 1) {
                count = 0L;
            } else {
                int mid = length / 2;
                CounterTask left = new CounterTask(array.subList(0, mid));
                CounterTask right = new CounterTask(array.subList(mid, length));

                // execute the recursive steps
                invokeAll(left, right);

                // combine
                array = merge(left, right);
                count += left.getCount() + right.getCount();
            }
        }
    }
}
