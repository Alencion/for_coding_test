package dfs_bfs.travelRoute;

import java.util.*;

class Solution {
    public String[] solution(String[][] tickets) {
        String[] answer = {};

        StringBuilder result = new StringBuilder();
        List<String> results = new ArrayList<>();

        HashMap<String, List<Integer>> map = new HashMap<>();
        boolean[] used = new boolean[tickets.length];

        for (int i = 0; i < tickets.length; i++) {
            String[] ticket = tickets[i];
            map.computeIfAbsent(ticket[0], k -> new ArrayList<>());
            List<Integer> list = map.get(ticket[0]);
            list.add(i);
        }

        String start = "ICN";
        result.append(start);
        result.append(",");
        dfs(start, map, tickets, used, result, results);

        Collections.sort(results);
        answer = results.get(0).split(",");
        return answer;
    }

    private void dfs(String from, HashMap<String, List<Integer>> map, String[][] tickets, boolean[] used, StringBuilder result, List<String> results) {
        if (isCheck(used)) {
            results.add(result.toString());
            return;
        }

        List<Integer> canUseTickets = map.get(from);
        if (canUseTickets == null) return;

        for (int index : canUseTickets) {
            String to = tickets[index][1];

            if (!used[index]) {
                used[index] = true;
                result.append(to);
                result.append(",");
                dfs(to, map, tickets, used, result, results);
                result.replace(result.toString().length()-4, result.toString().length(), "");
                used[index] = false;
            }
        }
    }

    private boolean isCheck(boolean[] used) {
        for (boolean check : used)
            if (!check) return false;
        return true;
    }

    public static void main(String[] args) {
//        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
//        String[][] tickets = {{"ICN","A"},{"A","B"},{"B","A"},{"A","ICN"},{"ICN","A"}};
        String[][] tickets = {{"ICN","AAA"},{"ICN","BBB"},{"BBB","ICN"}};
//        String[][] tickets = {{"ICN","BOO"}, {"ICN", "COO"}, {"COO", "DOO"}, {"DOO", "COO"}, {"BOO", "DOO"}, {"DOO", "BOO"}, {"BOO", "ICN"}, {"COO", "BOO"}};
//        String[][] tickets = {{"ICN", "BOO"}, {"ICN", "COO"}, {"COO", "DOO"}, {"DOO", "COO"}, {"BOO", "DOO"}, {"DOO", "BOO"},
//                {"BOO", "ICN"}, {"COO", "BOO"}};
//        String[][] tickets = {{"ICN", "COO"}, {"COO", "ICN"}, {"COO", "ICN"}};
        System.out.println(Arrays.toString(new Solution().solution(tickets)));
    }


    static boolean visit[];
    static List<String> list = new ArrayList<>();
    static String route = "";

    public String[] solution2(String[][] tickets) {
        visit = new boolean[tickets.length];

        for (int i = 0; i < tickets.length; i++) {

            String departure = tickets[i][0];
            String destination = tickets[i][1];

            if (departure.equals("ICN")) {
                visit[i] = true;
                route = departure + ",";
                dfs(tickets, destination, 1);
                visit[i] = false;
            }
        }

        Collections.sort(list);
        String[] answer = list.get(0).split(",");
        return answer;
    }

    public static void dfs(String[][] tickets, String end, int count) {

        route += end + ",";

        if (count == tickets.length) {
            list.add(route);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            String depart = tickets[i][0];
            String desti = tickets[i][1];

            if (end.equals(depart) && !visit[i]) {
                visit[i] = true;
                dfs(tickets, desti, count + 1);
                visit[i] = false;
                route = route.substring(0, route.length() - 4);
            }
        }
    }
}
