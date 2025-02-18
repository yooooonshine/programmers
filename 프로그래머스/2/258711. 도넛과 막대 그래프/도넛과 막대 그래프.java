import java.util.*;
import java.io.*;

class Solution {
    
    public static List<List<Integer>> adj;  
    public static int donut = 0;
    public static int stick = 0;
    public static int eight = 0;
    
    public int[] solution(int[][] edges) {
        adj = new ArrayList<>();
        boolean[] exist = new boolean[1000001];
        
        for (int i = 0; i <= 1000000; i++) {
            adj.add(new ArrayList<>());
        }
        
        List<List<Integer>> in = new ArrayList<>();
        for (int i = 0; i <= 1000000; i++) {
            in.add(new ArrayList<>());
        }
        
        for (int i = 0; i < edges.length; i++) {
            adj.get(edges[i][0]).add(edges[i][1]);
            in.get(edges[i][1]).add(edges[i][0]);
            exist[edges[i][0]] = true;
            exist[edges[i][1]] = true;
        }
        
        // 중앙 노드 찾기
        int mid = 0;
        int midMax = 0;
        for (int i = 1; i <= 1000000; i++) {
            if (in.get(i).size() == 0 && exist[i]) {
                if (midMax < adj.get(i).size()) {
                    mid = i;
                    midMax = adj.get(i).size();
                }
            }
        }
        
        dfs(mid);
        
        int[] answer = {mid, donut, stick, eight};
        return answer;
    }
    
    public static void dfs(int mid) {
        Stack<Integer> s = new Stack<>();
        boolean[] visit = new boolean[1000001];
        
        s.add(mid);
        
        while (!s.isEmpty()) {
            Integer node = s.pop();
        
            if (visit[node]) {
                return;
            }
            visit[node] = true;
            
            for (int tmp : adj.get(node)) {
                
                
                if (adj.get(tmp).size() >= 2) {
                    eight++;
                    continue;
                }
                
                if (visit[tmp]) {
                    donut++;
                    continue;
                }
                
                if (adj.get(tmp).size() == 0) {
                    stick++;
                    continue;
                }
                
                s.add(tmp);
            }
        }
    }
    
    // Stack 사용
    // 나가는 간선이 2개 이상이고 mid가 아니면 에잇 추가 나가기
    // dfs하다가 나가는 간선이 없으면 막대 추가, 끝
    // 돌다가 다음 간선이 이미 방문했으면 도넛 추가
}

// 도넛모양 그래프 -> n개 노드, n개 간선
// 막대모양 그래프 -> n개의 노드, n - 1개의 간선
//  8자모양 2n + 1개 정점, 2n + 2개 간선

// 무관한 정점 하나가 각 그래프로 연결
// 각 개수를 리턴

// 생성한 정점의 번호와, 각 그래프의 수

// 임의의 정점은 각 모든 방향으로 나간다.

// 그래프 탐색이구나
// dfs 하면되겠다.

// 1. 다른 곳으로 향하는 엣지가 없고, 원 위치로 돌아오면 도넛
// 2. 원 위치로 돌아오지 않으면 막대 -> 나가는 간선이 없다.
// 3. 다른 곳으로 향하는 엣지가 있으면 -> 나가는 간선이 2개 이상?인 곳이 있다.
