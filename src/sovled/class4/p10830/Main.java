package sovled.class4.p10830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 행렬 곱셈
 */
public class Main {
    static int[][] matrix;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        long b = Long.parseLong(split[1]);

        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(split[j]);
            }
        }

        int[][] result = multiply(b);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(result[i][j] % 1000 + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int[][] multiply(long b) {
        if (b == 1) {
            return matrix;
        } else if (b % 2 == 0) {
            int[][] matrix1 = multiply(b / 2);

            return multiply(matrix1, matrix1);
        } else {
            int[][] matrix1 = multiply(b - 1);

            return multiply(matrix1, matrix);
        }
    }

    private static int[][] multiply(int[][] matrix1, int[][] matrix2) {
        int[][] newMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    newMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
                newMatrix[i][j] %= 1000;
            }
        }

        return newMatrix;
    }
}
