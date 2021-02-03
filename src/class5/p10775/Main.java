package class5.p10775;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int gatewayCount = Integer.parseInt(br.readLine());
        int plainCount = Integer.parseInt(br.readLine());

        parent = new int[gatewayCount + 1];
        for (int i = 1; i <= gatewayCount; i++) {
            parent[i] = i;
        }

        int answer = 0;
        for (int i = 0; i < plainCount; i++) {
            int g = Integer.parseInt(br.readLine());
            int emptyGate = getParents(g);

            if (emptyGate == 0) {
                break;
            }

            answer++;
            union(emptyGate, emptyGate - 1);
        }

        System.out.println(answer);
    }

    public static int getParents(int x) {
        if (x == parent[x]) return x;
        return parent[x] = getParents(parent[x]);
    }

    public static void union(int x, int y) {
        x = getParents(x);
        y = getParents(y);

        if (x != y) {
            parent[x] = y;
        }
    }

}
