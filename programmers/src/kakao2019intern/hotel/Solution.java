package kakao2019intern.hotel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    public long[] solution(long k, long[] room_number) {
        int N = room_number.length;
        long[] answer = new long[N];
        HashMap<Long, Long> isFull = new HashMap<>();

        for (int i = 0; i < N; i++) {
            long hopeNumber = room_number[i];
            if (!isFull.containsKey(hopeNumber)) {
                answer[i] = hopeNumber;
                isFull.put(hopeNumber, hopeNumber + 1);
            } else {
                ArrayList<Long> list = new ArrayList<>();
                while (isFull.containsKey(hopeNumber)) {
                    list.add(hopeNumber);
                    hopeNumber = isFull.get(hopeNumber);
                }
                answer[i] = hopeNumber;
                list.add(hopeNumber);
                for (long l : list) {
                    isFull.put(l, hopeNumber + 1);
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int k = 10;
        long[] room_number = new long[]{3, 3, 3, 4, 3, 1};
        new Solution().solution(k, room_number);
    }
}
