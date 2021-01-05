package class5.p1799;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 비숍
 * 2021-01-05
 */
public class Main {

    static int lAns = 0;
    static int rAns = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<int[]> lMap = new ArrayList<>(); // 대각선에서 나올 수 있는 판
        ArrayList<int[]> rMap = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int can = sc.nextInt();
                if (can == 1) {
                    int[] input = {i, j};
                    if ((i + j) % 2 == 0) // 왼쪽 맵의 비숍
                        lMap.add(input);
                    else
                        rMap.add(input);
                }
            }
        }
        boolean[] lvisit = new boolean[lMap.size()];
        boolean[] rvisit = new boolean[rMap.size()];

        lbfs(lMap, lvisit, 0, 0);
        rbfs(rMap, rvisit, 0, 0);

        System.out.println(rAns + lAns);
    }

    private static void lbfs(ArrayList<int[]> map, boolean[] visit, int n, int cnt) {
        if (n >= map.size()) {
            lAns = lAns < cnt ? cnt : lAns;
            return;
        }

        if (cnt + (map.size() - n + 1) < lAns) return;

        for (int i = n; i < map.size(); i++) {
            if (isNotDiagonal(map, visit, i)) {
                visit[i] = true;
                lbfs(map, visit, i + 1, cnt + 1);
                visit[i] = false;
            }
        }

        lAns = lAns < cnt ? cnt : lAns;
    }

    private static void rbfs(ArrayList<int[]> map, boolean[] visit, int n, int cnt) {
        if (n >= map.size()) {
            rAns = rAns < cnt ? cnt : rAns;
            return;
        }

        if (cnt + (map.size() - n + 1) < rAns) return;

        for (int i = n; i < map.size(); i++) {
            if (isNotDiagonal(map, visit, i)) {
                visit[i] = true;
                rbfs(map, visit, i + 1, cnt + 1);
                visit[i] = false;
            }
        }

        rAns = rAns < cnt ? cnt : rAns;
    }

    private static boolean isNotDiagonal(ArrayList<int[]> map, boolean[] visit, int n) {
        int[] a = map.get(n);

        for (int i = 0; i < n; i++) {
            if (visit[i]) {
                int[] b = map.get(i);
                if (Math.abs(a[0] - b[0]) == Math.abs(a[1] - b[1])) { // 대각선에 있는 경우.
                    return false;
                }
            }
        }
        return true;
    }
}