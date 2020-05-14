package heap.doublePriorityQueue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];

        Queue<Integer> minHeap = new PriorityQueue<>();
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for (String operation : operations)
            parsingOperation(operation).execute(minHeap, maxHeap);

        if (minHeap.isEmpty() || maxHeap.isEmpty()) return new int[]{0, 0};
        answer[0] = maxHeap.poll();
        answer[1] = minHeap.poll();

        return answer;
    }

    private Operation parsingOperation(String operation) {
        String[] parsingOperation = operation.split(" ");
        if (parsingOperation[0].equals("I"))
            return new Operation(Method.INSERT, Integer.parseInt(parsingOperation[1]));
        return new Operation(Method.DELETE, Integer.parseInt(parsingOperation[1]));
    }

    public static void main(String[] args) {
        String[] operations = {"I 16", "D 1"};
        new Solution().solution(operations);

        operations = new String[]{"I 7", "I 5", "I -5", "D -1"};
        new Solution().solution(operations);
    }
}

class Operation {
    Method method;
    int number;

    public Operation(Method method, int number) {
        this.method = method;
        this.number = number;
    }

    public void execute(Queue<Integer> minHeap, Queue<Integer> maxHeap) {
        if (method == Method.INSERT) {
            minHeap.offer(number);
            maxHeap.offer(number);
            return;
        }
        if (method == Method.DELETE) {
            Integer findNumber;
            if (number == 1) findNumber = maxHeap.peek();
            else findNumber = minHeap.peek();
            maxHeap.remove(findNumber);
            minHeap.remove(findNumber);
        }
    }
}

enum Method {
    INSERT,
    DELETE;
}