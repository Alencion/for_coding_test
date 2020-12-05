package m10.p3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class Solution {
    static public int solution(int n, int[][] edges) {
        List<Integer>[] list = new ArrayList[n+1];

        for(int i=1; i<=n; i++) list[i] = new ArrayList<>();

        for(int[] edge : edges) {
            list[edge[0]].add(edge[1]);
            list[edge[1]].add(edge[0]);
        }

        int[] result = bfs(list, 1, n);

        int start = 1, max = 0, cnt = 0;
        for(int i=2; i<=n; i++) {
            if (result[i] > result[start]) start = i;
        }

        max = 0; cnt = 0;
        result = bfs(list, start, n);

        start = 1;
        for(int i=1; i<=n; i++)
            if(result[i]>result[start]) start = i;

        for(int i : result) max = Math.max(max, i);

        for(int i : result) if(max==i) cnt++;

        if(cnt>=2) return max;

        max = 0; cnt = 0;
        result = bfs(list, start, n);
        start = 1;
        for(int i : result) max = Math.max(max, i);

        for(int i : result) if(max==i) cnt++;

        if(cnt>=2) return max;

        else if(cnt==1)return max-1;

        return result[start];
    }

    static int[] bfs(List<Integer>[] list, int start, int n) {
        boolean[] visit = new boolean[n+1];
        int[] dist = new int[n+1];
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        visit[start] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for(int i : list[now]) {
                if(i==now || visit[i]) continue;
                visit[i] = true;
                queue.add(i);
                dist[i] = dist[now] + 1;
            }
        }
        return dist;
    }

    @Test
    public void test() {
        int n = 4;
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}};

        Assertions.assertEquals(2, new Solution().solution(n, edges));
    }

    @Test
    public void test2(){
        int n = 5;
        int[][] edges ={{1, 5}, {2, 5}, {3, 5}, {4, 5}};

        Assertions.assertEquals(2, new Solution().solution(n, edges));
    }
}


//    vector<vector<int>> Node;
//    vector<bool> Visit;
//    int Leaf, Max_Len, Cnt;
//
//    void DFS(int Cur, int Len, bool T) {
//        if (Visit[Cur] == true) return;
//        if (T == false) {
//            if (Len > Max_Len) {
//                Leaf = Cur;
//                Max_Len = Len;
//            }
//        } else {
//            if (Len > Max_Len) {
//                Leaf = Cur;
//                Cnt = 1;
//                Max_Len = Len;
//            } else if (Len == Max_Len) Cnt++;
//        }
//
//        Visit[Cur] = true;
//        for (int i = 0; i < Node[Cur].size(); i++) {
//            int Next = Node[Cur][i];
//            DFS(Next, Len + 1, T);
//        }
//    }
//
//    int solution(int n, vector<vector<int>> edges){
//        Node.resize(n + 1);
//        Visit.resize(n + 1, false);
//        for (int i = 0; i < edges.size(); i++) {
//            int N1 = edges[i][0];
//            int N2 = edges[i][1];
//            Node[N1].push_back(N2);
//            Node[N2].push_back(N1);
//        }
//        Leaf = 0;
//        Max_Len = 0;
//        DFS(1, 0, false);
//
//        Cnt = 0;
//        Max_Len = 0;
//        Visit.assign(n + 1, false);
//        DFS(Leaf, 0, true);
//        if (Cnt >= 2) return Max_Len;
//        else {
//            Cnt = 0;
//            Max_Len = 0;
//            Visit.assign(n + 1, false);
//            DFS(Leaf, 0, true);
//
//            if (Cnt >= 2) return Max_Len;
//            else return Max_Len - 1;
//        }
//    }

/** "Component" */
interface Graphic {

    //Prints the graphic.
    public void print();

}

/** "Composite" */
class CompositeGraphic implements Graphic {
    int i;

    public CompositeGraphic(int i) {
        this.i = i;
    }

    //Collection of child graphics.
    private List<Graphic> mChildGraphics = new ArrayList<Graphic>();

    //Prints the graphic.
    public void print() {
        System.out.println(this);
    }

    //Adds the graphic to the composition.
    public void add(Graphic graphic) {
        mChildGraphics.add(graphic);
    }

    //Removes the graphic from the composition.
    public void remove(Graphic graphic) {
        mChildGraphics.remove(graphic);
    }

    @Override
    public String toString() {
        return "\n------------------------------------\nCompositeGraphic{" +
                "i=" + i +
                ", mChildGraphics=" + mChildGraphics +
                "}\n--------------------------------------\n";
    }
}


/** "Leaf" */
class Ellipse implements Graphic {
    int i;

    public Ellipse(int i) {
        this.i = i;
    }

    //Prints the graphic.
    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Ellipse{" +
                "i=" + i +
                '}';
    }
}


/** Client */
class Program {

    public static void main(String[] args) {
        //Initialize four ellipses
        Ellipse ellipse1 = new Ellipse(1);
        Ellipse ellipse2 = new Ellipse(2);
        Ellipse ellipse3 = new Ellipse(3);
        Ellipse ellipse4 = new Ellipse(4);

        //Initialize three composite graphics
        CompositeGraphic graphic = new CompositeGraphic(0);
        CompositeGraphic graphic1 = new CompositeGraphic(1);
        CompositeGraphic graphic2 = new CompositeGraphic(2);

        //Composes the graphics
        graphic1.add(ellipse1);
        graphic1.add(ellipse2);
        graphic1.add(ellipse3);

        graphic2.add(ellipse4);

        graphic.add(graphic1);
        graphic.add(graphic2);

        //Prints the complete graphic (four times the string "Ellipse").
        graphic.print();
    }
}