package p4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Main {
    // 우 하 좌 상
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        int m = Integer.parseInt(split[0]);
        int n = Integer.parseInt(split[1]);
        int p = Integer.parseInt(split[2]);

        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++) {
            String data = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = data.charAt(j);
            }
        }

        HashMap<Character, Player> players = new HashMap<>();
        for (int i = 0; i < p; i++) {
            split = br.readLine().split(" ");
            players.put(split[0].charAt(0), new Player(i, split[0].charAt(0), Integer.parseInt(split[1])));
        }
        int bossHp = Integer.parseInt(br.readLine());

        int answer = solution(m, n, map, players, bossHp);
        System.out.println(answer);
    }

    private static int solution(int m, int n, char[][] map, HashMap<Character, Player> players, int bossHp) {

        int[] bossPoint = new int[0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '.' || map[i][j] == 'X') continue;
                if (map[i][j] == 'B') bossPoint = new int[]{i, j};
                else {
                    Player player = players.get(map[i][j]);
                    player.setY(i);
                    player.setX(j);
                }
            }
        }

        // 보스에게 이동
        for (Player player : players.values()) {
            bfs(m, n, map, player, bossPoint);
        }

        List<Player> collect = players.values().stream().filter(player -> player.time != -1)
                .sorted()
                .collect(Collectors.toList());
        int answer = 1;
        int dps = collect.get(0).dps;
        int time = collect.get(0).time;

        for (int i = 1; i < collect.size(); i++) {
            Player player = collect.get(i);
            if (time == player.time) {
                dps += player.dps;
                answer++;

            } else {
                bossHp = bossHp - ((player.time - time) * dps);
                dps += player.dps;
                time = player.time;
                if (bossHp > 0) answer++;
                if (bossHp < 0) break;
            }
        }

        return answer;
    }

    private static void bfs(int m, int n, char[][] map, Player player, int[] bossPoint) {
        boolean[][] visited = new boolean[m][n];

        Queue<Player> queue = new LinkedList<>();
        queue.add(new Player(player.y, player.x, 0));
        visited[player.y][player.x] = true;

        while (!queue.isEmpty()) {
            Player poll = queue.poll();

            if (poll.y == bossPoint[0] && poll.x == bossPoint[1]) {
                player.time = poll.time - 1;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int newY = poll.y + dy[i];
                int newX = poll.x + dx[i];

                if (!(newY >= 0 && newY < m) || !(newX >= 0 && newX < n)) continue;
                if (!visited[newY][newX] && map[newY][newX] != 'X') {
                    visited[newY][newX] = true;
                    queue.add(new Player(newY, newX, poll.time + 1));
                }
            }
        }
    }

    private static class Player implements Comparable<Player> {
        int index;
        int x;
        int y;
        char name;
        int dps;
        int time;

        public Player(int index, char name, int dps) {
            this.index = index;
            this.name = name;
            this.dps = dps;
            this.time = -1;
        }

        public Player(int y, int x, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public int compareTo(Player o) {
            return Integer.compare(time, o.time);
        }
    }
}
