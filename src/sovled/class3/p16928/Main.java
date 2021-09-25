package sovled.class3.p16928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 뱀과 사다리 게임
 * 2021-09-25
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");

        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < N + M; i++) {
            split = br.readLine().split(" ");
            map.put(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }

        System.out.println(bfs(map));
    }

    private static int bfs(Map<Integer, Integer> map) {
        boolean[] visited = new boolean[101];
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(1, 0));
        visited[1] = true;

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            if (current.position == 100) return current.count;

            if (map.containsKey(current.position)) {
                current.position = map.get(current.position);
            }

            for (int i = 1; i <= 6; i++) {
                if (current.position + i > 100) {
                    break;
                }
                if (!visited[current.position + i]) {
                    visited[current.position + i] = true;
                    queue.offer(new Position(current.position + i, current.count + 1));
                }
            }
        }

        return 0;
    }

    static class Position {
        int position;
        int count;

        public Position(int position, int count) {
            this.position = position;
            this.count = count;
        }
    }
}
