package graph.countOfRooms;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] arrows) {
        int answer = 0;
        Set<String> lineSet = new HashSet<>();
        Set<String> pointSet = new HashSet<>();

        int x = 0;
        int y = 0;

        pointSet.add("" + x+"|"+y);

        for (int i = 0; i < arrows.length; i++) {
            for(int j = 0; j < 2; j++){
                int vect = arrows[i];
                String start = ""+ x+"|"+y;

                if(vect<=1 || vect==7) y++;
                if(1<=vect && vect<=3) x++;
                if(3<=vect && vect<=5) y--;
                if(5<=vect && vect<=7) x--;
                String point = "" + x+"|"+y;
                String normalLine = start +"|" + point;
                String backLine =  point + "|" + start;

                if(pointSet.contains(point)){
                    if(!lineSet.contains(normalLine)){
                        answer++;
                    }
                }
                pointSet.add(point);
                lineSet.add(normalLine);
                lineSet.add(backLine);
            }

        }

        return answer;
    }

    @Test
    public void test() {
        int[] arrows = {6, 6, 4, 4, 4, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0};
        Assert.assertEquals(4, new Solution().solution(arrows));
    }

    @Test
    public void test2() {
        int[] arrows = {6, 5, 2, 7, 1, 4, 2, 4, 6};
        Assert.assertEquals(3, new Solution().solution(arrows));
    }

    @Test
    public void test3() {
        int[] arrows = {5, 2, 7, 1, 6, 3};
        Assert.assertEquals(3, new Solution().solution(arrows));
    }

    @Test
    public void test4() {
        int[] arrows = {6, 2, 4, 0, 5, 0, 6, 4, 2, 4, 2, 0};
        Assert.assertEquals(3, new Solution().solution(arrows));
    }
}
