package class2.p1920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] splits = br.readLine().split(" ");

        int[] arrayA = new int[n];
        for (int i = 0; i < n; i++) {
            arrayA[i] = Integer.parseInt(splits[i]);
        }

        int m = Integer.parseInt(br.readLine());
        splits = br.readLine().split(" ");

        int[] arrayB = new int[m];
        for (int i = 0; i < m; i++) {
            arrayB[i] = Integer.parseInt(splits[i]);
        }

        List<Integer> answer = solution(arrayA, arrayB);
        answer.forEach(System.out::println);
    }

    private static List<Integer> solution(int[] arrayA, int[] arrayB) {
        List<Integer> answer = new ArrayList<>();

        Arrays.sort(arrayA);

        for (int b : arrayB) {
            int start = 0;
            int end = arrayA.length - 1;

            boolean find = false;
            while (start <= end) {
                int mid = (start + end) / 2;

                if (arrayA[mid] == b) {
                    find = true;
                    break;
                }
                if (arrayA[mid] < b) start = mid + 1;
                else if(arrayA[mid] > b) end = mid - 1;
            }

            if (find) answer.add(1);
            else answer.add(0);
        }
        return answer;
    }
}
