package tonny9402.data_structure.p1158;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 요세푸스 문제
 * 2021-09-28
 */
public class Main {
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }

        List<Integer> result = new ArrayList<>();

        while(!queue.isEmpty()){
            for (int i = 0; i < k - 1; i++) {
                queue.add(queue.poll());
            }
            result.add(queue.poll());
        }

        System.out.println("<" + result.stream().map(Object::toString).collect(Collectors.joining(", ")) + ">");
    }
}
