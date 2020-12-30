package class4.p5639;

import java.io.*;

public class Main {
    static Node root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputLine = "";

        while ((inputLine = br.readLine()) != null && inputLine.length() != 0) {
            Node node = new Node(Integer.parseInt(inputLine));

            if (root == null) root = node;
            else makeTree(root, node);
        }

        postOrder(root, bw);
        bw.flush();
    }

    private static void postOrder(Node root, BufferedWriter bw) throws IOException {

        if (root.left != null) postOrder(root.left, bw);
        if (root.right != null) postOrder(root.right, bw);
        bw.write(root.value + "\n");
    }

    private static void makeTree(Node current, Node node) {
        if (current.value > node.value) {
            if (current.left == null) {
                current.left = node;
            } else {
                makeTree(current.left, node);
            }
        } else {
            if (current.right == null) {
                current.right = node;
            } else {
                makeTree(current.right, node);
            }
        }
    }


    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }
}
