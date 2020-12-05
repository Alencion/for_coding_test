package kakao2019BlindRecruitment.wayFindnigGame.anotherSolution;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    public int[][] solution(int[][] nodeInfos) {
        int[][] answer = new int[2][nodeInfos.length];

        List<Node> nodes = IntStream.range(0, nodeInfos.length)
                .mapToObj(index -> new Node(index, nodeInfos[index][0], nodeInfos[index][1]))
                .sorted((o1, o2) -> o2.y - o1.y)
                .collect(Collectors.toList());

        Tree tree = new Tree(nodes.get(0));
        for (int i = 1; i < nodes.size(); i++) {
            tree.add(nodes.get(i));
        }
        answer[0] = tree.preOrder();
        answer[1] = tree.postOrder();
        return answer;
    }

    private static class Tree {
        Node root;

        public Tree(Node root) {
            this.root = root;
        }

        public void add(Node node) {
            Node head = root;

            while (true) {
                if (head.x < node.x) {
                    if (head.right == null) {
                        head.right = node;
                        break;
                    } else {
                        head = head.right;
                    }
                } else {
                    if (head.left == null) {
                        head.left = node;
                        break;
                    } else {
                        head = head.left;
                    }
                }
            }
        }

        public int[] preOrder() {
            List<Integer> result = new ArrayList<>();
            Stack<Node> stack = new Stack<>();

            stack.push(root);
            while (!stack.isEmpty()) {
                Node pop = stack.pop();
                result.add(pop.index + 1);
                if (pop.right != null) stack.push(pop.right);
                if (pop.left != null) stack.push(pop.left);
            }
            return result.stream().mapToInt(i -> i).toArray();
        }

        public int[] postOrder() {
            List<Integer> result = new ArrayList<>();
            postOrder(root, result);
            return result.stream().mapToInt(i -> i).toArray();
        }

        private void postOrder(Node node, List<Integer> result) {
            if (node.left != null) postOrder(node.left, result);
            if (node.right != null) postOrder(node.right, result);
            result.add(node.index + 1);
        }
    }

    private static class Node {
        int index;
        int x;
        int y;
        Node left;
        Node right;

        public Node(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }
    }

    @Test
    public void test() {
        int[][] nodeInfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};

        int[][] results = new Solution().solution(nodeInfo);

        Assert.assertEquals(Arrays.toString(new int[]{7, 4, 6, 9, 1, 8, 5, 2, 3}), Arrays.toString(results[0]));
        Assert.assertEquals(Arrays.toString(new int[]{9, 6, 5, 8, 1, 4, 3, 2, 7}), Arrays.toString(results[1]));
    }
}
