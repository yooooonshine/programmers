import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] next;
    static int[] visited; // 0: 미방문, >0: 방문 라운드 ID
    static int teamCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            next = new int[N + 1];
            visited = new int[N + 1];
            teamCnt = 0; // ★ 테스트 케이스마다 리셋

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) next[i] = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= N; i++) {
                if (visited[i] != 0) continue;

                int cur = i;
                while (visited[cur] == 0) {     // 현재 라운드 i로 표기
                    visited[cur] = i;
                    cur = next[cur];
                }
                if (visited[cur] == i) {        // 같은 라운드에서 만났다면 사이클
                    int cycle = 1;
                    for (int v = next[cur]; v != cur; v = next[v]) cycle++;
                    teamCnt += cycle;
                }
                // visited[cur] != i 인 경우: 이전 라운드에서 이미 처리됨 → 패스
            }

            sb.append(N - teamCnt).append('\n');
        }
        System.out.print(sb.toString());
    }
}