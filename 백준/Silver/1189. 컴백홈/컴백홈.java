import java.util.*;
import java.io.*;

public class Main {

	public static int R, C, K;
	public static char[][] map;
	public static boolean[][] visit;
	public static int answer = 0;

	public static int[] dr = {1, -1, 0, 0};
	public static int[] dc = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visit = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		// 시작점 방문 표시 후 시작
		visit[R - 1][0] = true;
		dfs(R - 1, 0, 1);

		System.out.println(answer);
	}

	public static void dfs(int r, int c, int depth) {
		if (depth == K) {
			if (r == 0 && c == C - 1) {
				answer++;
			}
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
			if (map[nr][nc] == 'T') continue;
			if (visit[nr][nc]) continue;

			visit[nr][nc] = true;
			dfs(nr, nc, depth + 1);
			visit[nr][nc] = false;
		}
	}
}