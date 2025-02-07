import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int maxDistance = 0;

        // 모든 육지에서 BFS 실행
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'L') {
                    maxDistance = Math.max(maxDistance, bfs(i, j));
                }
            }
        }

        System.out.println(maxDistance);
    }

    static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        queue.offer(new int[]{x, y, 0});
        visited[x][y] = true;

        int maxDist = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            int dist = current[2];

            maxDist = Math.max(maxDist, dist);

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < R && ny >= 0 && ny < C && !visited[nx][ny] && map[nx][ny] == 'L') {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, dist + 1});
                }
            }
        }

        return maxDist;
    }
}
