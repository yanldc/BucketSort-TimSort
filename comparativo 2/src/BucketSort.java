import java.util.ArrayList;
import java.util.Collections;

public class BucketSort {
    private int comparisonCount;
    private int movementCount;

    public void sort(int[] array) {
        comparisonCount = 0;
        movementCount = 0;

        int numberOfBuckets = array.length;

        if (numberOfBuckets <= 1) {
            return; // Array already sorted
        }

        // Find maximum and minimum values to scale the values properly
        int maxValue = array[0];
        int minValue = array[0];
        for (int value : array) {
            if (value > maxValue) {
                maxValue = value;
            }
            if (value < minValue) {
                minValue = value;
            }
            comparisonCount += 2; // two comparisons per iteration
        }

        // Step 1: Create empty buckets
        ArrayList<Integer>[] buckets = new ArrayList[numberOfBuckets];
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Step 2: Distribute input array values into buckets
        for (int value : array) {
            int bucketIndex = (int) ((double) (value - minValue) / (maxValue - minValue + 1) * (numberOfBuckets - 1));
            buckets[bucketIndex].add(value);
            movementCount++;
        }

        // Step 3: Sort individual buckets
        for (ArrayList<Integer> bucket : buckets) {
            Collections.sort(bucket);
            // The number of comparisons for sorting a bucket cannot be easily counted,
            // thus we don't count it here.
        }

        // Step 4: Concatenate all buckets into array
        int index = 0;
        for (ArrayList<Integer> bucket : buckets) {
            for (int value : bucket) {
                array[index++] = value;
                movementCount++;
            }
        }
    }

    public void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public int getComparisonCount() {
        return comparisonCount;
    }

    public int getMovementCount() {
        return movementCount;
    }
}
