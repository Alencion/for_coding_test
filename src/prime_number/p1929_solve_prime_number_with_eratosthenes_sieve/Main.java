package prime_number.p1929_solve_prime_number_with_eratosthenes_sieve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int findPrimeNumberStart = Integer.parseInt(split[0]);
        int findPrimeNumberEnd = Integer.parseInt(split[1]);

        int[] primeNumbers = solution(findPrimeNumberStart, findPrimeNumberEnd);
        for (int primeNumber : primeNumbers) {
            System.out.println(primeNumber);
        }
    }

    private static int[] solution(int start, int end) {
        List<Integer> result = new ArrayList<>();
        int[] isPrime = new int[end + 1];
        int sqrt = (int) Math.sqrt(Integer.MAX_VALUE);

        for (int i = 2; i <= end; i++) {
            isPrime[i] = i;
        }

        for (int i = 2; i <= end; i++) {
            if (isPrime[i] == 0) continue;

            if (i >= start) result.add(i);
            if (i < sqrt) {
                for (int j = i * i; j <= end; j += i) {
                    isPrime[j] = 0;
                }
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}
