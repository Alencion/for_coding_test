package dfs_bfs.network;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;

        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]){
                dfs(i, computers, visited);
                answer++;
            }
        }

        return answer;
    }

    private void dfs(int i, int[][] computers, boolean[] visited) {
        int[] computer = computers[i];
        visited[i] = true;

        for (int j = 0; j < computer.length; j++) {
            if (!visited[j] && computer[j] == 1)
                dfs(j, computers, visited);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};

        System.out.println(new Solution().solution(n, computers));

        int[][] computers2 = {{1,1,0}, {1,1,1}, {0,1,1}};
        System.out.println(new Solution().solution(n, computers2));
    }
}