package sovled.class3.p9019;

import java.io.*;
import java.util.*;

/**
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String[] split = br.readLine().split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);

            bw.write(bfs(a, b) + "\n");
        }
        bw.flush();
    }

    private static String bfs(int a, int b) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(a);
        boolean[] visited = new boolean[10000];
        String[] command = new String[100000];
        Arrays.fill(command, "");

        visited[a] = true;
        Method[] values = Method.values();
        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == b) return command[current];

            int[] array = {(2*current)%10000,
                    (current == 0) ? 9999 : current-1,
                    (current % 1000) * 10 + current/1000,
                    (current % 10) * 1000 + current/10};

            for (int i = 0; i < 4; i++) {
                if (!visited[array[i]]){
                    queue.add(array[i]);
                    command[array[i]] = command[current] + values[i];
                    visited[array[i]] = true;
                }
            }
        }
        return "";
    }

    enum Method {
        D, S, L, R
    }
}
