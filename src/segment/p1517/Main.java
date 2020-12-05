package segment.p1517;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long swapCount = 0;
    static long[] sorted;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        sorted = new long[n];
        long[] arr = new long[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        mergeSort(arr, 0, n - 1);

        System.out.println(swapCount);
    }

    static void mergeSort(long[] arr, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    static void merge(long[] arr, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int index = start;

        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j])
                sorted[index++] = arr[i++];
            else {
                sorted[index++] = arr[j++];
                swapCount += (mid + 1 - i);
            }
        }

        while (i <= mid)
            sorted[index++] = arr[i++];

        while (j <= end)
            sorted[index++] = arr[j++];

        if (end > start) System.arraycopy(sorted, start, arr, start, end + 1 - start);
    }
}
