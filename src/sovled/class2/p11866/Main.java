package sovled.class2.p11866;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 2020-12-12
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);

        int[] answer = solution(n, k);
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        for (int i = 0; i < n-1; i++) {
            sb.append(answer[i]);
            sb.append(", ");
        }
        sb.append(answer[n-1]);
        sb.append(">");
        System.out.println(sb.toString());
    }

    private static int[] solution(int n, int k) {
        int[] answer = new int[n];

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i+1);
        }

        int index = 0;
        int i = 0;
        while(!list.isEmpty()){
            int size = list.size();

            index = (index + k - 1) % size;
            answer[i++] = list.remove(index);
        }

        return answer;
    }
}
