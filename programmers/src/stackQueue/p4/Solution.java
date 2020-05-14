package stackQueue.p4;

import java.util.*;

public class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 1;
        Queue<Integer> priority = new PriorityQueue<>(Collections.reverseOrder());

        for(int task : priorities)
            priority.add(task);

        System.out.println(priority);
        while(!priority.isEmpty()){
            for(int i=0; i<priorities.length; i++){
                if(priorities[i] == priority.peek()) {
                    if(i == location)
                        return answer;

                    priority.poll();
                    answer++;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] priorities = {9, 1, 9};
        int location = 1;
        new Solution().solution(priorities, location);
    }
}

class Document {
    int index;
    int priority;

    public Document(int index, int priority) {
        this.index = index;
        this.priority = priority;
    }

}