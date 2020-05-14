package kakao2020BlindRecruitment.lockAndKey;

class Solution {

    public boolean solution(int[][] key, int[][] lock) {

        for (int i = 0; i < 4; i++) {
            if (match(key, lock)) return true;
            key = spin(key);
        }

        return false;
    }

    private int[][] spin(int[][] arr) {
        int[][] temp = new int[arr.length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                temp[i][j] = arr[arr.length - 1 - j][i];
            }
        }

        return temp;
    }

    private int[][] move(int[][] key, int length, int row, int col) {
        int[][] temp = new int[length][length];

        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                if (i + row >= 0
                        && i + row < length
                        && j + col >= 0
                        && j + col < length) {
                    temp[i + row][j + col] = key[i][j];
                }
            }
        }

        return temp;
    }

    private boolean isUnlock(int[][] key, int[][] lock) {
        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                if ((key[i][j] + lock[i][j]) != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean match(int[][] key, int[][] lock) {
        for (int i = (1 - key.length); i < lock.length; i++) {
            for (int j = (1 - key.length); j < lock.length; j++) {
                int[][] temp = move(key, lock.length, i, j);
                if (isUnlock(temp, lock)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] key = new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        new Solution().solution(key, lock);
    }
}
