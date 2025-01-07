import java.util.*;
import java.io.*;

class Solution {
    public int maxX;
    public int maxY;
    public Edge[][] adj;
    public List<List<Integer>> level;
    public int[][] answer;
    public Map<Integer, Integer> myM;
    
    public int[][] solution(int[][] nodeinfo) {
        myM = new HashMap<>(); // x값, 인덱스
        
        int c = nodeinfo.length;
        // 0번 인덱스가 x, 1번 인덱스가 y
        maxX = 0;
        for (int i = 0; i < c; i++) {
            if (maxX < nodeinfo[i][0]) {
                maxX = nodeinfo[i][0];
            }
        }
        
        maxY = 0;
        for (int i = 0; i < c; i++) {
            if (maxY < nodeinfo[i][1]) {
                maxY = nodeinfo[i][1];
            }
        }
        
        // 자식 노드
        adj = new Edge[maxX + 1][2]; // 왼 오,
        
        // 레벨별 노드
        level = new ArrayList<>();
        for (int i = 0; i <= maxY; i++) {
            level.add(new ArrayList<>());
        }
        for (int i = 0; i < c; i++) {
            level.get(nodeinfo[i][1]).add(nodeinfo[i][0]);
            myM.put(nodeinfo[i][0], i + 1);
        }
        
        makeTree();
        
        answer = new int[2][c];
        int x = level.get(maxY).get(0);
        System.out.println(x);
        
        List<Integer> tmp = new ArrayList<>();
        preOrder(x, tmp);

        for (int i = 0; i < c; i++) {
            answer[0][i] = tmp.get(i);
        }
        
        tmp = new ArrayList<>();
        postOrder(x, tmp);
        for (int i = 0; i < c; i++) {
            answer[1][i] = tmp.get(i);
        }
        
        return answer;
    }
    
    public void makeTree() {
        Queue<Node> myQ = new LinkedList<>();
        int x = level.get(maxY).get(0);
        Node node1 = new Node(x, maxY, 0, maxX);
        myQ.add(node1);
    
        
        int nowY = maxY;
        while (!myQ.isEmpty()) {
            Node tmpNode = myQ.poll();
            nowY = tmpNode.y;
            
            nowY--;
            while (nowY >= 0 && level.get(nowY).size() == 0) {
                nowY--;
            }
            if (nowY < 0 || level.get(nowY).size() == 0) {
                continue;
            }
            
            int l = tmpNode.l;
            int r = tmpNode.r;
            
            for (int tmpX : level.get(nowY)) {
                if (l <= tmpX && tmpX < tmpNode.x) {
                    adj[tmpNode.x][0] = new Edge(tmpX, nowY);
                    myQ.add(new Node(tmpX, nowY, l, tmpNode.x - 1));
                } else if (tmpNode.x < tmpX && tmpX <= r) {
                    adj[tmpNode.x][1] = new Edge(tmpX, nowY);
                    myQ.add(new Node(tmpX, nowY, tmpNode.x + 1, r));
                }
            }
        }
    }
    
    // 중앙 왼, 오
    public void preOrder(int x, List<Integer> tmp) {
        tmp.add(myM.get(x));
        if (adj[x][0] != null) {
            preOrder(adj[x][0].x, tmp);
        }
        if (adj[x][1] != null) {
            preOrder(adj[x][1].x, tmp);
        }
    }
    
    // 왼 오 중앙
    public void postOrder(int x, List<Integer> tmp) {
        if (adj[x][0] != null) {
            postOrder(adj[x][0].x, tmp);
        }
        
        if (adj[x][1] != null) {
            postOrder(adj[x][1].x, tmp);
        }
        tmp.add(myM.get(x));
    }
    
    public static class Node {
        int x;
        int y;
        int l;
        int r;
        
        public Node(int x, int y, int l, int r) {
            this.x = x;
            this.y = y;
            this.l = l;
            this.r = r;
        }
    }
    
    public static class Edge {
        int x;
        int y;
        
        public Edge(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

// 방문할 곳 2차원 좌표
// 각 장소를 이진트리로 구성


// 트리 x, y는 정수
// x는 모두 다르다.
// y가 같으면 같은 레벨
// y가 작으면 자식노드
// 전위 순회, 후위 순화한 결과를 2차원 배열에

// 인덱스는 0번부터
// 전위 순회 (중앙, 왼, 오)
// 후위 순회 (왼, 오, 중앙)

// 노드를 y순으로 정렬한다. 내림차순(그 안의 x는 오름 차순)
// 

// 노드는 형성할 수 없겠다.
// 그냥 순회해야 돼 
// 이진탐색같네
// 재귀?
// 루트 찾는다.
// 루트 기준 왼 오 나눈다.(이 때 최상최하기준)
// 안으로 왼쪽은 처음 부터 자신 전까지
// 안으로 오른쪽은 자신 + 1부터 끝까지
// Queue를 사용하면 되겠는데?
// Queue에 범위를 넣어둔다.
// 그 아래 범위에 대해 탐색하면서 자식으로 넣는다. 범위가 끝나면 다음 Queue로 넘어간다.
// Node(자신 위치, 왼끝 오른 끝)
// 노드를 깊이 기준으로 List에 넣어둔다.
// 다음 깊이에 대해 자식 노드를 찾는다. 다 찾으면 넘어간다.(queue에 넣는다.)
