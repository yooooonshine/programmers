import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    private static int V;
    private static LinkedList<Integer> adj[]; // 링크드리스트의 배열


    static void addEdge (int v, int w) { // v번째 LinkedList 에 w를 삽입
        adj[v].add(w);
        adj[w].add(v);
    }
    // DFS 함수
    static void DFS() { // v를 시작노드로!
        boolean visited[] = new boolean[V + 1]; // 각 노드이 방문 여부를 저장하기 위해
        int[] colorList = new int[V + 1]; //각 노드의 색 저장
        for (int i = 1; i <= V; i++) {
            if (!visited[i]) {
                if (!DFSUtil(i, visited, colorList, 1)) {
                    System.out.println("NO");
                    return;
                }    
            }
            
        }
        System.out.println("YES");
    }
    // DFS에서 호출되는 함수
    static boolean DFSUtil(int v, boolean visited[], int[] colorList, int color)  { //color는 이전 컬러
        if (color == 1) {
            colorList[v] = 2;
        } else {
            colorList[v] = 1;
        }
        // 현재 노드를 방문한 것으로 체크 (visited의 v번째 요소를 true로)
        visited[v] = true;

        // 방문한 노드와 인접한 모든 노드를 가지고 온다
        Iterator<Integer> it = adj[v].listIterator();
        while (it.hasNext()) {
            int n = it.next();
            if (colorList[n] == colorList[v]) { //인접노드와 컬러가 같으면 이분그래프가 아니다.
                return false;
            }
            // 방문하지 않은 노드면 해당 노드를 다시 시작 노드로하여 DFSUtil을 호출
            if (!visited[n]) {
                if (!DFSUtil(n, visited, colorList, colorList[v])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            String[] stringList = br.readLine().split(" ");
            V = Integer.parseInt(stringList[0]);
            int E = Integer.parseInt(stringList[1]); //간선의 개수
            adj = new LinkedList[V + 1]; // 1<= 정점 <= V
            // v개의 LinkedList 선언 및 생성

            for (int j = 1; j <= V; j++) { //링크드 리스트 생성
                adj[j] = new LinkedList();
            }

            for (int j = 0; j < E; j++) {
                String[] temporaryStringList = br.readLine().split(" ");
                int vertex1 = Integer.parseInt(temporaryStringList[0]); //정점1
                int vertex2 = Integer.parseInt(temporaryStringList[1]); //정점2
                addEdge(vertex1, vertex2); //간선 추가
            }



            DFS();
        }
    }


}


