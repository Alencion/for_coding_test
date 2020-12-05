package skillTest.level_three.assume_rating;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * n명의 권투선수가 권투 대회에 참여했고 각각 1번부터 n번까지 번호를 받았습니다.
 * 권투 경기는 1대1 방식으로 진행이 되고, 만약 A 선수가 B 선수보다 실력이 좋다면 A 선수는 B 선수를 항상 이깁니다.
 * 심판은 주어진 경기 결과를 가지고 선수들의 순위를 매기려 합니다. 하지만 몇몇 경기 결과를 분실하여 정확하게 순위를 매길 수 없습니다.
 *
 * 선수의 수 n, 경기 결과를 담은 2차원 배열 results가 매개변수로 주어질 때 정확하게 순위를 매길 수 있는 선수의 수를 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한사항
 * 선수의 수는 1명 이상 100명 이하입니다.
 * 경기 결과는 1개 이상 4,500개 이하입니다.
 * results 배열 각 행 [A, B]는 A 선수가 B 선수를 이겼다는 의미입니다.
 * 모든 경기 결과에는 모순이 없습니다.
 */
public class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;

        Player[] players = new Player[n];
        for (int i = 1; i <= n; i++) {
            players[i] = new Player(i);
        }

        for (int[] result : results) {
            setResult(players, result[0], result[1]);
        }

        for (Player player : players) {
            if (player.win.size() + player.lose.size() == n - 1) {
                answer++;
            }
        }
        return answer;
    }

    private void setResult(Player[] players, int win, int lose) {
        Player winPlayer = players[win];
        Player losePlayer = players[lose];

        if (winPlayer.win.contains(lose) || losePlayer.lose.contains(win)) return;
        winPlayer.win.add(lose);
        losePlayer.lose.add(win);

        for (int playerIndex : winPlayer.lose) {
            setResult(players, playerIndex, lose);
        }

        for (int playerIndex : losePlayer.win) {
            setResult(players, win, playerIndex);
        }
    }

    private static class Player {
        int index;
        Set<Integer> win = new HashSet<>();
        Set<Integer> lose = new HashSet<>();

        public Player(int index) {
            this.index = index;
        }
    }

    @Test
    public void test() {
        int n = 5;
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};

        Assert.assertEquals(2, new Solution().solution(n, results));
    }
}


//public class Solution {
//    public int solution(int n, int[][] results) {
//        int answer = 0;
//        List<Person> people = new ArrayList<>();
//
//        for (int i = 0; i < n; i++) {
//            people.add(new Person(i + 1));
//        }
//
//        for(int[] result : results){
//            Person winPerson = people.get(result[0] - 1);
//            Person losePerson = people.get(result[1] - 1);
//
//            winPerson.win.add(losePerson.index);
//            winPerson.win.addAll(losePerson.win);
//
//            for(int index : winPerson.lose){
//                people.get(index - 1).win.add(losePerson.index);
//                people.get(index - 1).win.addAll(losePerson.win);
//            }
//
//            for(int index : winPerson.win){
//                people.get(index - 1).lose.add(winPerson.index);
//                people.get(index - 1).lose.addAll(winPerson.lose);
//            }
//
//
//        }
//
//        answer = (int) people.stream().filter(person ->
//            person.win.size() + person.lose.size() == n -1
//        ).count();
//        return answer;
//    }
//
//
//    @Test
//    public void test1(){
//        int n = 5;
//        int[][] results = {{4,3}, {4,2}, {3,2}, {1,2}, {2,5}};
//
//        Assert.assertEquals(2, new Solution().solution(n, results));
//    }
//
//    @Test
//    public void test2(){
//        int n = 5;
//        int[][] results = {{2,5}, {1,2}, {3,2}, {4,2}, {4,3}};
//
//        Assert.assertEquals(2, new Solution().solution(n, results));
//    }
//
//    @Test
//    public void test3(){
//        int n = 4;
//        int[][] results = {{1,2}, {2,3}, {3,4}};
//
//        Assert.assertEquals(2, new Solution().solution(n, results));
//    }
//
//    class Person{
//        int index;
//        Set<Integer> lose;
//        Set<Integer> win;
//
//        public Person(int index) {
//            this.lose = new HashSet<>();
//            this.win = new HashSet<>();
//            this.index = index;
//        }
//    }
//}