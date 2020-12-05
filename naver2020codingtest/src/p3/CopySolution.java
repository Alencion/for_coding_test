package p3;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class CopySolution {
    private static int solution(int n, int[][] edges) {
        int[] parentOf = new int[n];
        parentOf[0] = -1;
        int[] children = new int[n];

        for (int i = 0; i < edges.length; i++) {
            int A = edges[i][0];
            int B = edges[i][1];
            parentOf[B] = A;
        }

        for (int i = 1; i < n; i++) {
            int p = parentOf[i];
            while (p != 0) {
                children[p] += 1;
                p = parentOf[p];
            }
        }

        int answer = n;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        while (!queue.isEmpty()) {
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < queue.size(); i++) {
                list.add(queue.poll());
            }

            int max = -1;
            int k = -1;

            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < n; j++) {
                    if (parentOf[j] == list.get(i)) {
                        if (children[j] + 1 > max) {
                            max = children[j] + 1;
                            k = j;
                        }
                    }
                }
            }

            answer -= max;

            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < n; j++) {
                    if (parentOf[j] == list.get(i) && j != k) {
                        queue.add(j);
                    }
                }
            }
        }

        return answer;
    }

    @Test
    public void test() {
        int n = 10;
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {2, 4}, {2, 5}, {2, 6}, {3, 7}, {3, 8}, {3, 9}};

        Assert.assertEquals(2, solution(n, edges));
    }

    @Test
    public void test2() {
        int n = 19;
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}, {1, 5}, {2, 6}, {3, 7}, {3, 8}, {3, 9}, {4, 10}, {4, 11}, {5, 12}, {5, 13}, {6, 14}, {6, 15}, {6, 16}, {8, 17}, {8, 18}};

        Assert.assertEquals(7, solution(n, edges));
    }
}