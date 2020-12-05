package graph.rank;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

public class Solution {

    public int solution(int n, int[][] results) {

        int answer = 0;

        ArrayList<Player> players = new ArrayList<>();

        for (int i = 0; i <= n; i++)
            players.add(new Player(i));

        for (int[] result : results) {
            players.get(result[0]).win.add(result[1]);
            players.get(result[1]).lose.add(result[0]);
        }

        for (int depth = 0; depth < n; depth++) {
            for (int i = 1; i <= n; i++) {
                Player player = players.get(i);

                HashSet<Integer> winSet = new HashSet<>();
                for (Integer win : player.win)
                    winSet.addAll(players.get(win).win);

                player.win.addAll(winSet);

                HashSet<Integer> loseSet = new HashSet<>();
                for (Integer lose : player.lose)
                    loseSet.addAll(players.get(lose).lose);

                player.lose.addAll(loseSet); // 추가
            }
        }

        for (Player player : players) {
            int size = player.win.size() + player.lose.size();

            if (size == n - 1) {
                answer++;
            }
        }
        return answer;
    }

    @Test
    public void 정답() {
        Assert.assertEquals(2, solution(5, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}));
    }


}

class Player {
    int code;

    HashSet<Integer> win = new HashSet<>();
    HashSet<Integer> lose = new HashSet<>();

    public Player(int code) {
        this.code = code;
    }
}