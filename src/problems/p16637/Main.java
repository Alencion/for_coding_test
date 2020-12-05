package problems.p16637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String expression = br.readLine();

        int[][] maxCache = new int[10][10];
        int[][] minCache = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                maxCache[i][j] = Integer.MIN_VALUE;
                minCache[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < N / 2 + 1; i++) {
            maxCache[i][i] = expression.charAt(i * 2) - '0';
            minCache[i][i] = expression.charAt(i * 2) - '0';
        }

        for (int count = 1; count < N / 2 + 1; count++) {  // 한 연산에 포함시킬 숫자 갯수
            for (int index = 0; index < N / 2 + 1 - count; index++) { // 연산이 사작되는 숫자 index
                for (int i = 1, j = count; i <= count; i++, j--) {

                    // ex) count = 2이고, index = 0이라면
                    // calculate(0번째 숫자, 1~2번째 숫자들의 연산장 값)
                    // calculate(0~1번째 숫자들의 연산 값, 2번째 숫자)
                    // 이 중 최고값 최소값을 저

                    int[] candidates = new int[4];

                    candidates[0] = calculate(maxCache[index][index + count - j], maxCache[index + i][index + count], expression.charAt((index + count - j) * 2 + 1));
                    candidates[1] = calculate(maxCache[index][index + count - j], minCache[index + i][index + count], expression.charAt((index + count - j) * 2 + 1));
                    candidates[2] = calculate(minCache[index][index + count - j], maxCache[index + i][index + count], expression.charAt((index + count - j) * 2 + 1));
                    candidates[3] = calculate(minCache[index][index + count - j], minCache[index + i][index + count], expression.charAt((index + count - j) * 2 + 1));

                    Arrays.sort(candidates);

                    maxCache[index][index + count] = Math.max(maxCache[index][index + count], candidates[3]);
                    minCache[index][index + count] = Math.min(minCache[index][index + count], candidates[0]);
                }
            }
        }

        System.out.println(maxCache[0][N / 2]);
    }

    private static int calculate(int a, int b, char operation) {
        int result = 0;

        switch (operation) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
        }

        return result;
    }
}