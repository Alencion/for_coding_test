package p3;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;s

public class Solution {
    int min = Integer.MAX_VALUE;
    Tree tree;

    public int solution(int n, int[][] edges) {
        int answer = 0;
        tree = Tree.makeTree(edges);

        boolean[] disConnected = new boolean[n];

        List<Integer> list = new ArrayList<>();
        list.addAll(tree.root.child.keySet());

        dfs(list, disConnected);

        answer = min;
        return answer;
    }

    private void dfs(List<Integer> list, boolean[] disConnected) {
        if (list.isEmpty()) {
            int value = count(disConnected);
            min = Math.min(value, min);
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            int parentIndex = list.get(i);

            disConnected[parentIndex] = true;
            List<Integer> newList = makeList(list, disConnected);
            dfs(newList, disConnected);
            disConnected[parentIndex] = false;
        }
    }

    private List<Integer> makeList(List<Integer> list, boolean[] disConnected) {
        List<Integer> newList = new ArrayList<>();

        for (int childIndex : list) {
            if (!disConnected[childIndex])
                newList.addAll(Tree.nodes.get(childIndex).child.keySet());
        }

        return newList;
    }

    private int count(boolean[] disconnected) {
        int count = 0;
        Queue<Tree.Node> queue = new LinkedList<>();
        queue.add(tree.root);

        while (!queue.isEmpty()) {
            Tree.Node poll = queue.poll();
            if (disconnected[poll.value]) continue;
            count++;
            queue.addAll(poll.child.values());
        }
        return count;
    }


    private static class Tree {
        Node root;
        static Map<Integer, Node> nodes = new HashMap<>();

        public Tree(Node root) {
            this.root = root;
        }

        public static Tree makeTree(int[][] edges) {
            Node root = new Node(0);

            nodes.put(0, root);
            for (int[] edge : edges) {
                Node parent = nodes.get(edge[0]);
                Node child = new Node(edge[1]);
                parent.addChild(child);
                nodes.put(child.value, child);
            }

            return new Tree(root);
        }

        private static class Node {
            int value;
            Map<Integer, Node> child;

            public Node(int value) {
                this.value = value;
                child = new HashMap<>();
            }

            public void addChild(Node child) {
                this.child.put(child.value, child);
            }
        }
    }

    @Test
    public void test() {
        int n = 10;
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {2, 4}, {2, 5}, {2, 6}, {3, 7}, {3, 8}, {3, 9}};

        Assert.assertEquals(2, new Solution().solution(n, edges));
    }

    @Test
    public void test2() {
        int n = 19;
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}, {1, 5}, {2, 6}, {3, 7}, {3, 8}, {3, 9}, {4, 10}, {4, 11}, {5, 12}, {5, 13}, {6, 14}, {6, 15}, {6, 16}, {8, 17}, {8, 18}};

        Assert.assertEquals(7, new Solution().solution(n, edges));
    }
}
