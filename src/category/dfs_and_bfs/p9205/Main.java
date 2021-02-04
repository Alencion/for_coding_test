package category.dfs_and_bfs.p9205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int cvsCount = Integer.parseInt(br.readLine());

            String[] split = br.readLine().split(" ");
            int[] position = new int[]{Integer.parseInt(split[0]), Integer.parseInt(split[1])};

            int[][] cvsArray = new int[cvsCount][2];
            for (int j = 0; j < cvsCount; j++) {
                split = br.readLine().split(" ");
                cvsArray[j][0] = Integer.parseInt(split[0]);
                cvsArray[j][1] = Integer.parseInt(split[1]);
            }
            split = br.readLine().split(" ");
            int[] dest = new int[]{Integer.parseInt(split[0]), Integer.parseInt(split[1])};

            String answer = solution(position, dest, cvsCount, cvsArray);
            System.out.println(answer);
        }

    }

    private static String solution(int[] position, int[] dest, int cvsCount, int[][] cvsArray) {

        boolean[] visited = new boolean[cvsCount];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(position);

        while(!queue.isEmpty()){
            position = queue.poll();

            if (distance(position, dest)) return "happy";

            for (int i = 0; i < cvsCount; i++) {
                if (!visited[i] && distance(position, cvsArray[i])){
                    queue.add(cvsArray[i]);
                    visited[i] = true;
                }
            }
        }

        return "sad";
    }

    private static boolean distance(int[] position, int[] dest) {
        return Math.abs(position[0] - dest[0]) + Math.abs(position[1] - dest[1]) <= 1000;
    }
}
