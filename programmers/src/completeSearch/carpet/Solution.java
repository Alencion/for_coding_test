package completeSearch.carpet;

public class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int maxIndex = brown / 2;

        for (int x = 0; x < maxIndex; x++) {
            for (int y = 0; y < maxIndex; y++) {
                if (checkCount(brown, yellow, x, y) && x >= y) {
                    answer[0] = x;
                    answer[1] = y;
                }
            }
        }

        return answer;
    }

    private boolean checkCount(int brown, int yellow, int x, int y) {
        if (brown + yellow != x*y) return false;
        return (x - 2) * (y - 2) == yellow;
    }


    public static void main(String[] args) {

    }
}
