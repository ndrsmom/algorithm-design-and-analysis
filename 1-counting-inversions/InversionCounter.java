import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Computes the number of inversions in an array of integers.
 */
public class InversionCounter {

	public static void main(String[] args) {
		try {
            String[] input = {"./resources/IntegerArray.txt"};
			System.out.println("Inversions: " + countInversions(input));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Long countInversions(String[] args) throws IOException {
        return recursiveSortAndCount(parseArgs(args)).second();
	}

    /**
     * Parse the array of integers from a file.
     */
    private static List<Integer> parseArgs(String[] args) throws IOException {
        if (args.length < 1) {
            throw new IllegalArgumentException("Input file not specified.");
        }
        List<Integer> inputList = new ArrayList<>();
        Path input = Paths.get(args[0]);
        try (BufferedReader reader =
                     Files.newBufferedReader(input, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                inputList.add(Integer.parseInt(line));
            }
        } catch (IOException ioe) {
            System.out.println("Problem reading input file " + args[0] + ".");
            System.out.println(ioe.getMessage());
        } catch (NumberFormatException nfe) {
            System.out.println("Input file invalid; contains non-numeric data.");
            System.out.println(nfe.getMessage());
        }
        return inputList;
    }

    /**
     * Recursively compute the number of inversions via sorting.
     */
    protected static Pair<List<Integer>, Long> recursiveSortAndCount(List<Integer> array) {
        int length = array.size();
        // base case
        if (length <= 1) {
            return new Pair<>(array, 0L);

        } else {
            int mid = length / 2;
            Pair<List<Integer>, Long> left = recursiveSortAndCount(array.subList(0, mid));
            Pair<List<Integer>, Long> right = recursiveSortAndCount(array.subList(mid, length));
            Pair<List<Integer>, Long> merged = mergeAndCountSplit(left.first(), right.first());
            return new Pair<>(merged.first(), left.second() + right.second() + merged.second());
        }
    }

    /**
     * Compute the split inversions by merging the sorted arrays.
     */
    protected static Pair<List<Integer>, Long> mergeAndCountSplit(List<Integer> left, List<Integer> right) {
        List<Integer> merged = new ArrayList<>();
        Long count = 0L;
        int i = 0;
        int j = 0;
        for (int k = 0; k < left.size() + right.size(); k++) {
            if (j >= right.size() || (i < left.size() && left.get(i) < right.get(j))) {
                merged.add(left.get(i));
                i++;
            } else {
                merged.add(right.get(j));
                j++;

                // inversion if copying from right list before exhausting left list
                if (i < left.size()) {
                    // increment inversion count by number of elements remaining in left
                    // because all remaining are less than right[j]
                    count += left.size() - i;
                }
            }
        }
        return new Pair<>(merged, count);
    }

    /**
     * Helper class for creating pairs
     */
    public static class Pair<A, B> {
        public final A first;
        public final B second;
        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }
        public A first() {
            return first;
        }
        public B second() {
            return second;
        }
    }

}
