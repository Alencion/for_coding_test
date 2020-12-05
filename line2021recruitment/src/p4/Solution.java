package p4;

import org.junit.Assert;
import org.junit.Test;

public class Solution {
    //방향 0-우 1-하 2-좌 3-상
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    public int solution(int[][] maze) {
        int answer = 0;
        int n = maze.length;
        Person person = new Person(0, 0, 0);

        int time = 0;
        while (!(person.y == n - 1 && person.x == n - 1)) {

            int newY = person.y + dy[person.dir];
            int newX = person.x + dx[person.dir];

            int leftY = person.y + dy[(person.dir + 3) % 4];
            int leftX = person.x + dx[(person.dir + 3) % 4];

            if (!vaild(newY, n) || !vaild(newX, n) || maze[newY][newX] == 1) {
                person.dir = (person.dir + 1) % 4;
                continue;
            } else if (vaild(leftY, n) && vaild(leftX, n) && maze[leftY][leftX] == 0){
                person.dir = (person.dir + 3) % 4;
            }
            person.x = newX;
            person.y = newY;
            time++;

        }

        answer = time;
        return answer;
    }

    private boolean vaild(int leftY, int n) {
        return leftY >= 0 && leftY < n;
    }

    private static class Person {
        int y;
        int x;
        int dir;

        public Person(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }

    @Test
    public void test() {
        int[][] maze = {{0, 1, 0, 1}, {0, 1, 0, 0}, {0, 0, 0, 0}, {1, 0, 1, 0}};

        Assert.assertEquals(10, new Solution().solution(maze));
    }
}
