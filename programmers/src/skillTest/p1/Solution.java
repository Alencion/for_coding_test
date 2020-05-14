package skillTest.p1;

import java.util.Arrays;
import java.util.Stack;

class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        int max = 0;

        Arrays.sort(d);
        Stack<Integer> prevIndex = new Stack<>();
        for (int i = 0; i < d.length; i++) {

            if (budget == max + d[i]){
                answer++;
                break;
            }
            else if (budget > max + d[i]){
                max += d[i];
                prevIndex.push(i);
                answer++;
            }
            else if (budget < max + d[i] ){
                max -= d[prevIndex.pop()];
                i--;
                answer--;
            }
        }


        return answer;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] d = {1,3,2,5,4};
        int budget = 9;
        int result = solution.solution(d, budget);

        System.out.println(result);


    }
}