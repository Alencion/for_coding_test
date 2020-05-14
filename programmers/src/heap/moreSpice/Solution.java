package heap.moreSpice;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        Queue<Integer> heap = new PriorityQueue<>();

        for(int scovilleScore: scoville)
            heap.offer(scovilleScore);

        if (heap.isEmpty())
            return -1;
        int mixCount = 0;
        while(heap.peek() < K){
            if (heap.size() < 2) return -1;

            int food1 = heap.poll();
            int food2 = heap.poll();

            int mixScore = food1 + food2 * 2;
            mixCount++;
            heap.offer(mixScore);
        }

        answer = mixCount;
        return answer;
    }

    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int k = 7;
        new Solution().solution(scoville, k);
    }
}
