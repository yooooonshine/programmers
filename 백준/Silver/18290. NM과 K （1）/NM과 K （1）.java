import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K;
    static int[][] arr;
    static boolean[][] visited;
    static int max = Integer.MIN_VALUE;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        System.out.println(max);
    }

    static void dfs(int depth, int start, int sum) {
        if (depth == K) {
            max = Math.max(max, sum);
            return;
        }

        for (int idx = start; idx < N * M; idx++) {
            int r = idx / M;
            int c = idx % M;

            if (visited[r][c]) continue;

            boolean canUse = true;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (inRange(nr, nc) && visited[nr][nc]) {
                    canUse = false;
                    break;
                }
            }

            if (!canUse) continue;

            visited[r][c] = true;
            dfs(depth + 1, idx + 1, sum + arr[r][c]);
            visited[r][c] = false;
        }
    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}