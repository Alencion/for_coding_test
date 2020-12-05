package p13549;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static boolean[] visit = new boolean[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splits = br.readLine().split(" ");

        int n = Integer.parseInt(splits[0]);
        int k = Integer.parseInt(splits[1]);

        Queue<Position> queue = new LinkedList<>();

        queue.add(new Position(0, n));

        int answer = 0;

        if (n >= k) {
            System.out.println(n - k);
            return;
        }

        while (!queue.isEmpty()) {
            Position position = queue.poll();
            visit[position.value] = true;

            if (position.value == k) {
                answer = position.time;
                break;
            }

            if (2 * position.value <= 100000 && !visit[position.value * 2])
                queue.add(new Position(position.time, position.value * 2));
            if (position.value - 1 >= 0 && !visit[position.value - 1])
                queue.add(new Position(position.time + 1, position.value - 1));
            if (position.value + 1 < 100000 && !visit[position.value + 1])
                queue.add(new Position(position.time + 1, position.value + 1));
        }

        System.out.println(answer);
    }

    static class Position {
        int time;
        int value;

        public Position(int time, int value) {
            this.time = time;
            this.value = value;
        }
    }
}

