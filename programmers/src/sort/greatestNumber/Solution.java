package sort.greatestNumber;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution {
    public String solution(int[] numbers) {
        if (Arrays.stream(numbers).sum() == 0)
            return "0";

        return Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .sorted((number1, number2) ->
                        number2.concat(number1).compareTo(number1.concat(number2)))
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        int[] numbers = {6, 10, 2};
        numbers = new int[]{3, 30, 34, 5, 9};
        System.out.println(new Solution().solution(numbers));
    }
}
