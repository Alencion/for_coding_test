package sovled.class5.p2239;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 스도쿠
 * 2021-01-07
 */
public class Main {
    static int map[][];

    public static void main(String[] args) throws Exception {
        map = new int[9][9];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String s = br.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(s.charAt(j) + "");
            }
        }

        go();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    static boolean go() {
        Point myPoint = findZero();
        if (myPoint == null) {
            return true;
        }
        List<Integer> myCanList = can(myPoint.x, myPoint.y);

        if (myCanList.isEmpty()) {
            return false;
        }
        for (int i : myCanList) {
            map[myPoint.x][myPoint.y] = i;
            if (go())
                return true;
        }
        map[myPoint.x][myPoint.y] = 0;
        return false;
    }

    static Point findZero() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (map[i][j] == 0)
                    return new Point(i, j);
            }
        }
        return null;
    }

    static List<Integer> can(int x, int y) { //현재 위치 가능한 숫자 찾기
        List<Integer> can = new ArrayList<>();
        boolean[] ten = new boolean[10];

        for (int i = 0; i < 9; i++) {
            ten[map[x][i]] = true;
            ten[map[i][y]] = true;
        }

        int startx = x - x % 3;
        int starty = y - y % 3;
        for (int i = startx; i < startx + 3; i++) {
            for (int j = starty; j < starty + 3; j++) {
                ten[map[i][j]] = true;
            }
        }

        for (int i = 1; i < 10; i++) {
            if (!ten[i])
                can.add(i);
        }
        return can;
    }
}