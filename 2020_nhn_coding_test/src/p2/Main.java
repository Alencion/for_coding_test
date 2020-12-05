package p2;

import org.junit.Test;

public class Main {
    private static void solution(int day, int width, int[][] blocks) {
        // TODO: 이곳에 코드를 작성하세요. 추가로 필요한 함수와 전역변수를 선언해서 사용하셔도 됩니다.
        int total = 0;
        int[] walls = new int[width];

        for (int i = 0; i < day; i++) {
            for (int j = 0; j < width; j++) {
                walls[j] += blocks[i][j];
            }

            int left = 0, right = 1;

            while (right < width) {
                if (walls[left] <= walls[right]) {
                    for (int j = left + 1; j < right; j++) {
                        total += walls[left] - walls[j];
                        walls[j] = walls[left];
                    }
                    left = right;
                    right += 1;
                } else if (walls[left] > walls[right]) {
                    right++;
                }
            }

            right = width - 1;
            left = right - 1;
            while (left >= 0) {
                if (walls[left] >= walls[right]) {
                    for (int j = right - 1; j > left; j--) {
                        total += walls[right] - walls[j];
                        walls[j] = walls[right];
                    }
                    right = left;
                    left -= 1;
                } else if (walls[left] < walls[right]) {
                    left--;
                }
            }
        }
        System.out.println(total);
    }

    @Test
    public void test(){
        int day = 2;
        int width = 6;
        int[][] blocks = {{6, 2, 11, 0, 3, 5}, {6, 3, 0, 9, 0, 5}};

        solution(day, width, blocks);
    }
}