package sovled.class4.p2263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 트리 순회
 * 2021-01-02
 */
public class ReMain {
    static int[] inOrder;
    static int[] postOrder;
    static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        inOrder = new int[n];
        postOrder = new int[n];

        String[] split = br.readLine().split(" ");
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            inOrder[i] = Integer.parseInt(split[i]) - 1;
            map.put(inOrder[i], i);
        }

        split = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            postOrder[i] = Integer.parseInt(split[i]) - 1;
        }
        StringBuilder sb = new StringBuilder();
        dfs(0, n - 1, 0, n - 1, sb);
        System.out.println(sb.toString());
    }

    private static void dfs(int inStart, int inEnd, int postStart, int postEnd, StringBuilder sb) {
        if (inStart == inEnd || postStart == postEnd) return;
        int root = postOrder[postEnd];
        sb.append(root + " ");

        int rootIndex = map.get(root);
        int left = rootIndex - inStart;

        dfs(inStart, rootIndex - 1, postStart, postStart + left - 1, sb);
        dfs(rootIndex + 1, inEnd, postStart + left - 1,    postEnd - 1, sb);
    }
}
