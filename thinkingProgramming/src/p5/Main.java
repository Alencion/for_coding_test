package p5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static String solution(int index) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Joengjun\\Desktop\\Project\\for_coding_test\\thinkingProgramming\\src\\p5\\data\\" + index + ".in"));
        String[] split = br.readLine().split(" ");
        int p = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            split = br.readLine().split(" ");
            players.add(new Player(Integer.parseInt(split[0]), split[1]));
        }

        List<Room> rooms = solution(m, players);
        StringBuilder sb = new StringBuilder();
        for (Room room : rooms) {
            sb.append(room);
        }

        return sb.toString();
    }

    private static List<Room> solution(int m, List<Player> players) {
        List<Room> rooms = new ArrayList<>();

        for (Player player : players) {
            int index = checkRoom(rooms, player);
            if (rooms.isEmpty() || index == -1) {
                rooms.add(new Room(player, m));
                continue;
            }
            if (rooms.get(index).canJoin(player))
                rooms.get(index).join(player);
        }

        return rooms;
    }

    private static int checkRoom(List<Room> rooms, Player player) {
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            if (room.canJoin(player))
                return i;
        }

        return -1;
    }

    private static class Player implements Comparable<Player> {
        int level;
        String name;

        public Player(int level, String name) {
            this.level = level;
            this.name = name;
        }

        @Override
        public int compareTo(Player o) {
            return name.compareTo(o.name);
        }
    }

    private static class Room {
        Player leader;
        List<Player> players;
        int m;
        int size;
        boolean isStarted;

        public Room(Player leader, int m) {
            this.leader = leader;
            this.players = new ArrayList<>();
            this.players.add(leader);
            this.m = m;
            this.size = 1;
            if(m == size) isStarted = true;
        }

        public boolean canJoin(Player player) {
            return !isStarted && leader.level - 10 <= player.level && leader.level + 10 >= player.level;
        }

        public void join(Player player) {
            players.add(player);
            size++;
            if (size == m) isStarted = true;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (isStarted) sb.append("Started!\n");
            else sb.append("Waiting!\n");
            Collections.sort(players);
            for (Player player : players) {
                sb.append(player.level).append(" ").append(player.name).append("\n");
            }
            return sb.toString();
        }
    }

    @Test
    public void test() throws IOException {
        for (int i = 0; i < 300; i++) {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Joengjun\\Desktop\\Project\\for_coding_test\\thinkingProgramming\\src\\p5\\data\\" + i + ".out"));
            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str).append("\n");
            }

            String solution = solution(i);
            System.out.println(i);
            Assertions.assertEquals(sb.toString(), solution);
        }
    }
}
