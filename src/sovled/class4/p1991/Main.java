package sovled.class4.p1991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Map<Character, Node> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");

            char parent = split[0].charAt(0);
            char left = split[1].charAt(0);
            char right = split[2].charAt(0);

            if (!map.containsKey(parent)) {
                map.put(parent, new Node(parent));
            }
            Node parentNode = map.get(parent);

            if (left != '.'){
                map.put(left, new Node(left));
                parentNode.left = map.get(left);
            }

            if (right != '.'){
                map.put(right, new Node(right));
                parentNode.right = map.get(right);
            }
        }

        preOrder(map.get('A'));
        System.out.println();
        inOrder(map.get('A'));
        System.out.println();
        postOrder(map.get('A'));
    }

    private static void postOrder(Node a) {
        if (a == null) return;
        postOrder(a.left);
        postOrder(a.right);
        System.out.print(a.value);
    }

    private static void inOrder(Node a) {
        if (a == null) return;
        inOrder(a.left);
        System.out.print(a.value);
        inOrder(a.right);
    }

    private static void preOrder(Node a) {
        if (a == null) return;
        System.out.print(a.value);
        preOrder(a.left);
        preOrder(a.right);
    }

    static class Node{
        char value;
        Node left;
        Node right;

        public Node(char value) {
            this.value = value;
        }
    }
}
