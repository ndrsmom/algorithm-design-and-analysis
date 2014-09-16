import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Computes the number of inversions in an array of integers
 * using Java 7 fork/join framework.
 */
public class InversionCounterMultithreaded {

    public static void main(String[] args) {
        try {
            String[] input = {"./resources/IntegerArray.txt"};
            System.out.println("Inversions: " + countInversions(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Long countInversions(String[] args) throws IOException {
        List<Integer> array = InversionCounter.parseArgs(args);
        InversionCounterTask counter = new InversionCounterTask(array);
        ForkJoinPool pool = new ForkJoinPool(4);
        pool.invoke(counter);
        return counter.getCount();
    }

    // This would typically be in a separate source file,
    // just want to keep each experiment grouped together.
    public static class InversionCounterTask extends RecursiveAction {
        private final List<Integer> array;
        private List<Integer> sorted;
        private Long count = 0L;

        public InversionCounterTask(List<Integer> array) {
            this.array = array;
            this.sorted = new ArrayList<>();
        }

        public int size() {
            return array.size();
        }

        public Long getCount() {
            return count;
        }

        public List<Integer> array() {
            return sorted;
        }

        private void merge(InversionCounterTask left, InversionCounterTask right) {
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
        }

        @Override
        protected void compute() {
            int length = size();
            // base case
            if (length <= 1) {
                count = 0L;
                sorted = array;
            } else {
                int mid = length / 2;
                InversionCounterTask left = new InversionCounterTask(array.subList(0, mid));
                InversionCounterTask right = new InversionCounterTask(array.subList(mid, length));

                // execute the recursive steps
                invokeAll(left, right);

                // combine
                merge(left, right);
                count += left.getCount() + right.getCount();
            }
        }
    }
}
