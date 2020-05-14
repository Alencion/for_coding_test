package class2.p1018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 체스판다시 칠하기
public class Main {
    public static final char WHITE = 'W';
    public static final char BLACK = 'B';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputStringSplits = br.readLine().split(" ");

        int n = Integer.parseInt(inputStringSplits[0]);
        int m = Integer.parseInt(inputStringSplits[1]);

        char[][] chessBoard = new char[n][m];

        String inputString;

        for (int i = 0; i < n; i++) {
            inputString = br.readLine();
            for (int j = 0; j < inputString.length(); j++) {
                chessBoard[i][j] = inputString.charAt(j);
            }
        }

        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < n - 7; i++) {
            for (int j = 0; j < m - 7; j++) {
                results.add(checkChangeCount(i, j, chessBoard,WHITE));
                results.add(checkChangeCount(i, j, chessBoard,BLACK));
            }
        }
        System.out.println(Collections.min(results));
    }

    private static int checkChangeCount(int i, int j, char[][] chessBoard, char chess) {
        int count = 0;
        int number;
        if (chess == WHITE) number = 1;
        else number = 0;

        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                if (number % 2 != checkBoard(chessBoard[i + k][j + l])) count++;
                number++;
            }
            number--;
        }
        return count;
    }

    private static int checkBoard(char chess) {
        if (chess == WHITE) return 1;
        else return 0;
    }
}

