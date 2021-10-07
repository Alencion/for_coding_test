package tonny9402.data_structure.p2800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 괄호제거
 * 2021-10-07
 * 조합 + 스택
 */
public class Main {
    static Set<String> result = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputString = br.readLine();

        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            if(c == '('){
                stack.push(i);
            } else if(c == ')'){
                int start = stack.pop();
                map.put(start, i);
            }
        }

        boolean[] isDelete = new boolean[inputString.length()];
        List<Integer> list = new ArrayList<>(map.keySet());
        for (int i = 0; i < list.size(); i++) {
            int start = list.get(i);
            isDelete[start] = true;
            isDelete[map.get(start)] =true;
            dfs(i + 1, inputString, isDelete, map, list);
            isDelete[start] = false;
            isDelete[map.get(start)] =false;
        }

        result.stream()
                .sorted(String::compareTo)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    static void dfs(int index, String inputString, boolean[] isDelete, Map<Integer,Integer> map, List<Integer> list) {
        result.add(makeResult(inputString, isDelete));

        for (int i = index; i < list.size(); i++) {
            int start = list.get(i);
            isDelete[start] = true;
            isDelete[map.get(start)] =true;
            dfs(i + 1, inputString, isDelete, map, list);
            isDelete[start] = false;
            isDelete[map.get(start)] =false;
        }
    }

    static String makeResult(String inputString, boolean[] isDelete){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inputString.length(); i++) {
            if(!isDelete[i]){
                sb.append(inputString.charAt(i));
            }
        }
        return sb.toString();
    }
}
