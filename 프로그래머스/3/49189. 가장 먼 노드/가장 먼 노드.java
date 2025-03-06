import java.util.*;
import java.io.*;

class Solution {
    
    public int MAX = 10000000;
    
    public int solution(int n, int[][] edge) {
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int i = 0; i < edge.length; i++) {
            int e1 = edge[i][0];
            int e2 = edge[i][1];
            
            adj.get(e1).add(new Edge(e2, 1));
            adj.get(e2).add(new Edge(e1, 1));
        }
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));
        
        int[] dist = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dist[i] = MAX;
        }
        boolean[] visit = new boolean[n + 1];
        
        while (!pq.isEmpty()) {
            Edge nEdge = pq.poll();
            
            int nE = nEdge.e;
            int nD = nEdge.d;
            
            if (visit[nE]) {
                continue;
            }
            visit[nE] = true;
            
            for (Edge adjE : adj.get(nE)) {
                
                if (dist[adjE.e] > dist[nE] + adjE.d) {
                    dist[adjE.e] = dist[nE] + adjE.d;
                    pq.add(new Edge(adjE.e, dist[adjE.e]));
                }
            }
        }
        
        int count = 0;
        int max = 0;
        for (int i = 1; i <= n; i++) {
            
            if (dist[i] > max) {
                max = dist[i];
                count = 1;
            } else if (dist[i] == max) {
                count++;
            }
        }
        
        return count;
    }
    
    public class Edge implements Comparable<Edge> {
        int e;
        int d; 
        
        public Edge(int e, int d) {
            this.e = e;
            this.d = d;
        }
        
        @Override
        public int compareTo(Edge e) {
            return this.d - e.d;
        }
    }
}

// 양방향 간선

// 다익스트라

// 가장 먼 노드 == 간선의 개수가 가장 많은 노드
// 이게 몇개인지

// 노드의 개수 n, 
// 간선 2차원 vertext

// dist가 간선 수