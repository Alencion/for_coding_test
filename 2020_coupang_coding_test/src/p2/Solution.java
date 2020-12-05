package p2;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.PriorityQueue;

public class Solution {
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public int solution(int n , String[] customers){

        PriorityQueue<Kiosk> kiosks = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            kiosks.add(new Kiosk(i));
        }

        for (String customer : customers) {
            String[] split = customer.split(" ");

            Kiosk kiosk = kiosks.poll();

            LocalDateTime plus = LocalDateTime.parse("2020/"+split[0] +" " + split[1], format).plus(Long.parseLong(split[2]), ChronoUnit.MINUTES);
            kiosk.setLocalDateTime(plus);
            kiosk.count += 1;
            kiosks.add(kiosk);
        }

        int max = 0;
        for (Kiosk kiosk : kiosks) {
            max = Math.max(kiosk.count , max);
        }
        return max;
    }

    private class Kiosk implements Comparable<Kiosk>{
        int count;
        int index;
        LocalDateTime localDateTime;

        public Kiosk(int index) {
            count =0;
            this.index = index;
        }

        public void setLocalDateTime(LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
        }

        @Override
        public int compareTo(Kiosk o) {
            if (localDateTime == null && o.localDateTime == null) return Integer.compare(index, o.index);
            if (localDateTime == null) return -1;
            if (o.localDateTime == null) return 1;
            if (localDateTime.equals(o.localDateTime)) return Integer.compare(index, o.index);
            return localDateTime.compareTo(o.localDateTime);
        }
    }


    @Test
    public void test(){
        int n =3;

        String[] customer = {"10/01 23:20:25 30", "10/01 23:25:50 26", "10/01 23:31:00 05", "10/01 23:33:17 24", "10/01 23:50:25 13", "10/01 23:55:45 20", "10/01 23:59:39 03", "10/02 00:10:00 10"};
        Assert.assertEquals(4,new Solution().solution(n, customer));
    }
}
