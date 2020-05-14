package heap.DiscController;

import java.util.*;

public class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;

        Queue<Job> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.arriveTime == o2.arriveTime)
                return Integer.compare(o1.needTime, o2.needTime);
            return Integer.compare(o1.arriveTime, o2.arriveTime);
        });

        for (int[] job : jobs)
            queue.offer(new Job(job[0], job[1]));

        Queue<Job> heap = new PriorityQueue<>();
        int time = 0;

        while (!(queue.isEmpty() && heap.isEmpty())) {
            Job current = heap.isEmpty() ? queue.poll() : heap.poll();
            if (time < current.arriveTime)
                time = current.arriveTime + current.needTime;
            else
                time += current.needTime;

            answer += time - current.arriveTime;
            while (!queue.isEmpty() && queue.peek().arriveTime < time)
                heap.offer(queue.poll());
        }

        answer /= jobs.length;
        return answer;
    }

    public static void main(String[] args) {
        int[][] jobs = {{0, 9}, {0, 4}, {0, 5}, {0, 7}, {0, 3}};
        new Solution().solution(jobs);
    }
}


class Job implements Comparable<Job> {
    int arriveTime;
    int needTime;

    public Job(int arriveTime, int needTime) {
        this.arriveTime = arriveTime;
        this.needTime = needTime;
    }

    @Override
    public int compareTo(Job o) {
        return Integer.compare(this.needTime, o.needTime);
    }
}