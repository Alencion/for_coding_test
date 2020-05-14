package skillTest.p5;

import java.util.Arrays;
import java.util.Stack;

class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        boolean[][] visit = new boolean[m][n];
        Stack<Point> stack = new Stack<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                if (picture[i][j] != 0 && !visit[i][j]) {
                    stack.push(new Point(i, j));
                    visit[i][j] = true;
                    numberOfArea++;
                }


                while(!stack.isEmpty()){
                    Point pop = stack.pop();
                    count++;
                    if (pop.x > 0 && picture[pop.x-1][pop.y]== picture[i][j] && !visit[pop.x-1][pop.y]) {
                        stack.push(new Point(pop.x-1,pop.y));
                        visit[pop.x-1][pop.y] = true;
                    }
                    if (pop.x < m-1 && picture[pop.x+1][pop.y]== picture[i][j] && !visit[pop.x+1][pop.y]) {
                        stack.push(new Point(pop.x+1,pop.y));
                        visit[pop.x+1][pop.y] = true;
                    }
                    if (pop.y > 0 && picture[pop.x][pop.y-1]== picture[i][j] && !visit[pop.x][pop.y-1]) {
                        stack.push(new Point(pop.x,pop.y-1));
                        visit[pop.x][pop.y-1] = true;
                    }
                    if (pop.y < n-1 && picture[pop.x][pop.y+1]== picture[i][j] && !visit[pop.x][pop.y+1]) {
                        stack.push(new Point(pop.x,pop.y+1));
                        visit[pop.x][pop.y+1] = true;
                    }

                }
                maxSizeOfOneArea = Math.max(count, maxSizeOfOneArea);
            }
        }
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[][] table = {{1, 1, 1, 0},
//                {1, 2, 2, 0},
//                {1, 0, 0, 1},
//                {0, 0, 0, 1},
//                {0, 0, 0, 3},
//                {0, 0, 0, 3}};
        int[][] table = {{1, 1, 1, 0},
                {1, 1, 1, 0},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1}};
        int[] solution1 = solution.solution(6, 4, table);
        System.out.println(Arrays.toString(solution1));
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}