package p944_delete_columns_to_make_sorted;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution {
    public int minDeletionSize(String[] A) {
        int answer = 0;

        int size = A.length;
        int stringLength = A[0].length();

        for (int i = 0; i < stringLength; i++) {
            char alphabet = A[0].charAt(i);
            for (int j = 1; j < size; j++) {
                if (alphabet > A[j].charAt(i)){
                    answer++;
                    break;
                }
                alphabet = A[j].charAt(i);
            }

        }
        return answer;
    }

    @Test
    void test() {
        String[] A = {"cba", "daf", "ghi"};

        Assertions.assertEquals(1, new Solution().minDeletionSize(A));
    }
}
