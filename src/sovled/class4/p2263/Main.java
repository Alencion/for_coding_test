package sovled.class4.p2263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 트리 순회
 * 2020-12-29
 */
public class Main {
    static int[] inOrder;
    static int[] postOrder;
    static int[] inOrderIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        inOrder = new int[n];
        postOrder = new int[n];

        String[] split = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            inOrder[i] = Integer.parseInt(split[i]) - 1;
        }

        split = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            postOrder[i] = Integer.parseInt(split[i]) - 1;
        }

        inOrderIndex = new int[n];
        for (int i = 0; i < n; i++) {
            inOrderIndex[inOrder[i]] = i;
        }

        StringBuilder sb = new StringBuilder();

        getPreOrder(0, n - 1, 0, n - 1, sb);
        System.out.println(sb.toString());
    }

    private static void getPreOrder(int inStart, int inEnd, int postStart, int postEnd, StringBuilder sb) {
        if (inStart > inEnd || postStart > postEnd) return;

        int root = postOrder[postEnd];
        sb.append((root + 1) + " ");

        int rootIndex = inOrderIndex[root];

        int left = rootIndex - inStart;

        getPreOrder(inStart, rootIndex - 1, postStart, postStart + left - 1, sb);
        getPreOrder(rootIndex + 1, inEnd, postStart + left, postEnd - 1, sb);
    }
}
