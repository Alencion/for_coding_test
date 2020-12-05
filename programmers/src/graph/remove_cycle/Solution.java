package graph.remove_cycle;

import java.util.*;

class Solution {
    static int solution(int n, int[][] edges) {
        // init graph
        var g = new Graph(n, edges);
        // prune leaf nodes
        g.prune();

        // get nodes of max degree
        var candidateNodes = g.getMaxDegreeNodes();
        var untested = true;
        var answer = 0;

        for (var node : candidateNodes) {
            if (untested) {
                g.removeNode(node);
                if (g.hasCycle()) break;
                untested = false;
            }
            answer += node;
        }

        return answer;
    }

    static class Graph {
        HashMap<Integer, HashSet<Integer>> adjList;
        int size;

        Graph(int size, int[][] edges) {
            this.size = size;
            adjList = new HashMap<>(size);
            var estimatedEdgesCapacity = (int) Math.ceil((double) edges.length*2/size);
            for (var i=1; i<=size; i++) {
                adjList.put(i, new HashSet<>(estimatedEdgesCapacity));
            }

            for(var edge : edges) {
                var a = edge[0];
                var b = edge[1];
                adjList.get(a).add(b);
                adjList.get(b).add(a);
            }
        }

        void prune() {
            Stack<Integer> nodes = new Stack<>();
            nodes.addAll(adjList.keySet());
            nodes.sort(Comparator.comparingInt(a -> -adjList.get(a).size()));

            while (!nodes.empty()) {
                var node = nodes.pop();
                var adjNodes = adjList.get(node);
                if (adjNodes == null) continue; // already removed by neighbor

                var size = adjNodes.size();
                if (size == 0) continue;
                if (size != 1) break;

                // remove node from neighbor
                var adjNode = adjNodes.iterator().next();
                var adjAdjNodes = adjList.get(adjNode);
                adjAdjNodes.remove(node);

                // rearrange neighbor to top
                if (adjAdjNodes.size() == 1) nodes.push(adjNode);

                // remove node itself
                adjList.remove(node);
            }
        }

        // detect cycle by union-find
        boolean hasCycle() {
            var set = new DisjointSet(this.size);
            var visitedEdges = new HashSet<Integer>(this.size);
            for (var entry : adjList.entrySet()) {
                var node = entry.getKey();

                var adjNodes = entry.getValue();
                for (var adjNode : adjNodes) {
                    // already added edge?
                    var edgeHash = node * this.size + adjNode;
                    if (visitedEdges.contains(edgeHash)) continue;

                    // mark edge
                    visitedEdges.add(edgeHash);
                    visitedEdges.add(adjNode * this.size + node);

                    if (set.find(node) == set.find(adjNode)) return true;
                    set.union(node, adjNode);
                }
            }
            return false;
        }

        // get nodes of max degree
        ArrayList<Integer> getMaxDegreeNodes() {
            // nodes list sorted by descending degree
            var nodes = new ArrayList<>(adjList.keySet());
            nodes.sort(Comparator.comparingInt(a -> adjList.get(a).size()).reversed());

            var maxDegreeNodes = new ArrayList<Integer>();
            int maxDegree = 2; // nodes in cycle shall have 2 degree at minimum
            for (var node : nodes) {
                var degree = adjList.get(node).size();
                if (degree >= maxDegree) {
                    maxDegree = degree; // will not collect nodes of smaller degree than max degree
                    maxDegreeNodes.add(node);
                } else {
                    break;
                }
            }

            return maxDegreeNodes;
        }

        void removeNode(int node) {
            var adjNodes = adjList.get(node);

            // remove node from neighbors
            for (var adjNode : adjNodes) {
                adjList.get(adjNode).remove(node);
            }

            // remove node itself
            adjList.remove(node);
        }
    }

    static class DisjointSet {
        int[] parent;

        DisjointSet(int maxNodeNum) {
            parent = new int[maxNodeNum+1];
            for (var i=1; i<=maxNodeNum; i++)
                parent[i] = -1;
        }

        void union(int a, int b) {
            var ap = find(a);
            var bp = find(b);
            if (ap != bp) {
                var sizeA = -parent[ap];
                var sizeB = -parent[bp];
                if (sizeA > sizeB) {
                    parent[bp] = ap;
                    parent[ap] -= sizeB;
                } else {
                    parent[ap] = bp;
                    parent[bp] -= sizeA;
                }
            }
        }

        int find(int a) {
            var p = a;

            while (parent[p] > 0) p = parent[p];
            if (p != a) {
                var t = a;
                while (parent[t] > 0) {
                    var tp = parent[t];
                    parent[t] = p;
                    t = tp;
                }
            }
            return p;
        }
    }
}