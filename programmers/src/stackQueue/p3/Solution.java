package stackQueue.p3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        Queue<Service> serviceQueue = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++)
            serviceQueue.add(new Service(progresses[i], speeds[i]));

        List<Integer> results = new ArrayList<>();
        Service current = serviceQueue.poll(), next;
        int count = 1;
        while (!serviceQueue.isEmpty()) {
            next = serviceQueue.poll();

            if (current.releaseDay >= next.releaseDay) {
                count++;
                continue;
            }

            current = next;
            results.add(count);
            count = 1;
        }
        results.add(count);

        answer = results.stream().mapToInt(i -> i).toArray();
        return answer;
    }

    public static void main(String[] args) {
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};
        new Solution().solution(progresses, speeds);
    }
}

class Service {
    int progress;
    int speed;
    int releaseDay;

    public Service(int progress, int speed) {
        this.progress = progress;
        this.speed = speed;
        this.releaseDay = (99 - progress) / speed + 1;
    }
}