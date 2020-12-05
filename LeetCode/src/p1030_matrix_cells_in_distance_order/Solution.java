package p1030_matrix_cells_in_distance_order;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Solution {
    //          상 우 하  좌
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0 ,-1, 0};
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        List<Point> answer = new ArrayList<>();
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        queue.add(new Point(r0, c0));
        visited[r0][c0] = true;
        while(!queue.isEmpty()){
            Point current = queue.poll();
            answer.add(current);

            for (int i = 0; i < 4; i++) {
                int newY = current.y + dy[i];
                int newX = current.x + dx[i];
                if (newY >= 0 && newY < R && newX >= 0 && newX <C){
                    if (!visited[newY][newX]){
                        queue.add(new Point(newY, newX));
                        visited[newY][newX] = true;
                    }
                }
            }
        }

        List<int[]> collect = answer.stream().map(point -> new int[]{point.y, point.x}).collect(Collectors.toList());
        int[][] ints = new int[collect.size()][2];
        for (int i = 0; i < collect.size(); i++) {
            ints[i] = collect.get(i);
        }
        return ints;
    }

    private static class Point{
        int y;
        int x;

        public Point(int r0, int c0) {
            this.y = r0;
            this.x = c0;
        }
    }
    @Test
    void test() {
        int R = 1;
        int C = 2;
        int r0 = 0;
        int c0 = 1;

        new Solution().allCellsDistOrder(R, C, r0, c0);
    }
}
