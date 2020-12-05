package p4;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int solution(String depar, String hub, String dest, String[][] roads){

        Map<String, City> map = new HashMap<>();
        Map<String, Boolean> visited = new HashMap<>();
        Map<String, Integer> dp = new HashMap<>();

        for (String[] road : roads) {
            if (!map.containsKey(road[0])){
                visited.put(road[0], false);
                dp.put(road[0], 0);
                map.put(road[0], new City(road[0]));
            }
            if (!map.containsKey(road[1])){
                visited.put(road[1], false);
                dp.put(road[1], 0);
                map.put(road[1], new City(road[1]));
            }

            City to = map.get(road[0]);
            City from = map.get(road[1]);

            from.comeIn.put(road[0], to);
        }

        visited.put(depar, true);
        dp.put(depar, 1);
        dfs(hub, map, dp, visited);

        for (Map.Entry<String, Boolean> entry : visited.entrySet()) {
            if (visited.get(entry.getKey()) && !entry.getKey().equals(hub))
                dp.put(entry.getKey(), 0);
        }

        visited.put(hub, true);
        dfs(dest, map, dp, visited);

        return dp.get(dest);
    }

    private void dfs(String dest, Map<String, City> map, Map<String, Integer> dp, Map<String, Boolean> visited) {
        City city = map.get(dest);
        int sum = 0;

        for (City from : city.comeIn.values()) {
            if (!visited.get(from.name)) {
                visited.put(from.name, true);
                dfs(from.name, map, dp, visited);
            }
            sum += dp.get(from.name);
        }
        dp.put(dest, sum);
    }

    private class City{
        String name;
        Map<String, City> comeIn;

        public City(String name) {
            this.name = name;
            this.comeIn = new HashMap<>();
        }
    }

    @Test
    public void test(){
        String depar = "SEOUL";
        String hub = "DAEGU";
        String dest = "YEOSU";

        String[][] roads = {{"ULSAN","BUSAN"},{"DAEJEON","ULSAN"},{"DAEJEON","GWANGJU"},{"SEOUL","DAEJEON"},{"SEOUL","ULSAN"},{"DAEJEON","DAEGU"},{"GWANGJU","BUSAN"},
                {"DAEGU","GWANGJU"},{"DAEGU","BUSAN"},{"ULSAN","DAEGU"},{"GWANGJU","YEOSU"},{"BUSAN","YEOSU"}};

        Assert.assertEquals(9, new Solution().solution(depar, hub, dest, roads));
    }

    @Test
    public void test2(){
        String depar = "ULSAN";
        String hub = "SEOUL";
        String dest = "BUSAN";

        String[][] roads = {{"SEOUL","DAEJEON"},{"SEOUL","ULSAN"},
                {"DAEJEON","ULSAN"},{"DAEJEON","BUSAN"},{"DAEJEON","GWANGJU"},
                {"ULSAN","BUSAN"},
                {"GWANGJU","BUSAN"}};

        Assert.assertEquals(0, new Solution().solution(depar, hub, dest, roads));
    }
}
