package sovled.class2.p2108;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        int[] answer = solution(n, array);
        for (int i : answer) {
            System.out.println(i);
        }
    }

    private static int[] solution(int n, int[] array) {
        int[] answer = new int[4];

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += array[i];
        }
        answer[0] = (int)Math.round((double) sum/n);
        answer[1] = Arrays.stream(array).sorted().toArray()[n/2];

        Map<Integer, Integer> map = new HashMap<>();
        for (int number : array) {
            map.put(number, map.getOrDefault(number, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> collect = map.entrySet().stream().sorted((entry1, entry2) -> {
            if (entry1.getValue().equals(entry2.getValue())) return entry1.getKey().compareTo(entry2.getKey());
            return entry2.getValue().compareTo(entry1.getValue());
        }).collect(Collectors.toList());
        if (collect.size() == 1 || !collect.get(0).getValue().equals(collect.get(1).getValue())){
            answer[2] = collect.get(0).getKey();
        }
        else {
            answer[2] = collect.get(1).getKey();
        }
        answer[3] = Arrays.stream(array).max().orElse(0) - Arrays.stream(array).min().orElse(0);

        return answer;
    }

    @Test
    public void test(){
        int n = 3;
        int sum = -40;
        System.out.println(Math.round((double) sum / n));
    }
}
