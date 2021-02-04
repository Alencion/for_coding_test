package sovled.class5.p9328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            String[] split = br.readLine().split(" ");

            int row = Integer.parseInt(split[0]);
            int column = Integer.parseInt(split[1]);

            char[][] map = new char[row][column];
            boolean[][] visited = new boolean[row][column];
            for (int j = 0; j < row; j++) {
                String readLine = br.readLine();

                for (int k = 0; k < readLine.length(); k++) {
                    map[j][k] = readLine.charAt(k);
                    if (map[j][k] == '*') visited[j][k] = true;
                }
            }

            Set<Character> keys = new HashSet<>();

            for (char c : br.readLine().toCharArray()) {
                keys.add(Character.toUpperCase(c));
            }

            Queue<int[]> queue = new LinkedList<>();
            List<int[]> subQueue = new ArrayList<>();
            findEntrance(queue, map, keys, subQueue);

            int answer = 0;
            while (true) {

                convertMap(keys, map);
                while (!queue.isEmpty()) {
                    int[] current = queue.poll();
                    if (Character.isUpperCase(map[current[0]][current[1]]) || visited[current[0]][current[1]]) continue;
                    visited[current[0]][current[1]] = true;

                    if (map[current[0]][current[1]] == '$') answer++;

                    for (int j = 0; j < 4; j++) {
                        int newY = current[0] + dy[j];
                        int newX = current[1] + dx[j];

                        if (newY < 0 || newX < 0 || newY >= row || newX >= column || visited[newY][newX]) continue;
                        if (map[newY][newX] == '*') continue;
                        if (!Character.isUpperCase(map[newY][newX])) {
                            queue.add(new int[]{newY, newX});
                            if (Character.isLowerCase(map[newY][newX])) {
                                keys.add(Character.toUpperCase(map[newY][newX]));
                            }
                        } else {
                            subQueue.add(new int[]{newY, newX});
                        }
                    }
                }

                for (int[] ints : subQueue) {
                    if (keys.contains(map[ints[0]][ints[1]]) && !visited[ints[0]][ints[1]]) {
                        queue.add(ints);
                    }
                }

                if (queue.isEmpty()) break;
            }

            System.out.println(answer);
        }
    }

    private static void convertMap(Set<Character> keys, char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (keys.contains(map[i][j])) map[i][j] = '.';
            }
        }
    }


    private static void findEntrance(Queue<int[]> queue, char[][] map, Set<Character> keys, List<int[]> subQueue) {
        for (int i = 0; i < map.length; i++) {
            if (map[i][0] != '*') {
                if (!Character.isUpperCase(map[i][0])) {
                    queue.add(new int[]{i, 0});
                    if (Character.isLowerCase(map[i][0])) keys.add(Character.toUpperCase(map[i][0]));
                } else {
                    subQueue.add(new int[]{i, 0});
                }
            }
            if (map[i][map[0].length - 1] != '*') {
                if (!Character.isUpperCase(map[i][map[0].length - 1])){
                    queue.add(new int[]{i, map[0].length - 1});
                    if (Character.isLowerCase(map[i][map[0].length - 1]))
                        keys.add(Character.toUpperCase(map[i][map[0].length - 1]));
                } else {
                    subQueue.add(new int[]{i, map[0].length - 1});
                }
            }
        }

        for (int i = 0; i < map[0].length; i++) {
            if (map[0][i] != '*') {
                if (!Character.isUpperCase(map[0][i])){
                    queue.add(new int[]{0, i});
                    if (Character.isLowerCase(map[0][i])) keys.add(Character.toUpperCase(map[0][i]));
                } else {
                    subQueue.add(new int[]{0, i});
                }
            }
            if (map[map.length - 1][i] != '*') {
                if (!Character.isUpperCase(map[map.length - 1][i])){
                    queue.add(new int[]{map.length - 1, i});
                    if (Character.isLowerCase(map[map.length - 1][i]))
                        keys.add(Character.toUpperCase(map[map.length - 1][i]));
                } else {
                    subQueue.add(new int[]{map.length - 1, i});
                }
            }
        }
    }
}
