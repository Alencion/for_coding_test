package sovled.class3.p16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    //
    static int[] dx = {0, -1, 1, 0};
    static int[] dy = {-1, 0, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] shark = null;
        int fishCount = 0;

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(split[j]);

                if (map[i][j] == 9) {
                    map[i][j] = 0;
                    shark = new int[]{i, j};
                } else if (map[i][j] != 0) fishCount++;
            }
        }

        System.out.println(bfs(new Position(shark[0], shark[1], 0), 2, map, fishCount));
    }

    private static int bfs(Position shark, int size, int[][] map, int fishCount) {
        int time = 0;

        boolean[][] visited;
        int eatCount = 0;
        for (int i = 0; i < fishCount; i++) {
            Queue<Position> queue = new PriorityQueue<>();
            shark.distance = 0;
            queue.add(shark);
            visited = new boolean[map.length][map.length];

            while (!queue.isEmpty()) {
                Position ints = queue.poll();
                visited[ints.y][ints.x] = true;

                if (map[ints.y][ints.x] > 0 && map[ints.y][ints.x] < size) {
                    map[ints.y][ints.x] = 0;
                    time += ints.distance;
                    shark = ints;
                    eatCount++;
                    if (eatCount == size) {
                        size++;
                        eatCount = 0;
                    }
                    break;
                }

                for (int j = 0; j < 4; j++) {
                    int newY = ints.y + dy[j];
                    int newX = ints.x + dx[j];

                    if (!valid(newY, map.length) || !valid(newX, map.length))continue;

                    if (!visited[newY][newX] && map[newY][newX] <= size) {
                        queue.add(new Position(newY, newX, ints.distance + 1));
                    }
                }
            }
        }
        return time;
    }

    private static boolean valid(int newX, int length) {
        return 0 <= newX && newX < length;
    }

    static class Position implements Comparable<Position> {
        int y;
        int x;
        int distance;

        public Position(int y, int x, int distance) {
            this.y = y;
            this.x = x;
            this.distance = distance;
        }

        @Override
        public int compareTo(Position o) {
            if (this.distance == o.distance) {
                if (this.y == o.y) return Integer.compare(this.x, o.x);
                else return Integer.compare(this.y, o.y);
            }
            return Integer.compare(this.distance, o.distance);
        }
    }
}
