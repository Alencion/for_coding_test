package kakao2019BlindRecruitment.wayFindnigGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;
        int[][] answer = new int[2][n];
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] node = nodeinfo[i];
            nodes.add(new Node(node[0], node[1], i + 1));
        }

        nodes.sort(Node::compareTo);

        Tree tree = new Tree(nodes.get(0));
        for (int i = 1; i < n; i++) {
            tree.insert(nodes.get(i));
        }

        answer[0] = preorder(tree.root);
        answer[1] = postorder(tree.root);

        return answer;
    }

    private int[] preorder(Node root) {
        Stack<Node> stack = new Stack<>();
        List<Integer> preorder = new ArrayList<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            preorder.add(current.index);

            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }

        return preorder.stream().mapToInt(i -> i).toArray();
    }

    private int[] postorder(Node root) {
        Stack<Node> stack = new Stack<>();
        List<Integer> postorder = new ArrayList<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            Node top = stack.peek();
            if (top.isLeaf()) {
                Node node = stack.pop();
                postorder.add(node.index);
            } else {
                if (top.right != null) {
                    stack.push(top.right);
                    top.right = null;
                }

                if (top.left != null) {
                    stack.push(top.left);
                    top.left = null;
                }
            }

        }

        return postorder.stream().mapToInt(i -> i).toArray();
    }


    public static void main(String[] args) {
        int[][] nodeinfo = {{5, 3},
                {11, 5},
                {13, 3},
                {3, 5},
                {6, 1},
                {1, 3},
                {8, 6},
                {7, 2},
                {2, 2}};
        new Solution().solution(nodeinfo);
    }
}

class Tree {
    Node root;

    Tree(Node root) {
        this.root = root;
    }

    public void insert(Node node) {
        Node thisNode = root;
        while (true) {
            if (node.x < thisNode.x) {
                if (thisNode.left != null) thisNode = thisNode.left;
                else {
                    thisNode.left = node;
                    break;
                }
            } else {
                if (thisNode.right != null) thisNode = thisNode.right;
                else {
                    thisNode.right = node;
                    break;
                }
            }
        }
    }
}

class Node implements Comparable<Node> {
    int x;
    int y;
    int index;
    Node left, right;

    public Node(int x, int y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }


    @Override
    public int compareTo(Node node) {
        if (this.y == node.y) return Integer.compare(this.x, node.x);
        return Integer.compare(node.y, this.y);
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}