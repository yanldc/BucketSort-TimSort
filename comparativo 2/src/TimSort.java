import java.util.Arrays;

public class TimSort {
    private static final int RUN = 32;
    private int comparisonCount;
    private int movementCount;

    public void sort(int[] arr) {
        comparisonCount = 0;
        movementCount = 0;

        int n = arr.length;

        for (int i = 0; i < n; i += RUN) {
            insertionSort(arr, i, Math.min((i + 31), (n - 1)));
        }

        for (int size = RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));

                if (mid < right) {
                    merge(arr, left, mid, right);
                }
            }
        }
    }

    private void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > temp) {
                comparisonCount++;
                arr[j + 1] = arr[j];
                j--;
                movementCount++;
            }
            arr[j + 1] = temp;
            movementCount++;
        }
    }

    private void merge(int[] arr, int l, int m, int r) {
        int len1 = m - l + 1, len2 = r - m;
        int[] left = new int[len1];
        int[] right = new int[len2];
        for (int x = 0; x < len1; x++) {
            left[x] = arr[l + x];
            movementCount++;
        }
        for (int x = 0; x < len2; x++) {
            right[x] = arr[m + 1 + x];
            movementCount++;
        }

        int i = 0, j = 0, k = l;

        while (i < len1 && j < len2) {
            comparisonCount++;
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
            movementCount++;
        }

        while (i < len1) {
            arr[k] = left[i];
            k++;
            i++;
            movementCount++;
        }

        while (j < len2) {
            arr[k] = right[j];
            k++;
            j++;
            movementCount++;
        }
    }

    public void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public int getComparisonCount() {
        return comparisonCount;
    }

    public int getMovementCount() {
        return movementCount;
    }
}
