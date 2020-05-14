package sort.kthNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = {};

        List<Integer> results = new ArrayList<>();
        for(int[] command : commands){
            int[] subArray = Arrays.copyOfRange(array, command[0] -1, command[1]);
            Arrays.sort(subArray);
            results.add(subArray[command[2] - 1]);
        }

        answer = results.stream().mapToInt(i->i).toArray();
        return answer;
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        new Solution().solution(array, commands);
    }
}
