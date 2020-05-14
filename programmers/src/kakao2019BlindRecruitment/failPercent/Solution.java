package kakao2019BlindRecruitment.failPercent;

import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public int[] solution(int N, int[] stages) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int s : stages)
            map.put(s, map.getOrDefault(s, 0) + 1);

        int total = stages.length;
        Node[] nodes = new Node[N];

        for (int i = 1; i <= N; i++) {
            if (!map.containsKey(i)) {
                nodes[i - 1] = new Node(i, 0);
                continue;
            }
            int count = map.get(i);
            nodes[i - 1] = new Node(i, (double) count / total);
            total -= count;
        }

        Arrays.sort(nodes);
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = nodes[i].num;
        }
        return answer;
    }
}

class Node implements Comparable<Node> {
    int num;
    double rate;

    Node(int num, double rate) {
        this.num = num;
        this.rate = rate;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(other.rate, this.rate);
    }
}