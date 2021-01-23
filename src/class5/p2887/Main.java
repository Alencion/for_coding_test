package class5.p2887;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 행성 터널
 * 2021=01-21
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int planetCount = Integer.parseInt(br.readLine());

        List<Planet> planets = new ArrayList<>();
        for (int i = 0; i < planetCount; i++) {
            String[] split = br.readLine().split(" ");
            planets.add(new Planet(i, Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])));
        }

        List<Tunnel> tunnels = new ArrayList<>();

        planets.sort(Comparator.comparingInt(p -> p.x));
        for (int i = 0; i < planets.size() - 1; i++) {
            Planet from = planets.get(i);
            Planet to = planets.get(i + 1);
            tunnels.add(new Tunnel(from.index, to.index, Math.abs(from.x - to.x)));
        }

        planets.sort(Comparator.comparingInt(p -> p.y));
        for (int i = 0; i < planets.size() - 1; i++) {
            Planet from = planets.get(i);
            Planet to = planets.get(i + 1);
            tunnels.add(new Tunnel(from.index, to.index, Math.abs(from.y - to.y)));
        }

        planets.sort(Comparator.comparingInt(p -> p.z));
        for (int i = 0; i < planets.size() - 1; i++) {
            Planet from = planets.get(i);
            Planet to = planets.get(i + 1);
            tunnels.add(new Tunnel(from.index, to.index, Math.abs(from.z - to.z)));
        }

        Collections.sort(tunnels);

        int[] cycleTable = new int[planetCount];
        for (int i = 0; i < planetCount; i++) {
            cycleTable[i] = i;
        }

        int answer = 0;
        for (Tunnel tunnel : tunnels) {
            int parentFrom = getParent(cycleTable, tunnel.from);
            int parentTo = getParent(cycleTable, tunnel.to);

            if (parentFrom != parentTo) {
                answer += tunnel.cost;
                union(cycleTable, tunnel.from, tunnel.to);
            }
        }

        System.out.println(answer);
    }

    private static void union(int[] cycleTable, int from, int to) {
        int parentFrom = getParent(cycleTable, from);
        int parentTo = getParent(cycleTable, to);

        if (parentFrom > parentTo) cycleTable[parentFrom] = parentTo;
        else cycleTable[parentTo] = parentFrom;
    }

    private static int getParent(int[] cycleTable, int child) {
        if (cycleTable[child] == child) return child;
        return getParent(cycleTable, cycleTable[child]);
    }

    static class Planet {
        int index;
        int x;
        int y;
        int z;

        public Planet(int index, int x, int y, int z) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Tunnel implements Comparable<Tunnel> {
        int from;
        int to;
        int cost;

        public Tunnel(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Tunnel o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
