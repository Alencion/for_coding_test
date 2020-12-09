package class2.p1978;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] array = new int[n];

        String[] split = br.readLine().split(" ");
        for (int i = 0; i < split.length; i++) {
            array[i] = Integer.parseInt(split[i]);
        }

        int answer = solution(array);

        System.out.println(answer);
    }

    private static int solution(int[] array) {
        int count = 0;

        for (int number : array) {
            if (isPrime(number)) count++;
        }

        return count;
    }

    private static boolean isPrime(int number) {
        int isZeroCount = 0;
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) isZeroCount++;
        }
        return isZeroCount == 2;
    }
}
