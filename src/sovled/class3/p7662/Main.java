package sovled.class3.p7662;

import java.io.*;
import java.util.TreeMap;

/**
 * 이중 우선순위 큐
 * 2020-12-23 minHeap/maxHeap X, 트리맵
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());

            String[] split;

            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            for (int j = 0; j < k; j++) {
                split = br.readLine().split(" ");

                char op = split[0].charAt(0);
                int number = Integer.parseInt(split[1]);

                if (op == 'I') {
                    treeMap.put(number, treeMap.getOrDefault(number, 0) + 1);
                } else if (op == 'D') {
                    if (treeMap.isEmpty()) continue;
                    if (number == -1) {
                        int minKey = treeMap.firstKey();
                        int minCount = treeMap.get(minKey);
                        if (minCount == 1) treeMap.remove(minKey);
                        else treeMap.put(minKey, treeMap.get(minKey) - 1);
                    } else if (number == 1) {
                        int maxKey = treeMap.lastKey();
                        int maxCount = treeMap.get(maxKey);
                        if (maxCount == 1) treeMap.remove(maxKey);
                        else treeMap.put(maxKey, treeMap.get(maxKey) - 1);
                    }
                }
            }
            if (treeMap.isEmpty()) bw.write("EMPTY\n");
            else bw.write(treeMap.lastKey() + " " + treeMap.firstKey() + "\n");
        }
        bw.flush();
    }
}
