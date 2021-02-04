package sovled.class2.p1966;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCaseNumber = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCaseNumber; i++) {
            String[] split = br.readLine().split(" ");
            int n = Integer.parseInt(split[0]);
            int position = Integer.parseInt(split[1]);

            split = br.readLine().split(" ");
            int[] priorityArray = new int[n];

            for (int j = 0; j < n; j++) {
                priorityArray[j] = Integer.parseInt(split[j]);
            }

            int answer = solution(n, position, priorityArray);
            System.out.println(answer);
        }
    }

    private static int solution(int n, int position, int[] priorityArray) {
        Queue<Integer> array = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < n; i++) {
            array.add(i);
            queue.add(priorityArray[i]);
            pq.add(priorityArray[i]);
        }

        int count = 0;

        while(!pq.isEmpty()){
            int max = pq.poll();

            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int current = queue.poll();
                int arrayCurrent = array.poll();

                if (current == max){
                    count++;
                    if (arrayCurrent == position) return count;
                    break;
                }
                queue.offer(current);
                array.offer(arrayCurrent);
            }
        }


        return 0;
    }
}
