package class5.p7453;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 합이 0인 네 정수
 * 2021-01-23
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] array = new int[4][n];

        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");

            for (int j = 0; j < 4; j++) {
                array[j][i] = Integer.parseInt(split[j]);
            }
        }

        int[] first = new int[n*n];
        int[] second = new int[n*n];

        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                first[index] = array[0][i] + array[1][j];
                second[index] = array[2][i] + array[3][j];
                index++;
            }
        }

        Arrays.sort(first);
        Arrays.sort(second);

        int head = 0;
        int back = second.length - 1;
        long answer = 0;

        while(head < first.length && back >= 0){
            int firstValue = first[head];
            int secondValue = second[back];

            int sum = firstValue + secondValue;
            if (sum == 0) {
                int firstValueCount = 0;
                int secondValueCount = 0;

                while(head < first.length && firstValue == first[head]){
                    firstValueCount++;
                    head++;
                }

                while(back >= 0 && secondValue == second[back]){
                    secondValueCount++;
                    back--;
                }
                answer += (long) firstValueCount * (long) secondValueCount;
            }
            else if(sum > 0) {
                back--;
            } else {
                head++;
            }
        }
        System.out.println(answer);
    }
}
