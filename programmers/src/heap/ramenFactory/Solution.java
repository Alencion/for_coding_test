package heap.ramenFactory;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    public int solution(int stock, int[] dates, int[] supplies, int k) {
        int answer = 0;

        Queue<WheatSupply> heap = new PriorityQueue<>();

        for (int i = 0; i < dates.length; i++)
            heap.offer(new WheatSupply(dates[i], supplies[i]));

        Queue<WheatSupply> queue = new LinkedList<>();
        while (stock < k) {
            WheatSupply supply = heap.poll();
            if (stock >= supply.date) {
                answer++;
                stock += supply.supplies;

                while (!queue.isEmpty())
                    heap.offer(queue.poll());
            } else {
                queue.add(supply);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int stock = 4;
        int[] dates = {2, 4, 10, 15};
        int[] supplies = {15, 15, 5, 3};
        int k = 100;

        new Solution().solution(stock, dates, supplies, k);
    }
}

class WheatSupply implements Comparable<WheatSupply> {
    int date;
    int supplies;

    public WheatSupply(int date, int supplies) {
        this.date = date;
        this.supplies = supplies;
    }

    @Override
    public int compareTo(WheatSupply o) {
        if (o.supplies == this.supplies) return Integer.compare(o.date, this.date);
        return Integer.compare(o.supplies, this.supplies);

    }
}