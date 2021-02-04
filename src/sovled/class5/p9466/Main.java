package sovled.class5.p9466;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 텀 프로젝트
 * 2021-01-27
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            int n = Integer.parseInt(br.readLine());

            String[] split = br.readLine().split(" ");
            int[] nums = new int[n];
            for (int j = 0; j < n; j++) {
                nums[j] = Integer.parseInt(split[j]) - 1;
            }

            int answer = 0;

            boolean[] visited = new boolean[n];
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                if (visited[j]) continue;
                visited[j] = true;

                int index = j;
                queue.add(index);
                while(!visited[nums[index]]){
                    index = nums[index];
                    visited[index] = true;
                    queue.add(index);
                }
                index = nums[index];

                while(!queue.isEmpty() && index != queue.peek()){
                    queue.poll();
                    answer++;
                }

                queue.clear();
            }

            sb.append(answer).append(System.lineSeparator());
        }
        System.out.println(sb.toString());
    }
}
