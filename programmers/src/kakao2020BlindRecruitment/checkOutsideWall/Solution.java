package kakao2020BlindRecruitment.checkOutsideWall;

class Solution {
    boolean finish;
    int[] choice;
    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;

        int[][] rotateWeak = setWeak(n, weak);
        for (int i = 1; i <= rotateWeak.length; i++) {
            choice = new int[i];
            permutation(i, 0, dist, new boolean[dist.length], rotateWeak);
            if (finish) return i;
        }
        return answer;
    }

    private boolean isFinish(boolean[] visit) {
        for (boolean bool : visit)
            if (!bool) return false;
        return true;
    }

    private void permutation(int number, int depth, int[] dist, boolean[] visit, int[][] rotateWeak) {
        if (finish) return;
        if (depth == number) {
            check(number, rotateWeak);
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (!visit[i]) {
                choice[depth] = dist[i];
                visit[i] = true;
                permutation(number, depth + 1, dist, visit, rotateWeak);
                visit[i] = false;
            }
        }
    }

    public void check(int number, int[][] rotateWeak) {
        for (int[] weak : rotateWeak) {
            int idx = 0, start = 0;
            boolean[] visit = new boolean[weak.length];

            while (idx != number) {
                int i = start;
                int value = choice[idx++];

                for (int j = start; j < weak.length; j++) {
                    if (!(weak[i] <= weak[j] && weak[j] <= weak[i] + value)) break;
                    visit[j] = true;
                    start++;
                }
            }

            if (isFinish(visit)) {
                finish = true;
                return;
            }
        }
    }

    public int[][] setWeak(int n, int[] weak) {
        int[][] result = new int[weak.length][weak.length];

        for (int i = 0; i < weak.length; i++) {
            result[i] = rotate(n, weak, i);
        }
        return result;
    }

    private int[] rotate(int n, int[] weak, int index) {
        int[] result = new int[weak.length];

        for (int i = 0; i < weak.length; i++) {
            if (i + index < weak.length) result[i] = weak[i];
            else result[i] = weak[i + index - weak.length] + n;
        }
        return result;
    }


    public static void main(String[] args) {
        int n = 12;
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};

        int answer = new Solution().solution(n, weak, dist);
        System.out.println(answer);
    }
}